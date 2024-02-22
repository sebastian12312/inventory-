package com.project.inventory.inventory.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.inventory.inventory.clases.RecuperarContrasena;
import com.project.inventory.inventory.clases.TipoDocumento;
import com.project.inventory.inventory.clases.TipoEstado;
import com.project.inventory.inventory.clases.TipoRol;
import com.project.inventory.inventory.clases.Usuario;
import com.project.inventory.inventory.implementacion.GeneradorDeCodigos;
import com.project.inventory.inventory.implementacion.UsuarioImplement;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/administrador")
public class administrador {

    @Autowired
    private UsuarioImplement usuarioImplement;

    @GetMapping("/dashboard")
    public String dashboardAdministrador(HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("user");
        if (user != null) {
            String[] nombre_usuario = user.getNombre().split(" ");
            String primerNombre = (nombre_usuario.length > 0) ? nombre_usuario[0] : "";
            session.setAttribute("NombreUsuario", primerNombre);
            session.setAttribute("CodigoUsuario", user.getCodigo_usuario());

        } else {
            session.invalidate();
        }
        return "/administrador/dashboard";
    }

    @GetMapping("/usuarios")
    public String listaUsuario(Model model) {
        List<Usuario> listaUsuario = usuarioImplement.listaUsuario();
        List<TipoDocumento> listaDocumentos = usuarioImplement.tipoDocumento();
        List<TipoEstado> listaEstados = usuarioImplement.tipoEstados();
        List<TipoRol> listaRoles = usuarioImplement.tipoRoles();
        model.addAttribute("cantidadlistaUsuario", listaUsuario.size());
        model.addAttribute("listaRoles", listaRoles);
        model.addAttribute("listaDocumentos", listaDocumentos);
        model.addAttribute("listaEstados", listaEstados);
        model.addAttribute("listaUsuario", listaUsuario);
        return "/administrador/usuarios";
    }

    @GetMapping("/usuarios/eliminar/{codigo_usuario}")
    public String eliminarUsuario(@PathVariable("codigo_usuario") String codigo_usuario,
            RedirectAttributes redirectAttributes) {
        boolean respuesta = usuarioImplement.eliminarUsuario(codigo_usuario);
        if (respuesta) {
            redirectAttributes.addFlashAttribute("mensajeOk", "¡El Empleado se ha Eliminado satisfactoriamente!");
        } else {
            redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un Error y no se podido eliminar!");
        }
        return "redirect:/administrador/usuarios";
    }

    @PostMapping("/usuarios/agregar")
    public String registrarUsuario(RedirectAttributes redirectAttributes,
            @RequestParam(required = true) int id_documento,
            @RequestParam(required = true) String documento_usuario, @RequestParam(required = true) String nombre,
            @RequestParam(required = true) String apellido_paterno,
            @RequestParam(required = true) String apellido_materno, @RequestParam(required = true) String telefono,
            @RequestParam(required = true) String correo, @RequestParam(required = true) String contrasena,
            @RequestParam(required = true) int id_estado, @RequestParam(required = true) int id_rol) {
        GeneradorDeCodigos codigos = new GeneradorDeCodigos();
        String codigo_usuario = codigos.GeneradorDeCodigoUsuarios();
        try {
            if (documento_usuario.isEmpty() || nombre.isEmpty() || apellido_paterno.isEmpty()
                    || apellido_materno.isEmpty()
                    || telefono.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                redirectAttributes.addFlashAttribute("mensajeError",
                        "¡Ha ocurrido un Error y no se podido Registrar!. Intente Mas Tarde!");
            } else {
                Usuario usuario = new Usuario();
                usuario.setCodigo_usuario(codigo_usuario);
                usuario.setId_documento(id_documento);
                usuario.setDocumento_usuario(documento_usuario);
                usuario.setNombre(nombre);
                usuario.setApellido_paterno(apellido_paterno);
                usuario.setApellido_materno(apellido_materno);
                usuario.setCorreo(correo);
                usuario.setTelefono(telefono);
                usuario.setContrasena(contrasena);
                usuario.setId_estado(id_estado);
                usuario.setId_rol(id_rol);
                boolean respuesta = usuarioImplement.registrarUsuario(usuario);
                if (respuesta) {
                    redirectAttributes.addFlashAttribute("mensajeOk",
                            "¡El Usuario se ha Registrado satisfactoriamente!");
                } else {
                    redirectAttributes.addFlashAttribute("mensajeError",
                            "¡Ha ocurrido un Error y no se podido Registrar!. Intente Mas Tarde!");
                }
            }
            return "redirect:/administrador/usuarios";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError",
                    "¡Ha ocurrido un Error y no se podido Registrar!. Intente Mas Tarde!");
            return "redirect:/administrador/usuarios";
        }

    }

    @GetMapping("/usuarios/editar/{codigo_usuario}")
    public String editarUsuario(@PathVariable("codigo_usuario") String codigo_usuario,
            RedirectAttributes redirectAttributes, Model model) {
        Usuario user = usuarioImplement.buscarUsuario(codigo_usuario);
        model.addAttribute("usuarioBuscado", user);
        List<TipoDocumento> listaDocumentos = usuarioImplement.tipoDocumento();
        List<TipoEstado> listaEstados = usuarioImplement.tipoEstados();
        List<TipoRol> listaRoles = usuarioImplement.tipoRoles();
        model.addAttribute("listaRoles", listaRoles);
        model.addAttribute("listaDocumentos", listaDocumentos);
        model.addAttribute("listaEstados", listaEstados);
        return "administrador/editarUsuario";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(RedirectAttributes redirectAttributes,
            @RequestParam(required = true) String codigo_usuario, @RequestParam(required = true) int id_documento,
            @RequestParam(required = true) String documento_usuario, @RequestParam(required = true) String nombre,
            @RequestParam(required = true) String apellido_paterno,
            @RequestParam(required = true) String apellido_materno, @RequestParam(required = true) String telefono,
            @RequestParam(required = true) String correo, @RequestParam(required = true) String contrasena,
            @RequestParam(required = true) int id_estado, @RequestParam(required = true) int id_rol) {

        try {
            if (documento_usuario.isEmpty() || nombre.isEmpty() || apellido_paterno.isEmpty()
                    || apellido_materno.isEmpty()
                    || telefono.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                redirectAttributes.addFlashAttribute("mensajeError",
                        "¡Ha ocurrido un Error y no se podido Registrar!. Intente Mas Tarde!");
            } else {
                        Usuario usuario = new Usuario();
                        usuario.setCodigo_usuario(codigo_usuario);
                        usuario.setId_documento(id_documento);
                        usuario.setDocumento_usuario(documento_usuario);
                        usuario.setNombre(nombre);
                        usuario.setApellido_paterno(apellido_paterno);
                        usuario.setApellido_materno(apellido_materno);
                        usuario.setCorreo(correo);
                        usuario.setTelefono(telefono);
                        usuario.setContrasena(contrasena);
                        usuario.setId_estado(id_estado);
                        usuario.setId_rol(id_rol);
                        boolean respuesta = usuarioImplement.actualizarUsuario(usuario);

                if (respuesta) {
                    redirectAttributes.addFlashAttribute("mensajeOk",
                            "¡El Usuario se ha Actualizado satisfactoriamente!");
                } else {
                    redirectAttributes.addFlashAttribute("mensajeError",
                            "¡Ha ocurrido un Error y no se podido Registrar!. Intente Mas Tarde!");
                }
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensajeError",
                    "¡Ha ocurrido un Error y no se podido Registrar!. Intente Mas Tarde!");
        }
        return "redirect:/administrador/usuarios";
    }

    @GetMapping("/modifypassword")
    public String modifypassword(Model model) {
        List<RecuperarContrasena> lista = usuarioImplement.listarRecuperarContrasenas();
        model.addAttribute("listaRecover", lista);
        return "/administrador/modifypassword";
    }
    @PostMapping("/resetpassword")
    public String reset(@RequestParam String password, @RequestParam String documento_usuario){
        Usuario user = usuarioImplement.buscarDocumento(documento_usuario);
        if(user.getCodigo_usuario() != null || user.getCodigo_usuario() != ""){
                
        }else{
            System.out.println("error");
        }
        return "rediect:/administrador/modifypassword";
    }


}
