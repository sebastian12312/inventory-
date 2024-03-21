package com.proyecto.web.restaurante.clases;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "empleados")
public class Empleados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id_empleado;

    private int tipo_id;
    private String nombre_empleado;
    private String apellido_empleado;
    private String telefono_empleado;
    private String direccion_empleado;
    private int rol;
    private int id_establecimiento;
    private String password_empleado;
    private String correo_empleado;
    private int id_estado;




}
