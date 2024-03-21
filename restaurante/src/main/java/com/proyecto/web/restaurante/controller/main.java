package com.proyecto.web.restaurante.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class main {

    @GetMapping("/")
    public String web(){

        return "web/index";
    }
}
