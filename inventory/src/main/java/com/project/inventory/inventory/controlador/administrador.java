package com.project.inventory.inventory.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/administrador")
public class administrador {
    @GetMapping("/dashboard")
    public String dashboardAdministrador() {
        
        return "/administrador/dashboard";
    }
    
}
