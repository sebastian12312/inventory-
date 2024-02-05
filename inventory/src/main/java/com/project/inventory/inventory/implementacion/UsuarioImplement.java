package com.project.inventory.inventory.implementacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.project.inventory.inventory.clases.Usuario;
import com.project.inventory.inventory.repositorio.UsuarioRepositorio;
import com.project.inventory.inventory.servicios.UsuarioServicios;


@Service
public class UsuarioImplement implements UsuarioServicios{

    @Autowired
     private UsuarioRepositorio usuarioRepositorio;

    @Override
     public Usuario validarUsuario(String documento_usuario, String contrasena) {
       Usuario user = usuarioRepositorio.findValidar(documento_usuario, contrasena);
        try {
          if (user.getCodigo_usuario() != "" || user.getCodigo_usuario() != null  ) {
            return user;
          }else{
            return null;
          }         
        } catch (Exception e) {
          return null;
        }
     }
    
}
