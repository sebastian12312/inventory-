package com.proyecto.web.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.web.restaurante.clases.clientes;

public interface ClienteRepository extends JpaRepository<clientes,String> {
    
}
