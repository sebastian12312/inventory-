package com.proyecto.web.restaurante.service;

import com.proyecto.web.restaurante.clases.Empleados;

import java.util.List;

public interface UsuarioService {
   Empleados autenticacion(String id_empleado,String password_empleado);
   List<Empleados> listaEmpleados();
   boolean eliminarEmpleado(String id_empleado);
   boolean nuevoEmpleado(String id_empleado, int tipo_id, String nombre_empleado,String apellido_empleado,String telefono_empleado, String direccion_empleado, int rol, int id_establecimiento, String password_empleado, String correo_empleado, int id_estado);
   Empleados buscarEmpleado(String id_empleado);
   boolean editarEmpleado(String id_empleado, int tipo_id, String nombre_empleado,String apellido_empleado,String telefono_empleado, String direccion_empleado, int rol, int id_establecimiento, String password_empleado, String correo_empleado, int id_estado);
   List<Empleados> cantidadEmpleadosActivos();
}
