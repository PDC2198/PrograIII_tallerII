package com.itsqmet.Mascotas.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorControlador {

    @GetMapping("/error")
    public String accesoDenegado() {
        return "error";
    }
}
