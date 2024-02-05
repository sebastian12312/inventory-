package com.project.inventory.inventory.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.inventory.inventory.clases.Usuario;
@Repository
public interface UsuarioRepositorio  extends JpaRepository<Usuario,String>{
    @Query("SELECT u FROM Usuario u WHERE u.documento_usuario = :documento_usuario and u.contrasena = :contrasena")
    Usuario findValidar(@Param("documento_usuario") String documento_usuario, @Param("contrasena") String contrasena);
}
