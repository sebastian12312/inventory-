package com.project.inventory.inventory.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.inventory.inventory.clases.TipoEstado;

@Repository
public interface TipoEstadoRepository extends JpaRepository<TipoEstado, Integer>{
    
}
