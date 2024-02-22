package com.project.inventory.inventory.clases;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "recuperarcontrasena")
public class RecuperarContrasena {
    @Id
    private String id_recuperar;

    private String codigo_usuario;
    private String nueva_contrasena;
    private String estado_consulta;
}
