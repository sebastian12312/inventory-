package com.proyecto.web.restaurante.clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "clientes")
@Entity
public class clientes{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_cliente;
    private int id_tipo;
    private String nombre_cliente;
    private String apellido_cliente;
    private String direccion_cliente;
    private String telefono_cliente;
    private String id_rol;
    private String pass_cliente;
}
