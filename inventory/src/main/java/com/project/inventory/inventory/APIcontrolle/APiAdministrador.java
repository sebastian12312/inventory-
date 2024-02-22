package com.project.inventory.inventory.APIcontrolle;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.inventory.inventory.implementacion.GeneradorDeCodigos;
import com.project.inventory.inventory.implementacion.UsuarioImplement;

import jakarta.websocket.server.PathParam;

import com.project.inventory.inventory.clases.Usuario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/administrador")
public class APiAdministrador {
    
    @Autowired
    private UsuarioImplement usuarioImplement;

    @PostMapping("/buscar/usuario/{documento_usuario}")
    private Usuario usuario(@PathVariable("documento_usuario") String documento_usuario){
        Usuario usuario =usuarioImplement.buscarDocumento(documento_usuario);
        return usuario;
    }

    @GetMapping("/usuariosList")
    private List<Usuario> listaUsuario(){
        List<Usuario> usuario = usuarioImplement.listaUsuario();
        return usuario;
    }

    
}
