package com.project.inventory.inventory.servicios;

import java.util.List;

import com.project.inventory.inventory.clases.RecuperarContrasena;
import com.project.inventory.inventory.clases.TipoDocumento;
import com.project.inventory.inventory.clases.TipoEstado;
import com.project.inventory.inventory.clases.TipoRol;
import com.project.inventory.inventory.clases.Usuario;

public interface  UsuarioServicios {
    Usuario validarUsuario(String documento_usuario, String contrasena);
    Usuario buscarUsuario(String codigo_usuario);
    List<Usuario> listaUsuario();
    boolean registrarUsuario(Usuario usuario);
    boolean eliminarUsuario(String codigo_usuario);
    List<TipoDocumento> tipoDocumento();
    List<TipoEstado> tipoEstados();
    List<TipoRol> tipoRoles();
    boolean actualizarUsuario(Usuario usuario);
    Usuario buscarDocumento(String documento);
    boolean solicitudRecovery(String codigo_usuario);
    List<RecuperarContrasena> listarRecuperarContrasenas();

}