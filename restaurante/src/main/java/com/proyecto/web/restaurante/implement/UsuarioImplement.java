package com.proyecto.web.restaurante.implement;


import com.proyecto.web.restaurante.clases.Empleados;
import com.proyecto.web.restaurante.repository.UsuarioRepository;
import com.proyecto.web.restaurante.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioImplement  implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    // CONTROLADO ADMINISTRADOR EMPLEADOS 
    //validacion
    @Override
    public Empleados autenticacion(String id_empleado, String password_empleado)
    {
        Empleados empleado = usuarioRepository.findValidar(id_empleado,password_empleado);
        return empleado;
    }


    //lista empleados
    @Override
    public List<Empleados> listaEmpleados() {
         List<Empleados> empleados = usuarioRepository.findAll();
        return empleados;
    }


    //eliminar empleado
    @Override
    public  boolean eliminarEmpleado( String id_empleado) {
        try{
            usuarioRepository.deleteBycodigo_usuario(id_empleado);
            return true;
        }catch(DataAccessException a){
            return false;
        }

    }

    // nuevo empleado
    @Override
    public boolean nuevoEmpleado(String id_empleado, int tipo_id, String nombre_empleado, String apellido_empleado,
            String telefono_empleado, String direccion_empleado, int rol, int id_establecimiento,
            String password_empleado, String correo_empleado, int id_estado) {
        try {
        usuarioRepository.insertarEmpleado(id_empleado,tipo_id,nombre_empleado,apellido_empleado,telefono_empleado,direccion_empleado,rol
                    ,id_establecimiento,password_empleado,correo_empleado,id_estado);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    // buscar empleado
    @Override
    public Empleados buscarEmpleado(String id_empleado) {       
        Empleados empleado = usuarioRepository.buscarEmpleado(id_empleado); 
        return empleado;
    }
    // editar empleado
    @Override
    public boolean editarEmpleado(String id_empleado, int tipo_id, String nombre_empleado, String apellido_empleado,
            String telefono_empleado, String direccion_empleado, int rol, int id_establecimiento,
            String password_empleado, String correo_empleado, int id_estado) {
            try {
                usuarioRepository.actualizarEmpleado(id_empleado, tipo_id, nombre_empleado, apellido_empleado, telefono_empleado, direccion_empleado, rol, id_establecimiento, password_empleado, correo_empleado, id_estado);
                return true;
            } catch (DataAccessException e) {
                return false;
            }                             
    }


    @Override
    public List<Empleados> cantidadEmpleadosActivos() {
       List<Empleados> cant = usuarioRepository.cantidadEmpleadosActivos(1);
        return cant;
    }

}
