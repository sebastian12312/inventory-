package com.project.inventory.inventory.servicios;

import com.project.inventory.inventory.clases.Usuario;

public interface  UsuarioServicios {
    Usuario validarUsuario(String documento_usuario, String contrasena);
}
