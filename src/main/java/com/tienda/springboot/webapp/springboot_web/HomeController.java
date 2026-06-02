package com.tienda.springboot.webapp.springboot_web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/productos")
    public String productos() {
        return "pages/productos";
    }

    @GetMapping("/pedidos")
    public String pedidos() {
        return "pages/pedidos";
    }

    @GetMapping("/clientes")
    public String clientes() {
        return "pages/clientes";
    }

    @GetMapping("/empleados")
    public String empleados() {
        return "pages/empleados";
    }
}
