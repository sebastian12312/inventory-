package com.project.inventory.inventory.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.inventory.inventory.clases.Usuario;
import com.project.inventory.inventory.implementacion.UsuarioImplement;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/autenticacion")
public class autenticacion {
    
     @Autowired
     private UsuarioImplement usuarioImplement;

    @GetMapping("/recoverPassword")
    private String recuperarPassword(){
        return "web/recoverPassword";
    }

    @PostMapping("/login")
    public String loginUsuario(RedirectAttributes redirectAttributes, HttpSession session, @RequestParam String numero_documento,@RequestParam  String password_usuario) {
        Usuario user = usuarioImplement.validarUsuario(numero_documento, password_usuario);
        
        try {
            System.out.println(user);
            if (user != null && user.getCodigo_usuario() != null) {
                return "redirect:/administrador/dashboard";
            } else {
                redirectAttributes.addFlashAttribute("mensajeError","El usuario NO EXISTE");
                return "redirect:/";                    
            }
        } catch (Exception e) {
            e.getMessage();
            redirectAttributes.addFlashAttribute("mensajeError","El usuario NO EXISTE");
            System.out.println("error");
            return "redirect:/";
        }
            
    }
    
}
