package com.project.inventory.inventory.clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Data
@Table(name = "usuario")
public class Usuario {
    
    @Id
    private String codigo_usuario;
    
    private int id_documento;
    private String documento_usuario;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String correo;
    private String telefono;
    private String contrasena;
    private int id_estado;
    private int id_rol;
}
