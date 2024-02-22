package com.project.inventory.inventory.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.inventory.inventory.clases.RecuperarContrasena;
import com.project.inventory.inventory.clases.Usuario;

@Repository
public interface RecuperarContrasenaRepositorio extends JpaRepository<RecuperarContrasena,String>{
    @Query("select u from RecuperarContrasena u where u.codigo_usuario = :codigo_usuario")
    RecuperarContrasena recoveryPass(@Param("codigo_usuario") String codigo_usuario);

    
}
