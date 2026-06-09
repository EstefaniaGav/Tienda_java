package com.tienda.springboot.webapp.springboot_web.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tienda.springboot.webapp.springboot_web.entities.Producto;
import com.tienda.springboot.webapp.springboot_web.repositories.RepositorioProducto;
import com.tienda.springboot.webapp.springboot_web.repositories.RepositorioPedidoProducto;

@Controller
@RequestMapping("/productos")
public class ControladorWebProducto {

    @Autowired
    private RepositorioProducto repositorioProducto;

    @Autowired
    private RepositorioPedidoProducto repositorioPedidoProducto;

    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = repositorioProducto.findAll();
        model.addAttribute("productos", productos);
        model.addAttribute("producto", new Producto());
        return "pages/productos";
    }

    @GetMapping("/consultar/{id}")
    public String consultarProducto(@PathVariable Integer id, Model model) {
        Optional<Producto> producto = repositorioProducto.findById(id);
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            return "pages/productos";
        } else {
            return "redirect:/productos";
        }
    }

    @PostMapping("/crear")
    public String crearProducto(@ModelAttribute Producto producto,
            RedirectAttributes redirectAttributes) {
        producto.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
        repositorioProducto.save(producto);
        redirectAttributes.addFlashAttribute("mensaje", "Producto creado exitosamente");
        return "redirect:/productos";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarProducto(@PathVariable Integer id,
            @ModelAttribute Producto detalleProducto,
            RedirectAttributes redirectAttributes) {
        Optional<Producto> producto = repositorioProducto.findById(id);
        if (producto.isPresent()) {
            Producto prod = producto.get();
            prod.setNombreProducto(detalleProducto.getNombreProducto());
            prod.setCantidadInventario(detalleProducto.getCantidadInventario());
            prod.setValor(detalleProducto.getValor());
            prod.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
            repositorioProducto.save(prod);
            redirectAttributes.addFlashAttribute("mensaje", "Producto actualizado exitosamente");
        }
        return "redirect:/productos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id,
            RedirectAttributes redirectAttributes) {
        if (repositorioProducto.existsById(id)) {
            Optional<Producto> productoOpt = repositorioProducto.findById(id);
            if (productoOpt.isPresent()) {
                Producto producto = productoOpt.get();
                // Verificar si el producto está relacionado con pedidos
                if (repositorioPedidoProducto.existsByProducto(producto)) {
                    redirectAttributes.addFlashAttribute("errorEliminacion", "true");
                    redirectAttributes.addFlashAttribute("mensajeError",
                        "No se puede eliminar el producto porque está relacionado con uno o más pedidos");
                } else {
                    repositorioProducto.deleteById(id);
                    redirectAttributes.addFlashAttribute("mensaje", "Producto eliminado exitosamente");
                }
            }
        }
        return "redirect:/productos";
    }
}