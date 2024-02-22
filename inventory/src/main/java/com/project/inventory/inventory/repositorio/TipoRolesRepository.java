package com.project.inventory.inventory.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.inventory.inventory.clases.TipoRol;

@Repository
public interface TipoRolesRepository extends JpaRepository<TipoRol, Integer>{
    
}
