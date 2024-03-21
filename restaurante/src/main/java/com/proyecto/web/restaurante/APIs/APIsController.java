package com.proyecto.web.restaurante.APIs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.web.restaurante.clases.Empleados;
import com.proyecto.web.restaurante.implement.UsuarioImplement;

@RestController
@RequestMapping("/api/controlador")
public class APIsController {
    
    @Autowired
    private UsuarioImplement usuarioImplement;
    @GetMapping("/buscar/empleado/{id_empleado}")
    private Empleados buscarEmpleado(@PathVariable("id_empleado") String id_empleado){
        System.out.println("id e,[;eadp " + id_empleado);
        Empleados buscarEmpleado = usuarioImplement.buscarEmpleado(id_empleado);
        return buscarEmpleado;
    }
}
