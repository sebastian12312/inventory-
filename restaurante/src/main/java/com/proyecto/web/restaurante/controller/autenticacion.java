package com.proyecto.web.restaurante.controller;

import com.proyecto.web.restaurante.clases.Empleados;
import com.proyecto.web.restaurante.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class autenticacion {
    @Autowired
    private UsuarioService usuarioService;
    @PostMapping("/login")
    public String autenticacion(@RequestParam String id_empleado,@RequestParam String password_empleado, HttpSession session){
        Empleados empleado = usuarioService.autenticacion(id_empleado, password_empleado);

        try {
            session.setAttribute("nombreEmpleado", empleado.getNombre_empleado());
            session.setAttribute("codigoEmpleado", empleado.getId_empleado());
            if(empleado.getRol() != 0){
                if(empleado.getRol() == 1){
                    return "redirect:/administrador/dashboard";
                }else if(empleado.getRol() == 2){
                    return "redirect:/mozo/dashboard";
                }else if(empleado.getRol() == 3){
                    return "redirect:/cajero/dashboard";
                }else{
                    return "redirect:/";
                }
            }else{
                return "redirect:/";
            }

        }catch (Exception e){
            e.getMessage();
            System.out.println("error");
            return "redirect:/";
        }
    }
    @GetMapping("/logout")
    private String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
