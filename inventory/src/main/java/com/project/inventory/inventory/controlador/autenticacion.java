package com.project.inventory.inventory.controlador;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.inventory.inventory.clases.Usuario;
import com.project.inventory.inventory.implementacion.GeneradorDeCodigos;
import com.project.inventory.inventory.implementacion.UsuarioImplement;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/autenticacion")
public class autenticacion {

    @Autowired
    private UsuarioImplement usuarioImplement;

    @GetMapping("/recoverPassword")
    private String recuperarPassword() {
        return "web/recoverPassword";
    }

    @PostMapping("/recuperar/contrasena")
    public String recuperarContrasena(RedirectAttributes redirectAttributes, @RequestParam String documento_usuario) {
        Usuario usuario = usuarioImplement.buscarDocumento(documento_usuario);
        try {
            if (usuario.getCodigo_usuario() != null) {
                boolean respuesta = usuarioImplement.solicitudRecovery(usuario.getCodigo_usuario());
                if (respuesta) {
                    redirectAttributes.addFlashAttribute("mensajeOk", "Envío Exitoso!");
                } else {
                    redirectAttributes.addFlashAttribute("mensajeError",
                            "El Documento de Usuario No Existe, Por Favor Ingrese Bien sus Datos");
                }
            } else {
                redirectAttributes.addFlashAttribute("mensajeError",
                        "El Documento de Usuario No Existe, Por Favor Ingrese Bien sus Datos");
            }
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("mensajeError",
                    "Error al acceder a la base de datos. Por favor, inténtelo de nuevo más tarde.");
            // Log the exception or do additional error handling if necessary
            e.printStackTrace();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError",
                    "Se ha producido un error. Por favor, inténtelo de nuevo más tarde.");
            // Log the exception or do additional error handling if necessary
            e.printStackTrace();
        }
        
        return "redirect:/autenticacion/recoverPassword";
    }

    @PostMapping("/login")
    public String loginUsuario(RedirectAttributes redirectAttributes, HttpSession session,
            @RequestParam String numero_documento, @RequestParam String password_usuario) {
        Usuario user = usuarioImplement.validarUsuario(numero_documento, password_usuario);

        try {
            if (user.getCodigo_usuario() != null || user.getCodigo_usuario() != "") {
                session.setAttribute("user", user);
                // ESTADO == 1 (ACTIVO)
                if (user.getId_estado() == 1) {
                    // ROL == ADMINISTRADOR
                    if (user.getId_rol() == 1) {

                        return "redirect:/administrador/dashboard";

                        // ROL == USUARIO TIPO 1
                    } else if (user.getId_rol() == 2) {
                        return "redirect:/usuario/dashboard";

                        // ROL == USUARIO TIPO 2
                    } else if (user.getId_rol() == 3) {
                        return "redirect:/inventario/dashboard";

                        // ROL == USUARIO TIPO NO EXISTE
                    } else {
                        session.invalidate();
                        redirectAttributes.addFlashAttribute("mensajeError",
                                "!Vaya Vaya!,El Rol del Usuario No Existe, comuniquese con el administrador.");
                        return "redirect:/";
                    }

                    // ESTADO == 2 (SUSPENDIDO)
                } else if (user.getId_estado() == 2) {
                    session.invalidate();
                    redirectAttributes.addFlashAttribute("mensajeError",
                            "!Vaya Vaya!,El Usuario Se encuentra SUSPENDIDO, comuniquese con el administrador.");
                    return "redirect:/";
                    // ESTADO == 3 (DESACTIVADO)
                } else if (user.getId_estado() == 3) {
                    session.invalidate();
                    redirectAttributes.addFlashAttribute("mensajeError",
                            "!Vaya Vaya!,El Usuario Se encuentra DESACTIVADO, comuniquese con el administrador.");
                    return "redirect:/";
                } else {
                    session.invalidate();
                    redirectAttributes.addFlashAttribute("mensajeError",
                            "!Vaya Vaya!, Ocurrio Un ERROR comuniquese con el administrador.");
                    return "redirect:/";
                }
            } else {
                redirectAttributes.addFlashAttribute("mensajeError", "El usuario NO EXISTE");
                return "redirect:/";
            }
        } catch (Exception e) {
            e.getMessage();
            System.out.println("usuario" + numero_documento + password_usuario);

            redirectAttributes.addFlashAttribute("mensajeError", "El usuario NO EXISTE");
            System.out.println("error catch jaa");
            return "redirect:/";
        }

    }

    @GetMapping("/logout")
    public String logoutUsuario(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
