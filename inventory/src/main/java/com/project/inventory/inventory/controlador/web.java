package com.project.inventory.inventory.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class web {
    @GetMapping("/")
    public String web() {
        return "web/index";
    }
    
}
