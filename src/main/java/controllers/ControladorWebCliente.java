package controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import entities.Cliente;
import repositories.RepositorioCliente;

@Controller
@RequestMapping("/cliente.html")
public class ControladorWebCliente {
    
    @Autowired
    private RepositorioCliente repositorioCliente;

    @GetMapping
    public String listarClientes(Model model) {
        List<Cliente> clientes = repositorioCliente.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    @GetMapping("/consultar/{id}")
    public String ConsultarCliente(@PathVariable Integer id, Model model) {
        Optional<Cliente> cliente = repositorioCliente.findById(id);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            return "cliente-detalle.html"; // Acá va la modal de modo edición
        } else {
            return "redirect:/cliente.html";
        }
    }

    @PostMapping("/crear")
    public String crearCliente(@ModelAttribute Cliente cliente, RedirectAttributes redirectAttributes) {
        repositorioCliente.save(cliente);
        redirectAttributes.addFlashAttribute("mensaje", "Cliente creado exitosamente");
        return "redirect:/cliente.html";
    }

    @PutMapping("/actualizar/{id}")
    public String actualizarCliente(@PathVariable Integer id, @ModelAttribute Cliente detalleCliente, RedirectAttributes redirectAttributes) {
        Optional<Cliente> cliente = repositorioCliente.findById(id);
        if (!cliente.isPresent()) {
            Cliente cli = cliente.get();
            cli.setNombres(detalleCliente.getNombres());
            cli.setApellidos(detalleCliente.getApellidos());
            cli.setTipoDocumento(detalleCliente.getTipoDocumento());
            cli.setNumeroDocumento(detalleCliente.getNumeroDocumento());
            cli.setSegmentoCliente(detalleCliente.getSegmentoCliente());
            cli.setFechaNacimiento(detalleCliente.getFechaNacimiento());
            repositorioCliente.save(cli);
            redirectAttributes.addFlashAttribute("mensaje", "Cliente actualizado exitosamente");
        }
        return "redirect:/cliente.html";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if(repositorioCliente.existsById(id)) {
            repositorioCliente.deleteById(id);
            redirectAttributes.addFlashAttribute("mensaje", "Cliente eliminado exitosamente");
        }
        return "redirect:/cliente.html";
    }
}
