package com.project.inventory.inventory.clases;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
 @Entity
 @Table(name="tipoestado")
public class TipoEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_estado;
    private String nombre_estado;
}
