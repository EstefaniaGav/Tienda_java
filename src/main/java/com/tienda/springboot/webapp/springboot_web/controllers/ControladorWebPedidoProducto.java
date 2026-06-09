package com.tienda.springboot.webapp.springboot_web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tienda.springboot.webapp.springboot_web.entities.Pedido;
import com.tienda.springboot.webapp.springboot_web.entities.PedidoProducto;
import com.tienda.springboot.webapp.springboot_web.repositories.RepositorioPedido;
import com.tienda.springboot.webapp.springboot_web.repositories.RepositorioPedidoProducto;
import com.tienda.springboot.webapp.springboot_web.repositories.RepositorioCliente;
import com.tienda.springboot.webapp.springboot_web.repositories.RepositorioEmpleado;
import com.tienda.springboot.webapp.springboot_web.repositories.RepositorioProducto;

@Controller
@RequestMapping("/pedidoproducto")
public class ControladorWebPedidoProducto {

    @Autowired
    private RepositorioPedido repositorioPedido;

    @Autowired
    private RepositorioPedidoProducto repositorioPedidoProducto;

    @Autowired
    private RepositorioCliente repositorioCliente;

    @Autowired
    private RepositorioEmpleado repositorioEmpleado;

    @Autowired
    private RepositorioProducto repositorioProducto;

    @GetMapping
    public String listarPedidos(Model model) {
        List<Pedido> pedidos = repositorioPedido.findAll();
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("pedido", new Pedido());
        
        // Cargar listas para los selects
        model.addAttribute("clientes", repositorioCliente.findAll());
        model.addAttribute("empleados", repositorioEmpleado.findAll());
        model.addAttribute("productos", repositorioProducto.findAll());
        
        return "pages/pedidoproducto";
    }

    @GetMapping("/consultar/{id}")
    public String consultarPedido(@PathVariable Integer id, Model model) {
        Optional<Pedido> pedido = repositorioPedido.findById(id);
        if (pedido.isPresent()) {
            model.addAttribute("pedido", pedido.get());
            model.addAttribute("clientes", repositorioCliente.findAll());
            model.addAttribute("empleados", repositorioEmpleado.findAll());
            model.addAttribute("productos", repositorioProducto.findAll());
            
            // Obtener los productos del pedido
            List<PedidoProducto> pedidoProductos = repositorioPedidoProducto.findByPedido(pedido.get());
            model.addAttribute("pedidoProductos", pedidoProductos);
            
            return "pages/pedidoproducto";
        } else {
            return "redirect:/pedidoproducto";
        }
    }

    @PostMapping("/crear")
    public String crearPedido(@ModelAttribute Pedido pedido,
            @RequestParam Integer idProducto,
            @RequestParam Integer cantidadCompra,
            RedirectAttributes redirectAttributes) {
        
        // Guardar el pedido
        Pedido pedidoGuardado = repositorioPedido.save(pedido);
        
        // Crear la relación PedidoProducto
        PedidoProducto pedidoProducto = new PedidoProducto();
        pedidoProducto.setPedido(pedidoGuardado);
        pedidoProducto.setProducto(repositorioProducto.findById(idProducto).orElse(null));
        pedidoProducto.setCantidadCompra(cantidadCompra);
        repositorioPedidoProducto.save(pedidoProducto);
        
        redirectAttributes.addFlashAttribute("mensaje", "Pedido creado exitosamente");
        return "redirect:/pedidoproducto";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarPedido(@PathVariable Integer id,
            @ModelAttribute Pedido detallePedido,
            @RequestParam Integer idProducto,
            @RequestParam Integer cantidadCompra,
            RedirectAttributes redirectAttributes) {
        
        Optional<Pedido> pedido = repositorioPedido.findById(id);
        if (pedido.isPresent()) {
            Pedido ped = pedido.get();
            ped.setNumeroFactura(detallePedido.getNumeroFactura());
            ped.setDescripcion(detallePedido.getDescripcion());
            ped.setCliente(detallePedido.getCliente());
            ped.setEmpleado(detallePedido.getEmpleado());
            repositorioPedido.save(ped);
            
            // Actualizar PedidoProducto (simplificado: eliminar y crear nuevo)
            List<PedidoProducto> pedidoProductos = repositorioPedidoProducto.findByPedido(ped);
            if (!pedidoProductos.isEmpty()) {
                repositorioPedidoProducto.deleteAll(pedidoProductos);
            }
            
            PedidoProducto pedidoProducto = new PedidoProducto();
            pedidoProducto.setPedido(ped);
            pedidoProducto.setProducto(repositorioProducto.findById(idProducto).orElse(null));
            pedidoProducto.setCantidadCompra(cantidadCompra);
            repositorioPedidoProducto.save(pedidoProducto);
            
            redirectAttributes.addFlashAttribute("mensaje", "Pedido actualizado exitosamente");
        }
        return "redirect:/pedidoproducto";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable Integer id,
            RedirectAttributes redirectAttributes) {
        if (repositorioPedido.existsById(id)) {
            // Primero eliminar los PedidoProducto asociados
            Optional<Pedido> pedido = repositorioPedido.findById(id);
            if (pedido.isPresent()) {
                List<PedidoProducto> pedidoProductos = repositorioPedidoProducto.findByPedido(pedido.get());
                repositorioPedidoProducto.deleteAll(pedidoProductos);
            }
            
            // Luego eliminar el pedido
            repositorioPedido.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Pedido eliminado exitosamente");
        }
        return "redirect:/pedidoproducto";
    }
}

// Made with Bob
