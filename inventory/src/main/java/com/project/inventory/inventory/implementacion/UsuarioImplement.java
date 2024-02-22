package com.project.inventory.inventory.implementacion;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.project.inventory.inventory.clases.RecuperarContrasena;
import com.project.inventory.inventory.clases.TipoDocumento;
import com.project.inventory.inventory.clases.TipoEstado;
import com.project.inventory.inventory.clases.TipoRol;
import com.project.inventory.inventory.clases.Usuario;
import com.project.inventory.inventory.repositorio.RecuperarContrasenaRepositorio;
import com.project.inventory.inventory.repositorio.TipoDocumentoRepository;
import com.project.inventory.inventory.repositorio.TipoEstadoRepository;
import com.project.inventory.inventory.repositorio.TipoRolesRepository;
import com.project.inventory.inventory.repositorio.UsuarioRepositorio;
import com.project.inventory.inventory.servicios.UsuarioServicios;

@Service
public class UsuarioImplement implements UsuarioServicios {
  GeneradorDeCodigos generadores = new GeneradorDeCodigos();
  @Autowired
  private UsuarioRepositorio usuarioRepositorio;

  // USUARIOS
  @Override
  public Usuario validarUsuario(String documento_usuario, String contrasena) {
    String contrasena_segura = generadores.passwordSecurity(contrasena);
    Usuario user = usuarioRepositorio.findValidar(documento_usuario, contrasena_segura);
    try {
      System.out.println("usuario" + documento_usuario + contrasena);
      if (user.getCodigo_usuario() != "" || user.getCodigo_usuario() != null) {
        return user;
      } else {
        return null;
      }
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public List<Usuario> listaUsuario() {
    List<Usuario> listaUsuario = usuarioRepositorio.findAll();
    return listaUsuario;
  }

  @Override
  public boolean eliminarUsuario(String codigo_usuario) {
    try {
      usuarioRepositorio.deleteById(codigo_usuario);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
//update user
  @Override
  public boolean actualizarUsuario(Usuario usuario) {    
    try {
      // Obtener el usuario de la base de datos por su código
      Optional<Usuario> optionalUsuario = usuarioRepositorio.findById(usuario.getCodigo_usuario());
      
      if (optionalUsuario.isPresent()) { // Verificar si el usuario existe en la base de datos
          Usuario usuarioExistente = optionalUsuario.get();
          usuarioExistente.setId_documento(usuario.getId_documento());
          usuarioExistente.setDocumento_usuario(usuario.getDocumento_usuario());
          usuarioExistente.setNombre(usuario.getNombre());
          usuarioExistente.setApellido_paterno(usuario.getApellido_paterno());
          usuarioExistente.setApellido_materno(usuario.getApellido_materno());
          usuarioExistente.setTelefono(usuario.getTelefono());
          usuarioExistente.setCorreo(usuario.getCorreo());
          usuarioExistente.setContrasena(usuario.getContrasena());
          usuarioExistente.setId_estado(usuario.getId_estado());
          usuarioExistente.setId_rol(usuario.getId_rol());
          
          usuarioRepositorio.save(usuarioExistente); // Guardar el usuario actualizado en la base de datos
          return true;
      } else {
          return false; // El usuario no existe en la base de datos
      }
  } catch (Exception e) {
      e.printStackTrace();
      return false; // Error al actualizar el usuario
  }
  }

  @Override
  public boolean registrarUsuario(Usuario usuario) {   
    try {
      String contrasena = usuario.getContrasena();
      String contrasenaCode = generadores.passwordSecurity(contrasena);
      usuario.setContrasena(contrasenaCode);
      usuarioRepositorio.save(usuario);
      String url = "C:/Users/SEBASTIAN/Documents/GitHub/inventory-/carpetasUsuario/" + usuario.getCodigo_usuario();
      File carpeta = new File(url);
      if (!carpeta.exists()) {
        if (carpeta.mkdirs()) {
          System.out.println("Carpeta Creada Exitosamente");
        } else {
          System.out.println("Ocurrio un problema no se pudo crear la carpeta");
        }
      } else {
        System.out.println("la carpeta ya existe");
      }
      return true;
    } catch (Exception e) {
      return false;
    }
   
  }

  // tipo documento
  @Autowired
  private TipoDocumentoRepository tipoDocumentoRepository;

  @Override
  public List<TipoDocumento> tipoDocumento() {
    List<TipoDocumento> listaDocumentos = tipoDocumentoRepository.findAll();
    return listaDocumentos;
  }

  @Autowired
  private TipoEstadoRepository tipoEstadoRepository;

  @Override
  public List<TipoEstado> tipoEstados() {
    List<TipoEstado> listaEstados = tipoEstadoRepository.findAll();
    return listaEstados;
  }

  @Autowired
  private TipoRolesRepository tipoRolesRepository;

  @Override
  public List<TipoRol> tipoRoles() {
    List<TipoRol> listaRoles = tipoRolesRepository.findAll();
    return listaRoles;
  }

  @Override
  public Usuario buscarUsuario(String codigo_usuario) {
    return usuarioRepositorio.findById(codigo_usuario).orElse(null);
  }

  @Override
  public Usuario buscarDocumento(String documento) {
      Usuario user = usuarioRepositorio.findBuscarNumeroDocumento(documento);
    return user;
  }

  @Autowired
  private RecuperarContrasenaRepositorio recuperarContrasenaRepositorio;
  @Override
  public boolean solicitudRecovery(String codigo_usuario) {
        RecuperarContrasena recuperarContrasena = new RecuperarContrasena();
        RecuperarContrasena recover=  recuperarContrasenaRepositorio.recoveryPass(codigo_usuario);
        if (recover == null || !"1".equals(recover.getEstado_consulta())) {
          // estado consulta: 1 = consulta pendiente | 2 = consulta resuelta | 3 = reseteo por admin
          recuperarContrasena.setId_recuperar(generadores.generadorPasswordRecover());
          recuperarContrasena.setCodigo_usuario(codigo_usuario);
          recuperarContrasena.setNueva_contrasena("automatico");
          recuperarContrasena.setEstado_consulta("1");
          recuperarContrasenaRepositorio.save(recuperarContrasena);
          return true;
      } else if (recover != null && "2".equals(recover.getEstado_consulta())) {
          recuperarContrasena.setId_recuperar(generadores.generadorPasswordRecover());
          recuperarContrasena.setCodigo_usuario(codigo_usuario);
          recuperarContrasena.setNueva_contrasena("automatico");
          recuperarContrasena.setEstado_consulta("1");
          recuperarContrasenaRepositorio.save(recuperarContrasena);
          return true;
      } else {
          return false;
      }
        
  }

  @Override
  public List<RecuperarContrasena> listarRecuperarContrasenas() {
   
    return recuperarContrasenaRepositorio.findAll();
  }

}
