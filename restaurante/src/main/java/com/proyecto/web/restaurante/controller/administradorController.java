package com.proyecto.web.restaurante.controller;

import com.proyecto.web.restaurante.clases.Empleados;
import com.proyecto.web.restaurante.clases.clientes;
import com.proyecto.web.restaurante.implement.ClienteImplement;
import com.proyecto.web.restaurante.implement.UsuarioImplement;

import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/administrador")
public class administradorController {

    @Autowired
    private UsuarioImplement usuarioImplement;

    // administrador controller empleado
    // MAIN DASHBOARD
    @GetMapping("/dashboard")
    public String dashboardAdministrador(HttpSession session) {
        String nombreEmpleado = (String) session.getAttribute("nombreEmpleado");
        String codigoEmpleado = (String) session.getAttribute("codigoEmpleado");
        session.setAttribute("nombreEmpleado", nombreEmpleado);
        session.setAttribute("codigoEmpleado", codigoEmpleado);
        return "administrador/dashboard";
    }

    // lista empleados
    @GetMapping("/empleados")
    public String empleados(Model model) {
        List<Empleados> empleados = usuarioImplement.listaEmpleados();
        List<Empleados> empleadosActivos = usuarioImplement.cantidadEmpleadosActivos();
        model.addAttribute("listaEmpleados", empleados);

        //cantidad de usuarios
        model.addAttribute("cantidadEmpleados", empleados.size());

        //cantidad de empleados activos
        model.addAttribute("empleadosActivos", empleadosActivos.size());

        return "administrador/empleados";
    }
    // eliminar empleado
    @GetMapping("/empleados/delete/{id_empleado}")
    public String eliminarEmpleado(@PathVariable String id_empleado, RedirectAttributes redirectAttributes) {
        System.out.println("id empleado" + id_empleado);

        try {
            boolean rs = usuarioImplement.eliminarEmpleado(id_empleado);
            if (rs == true) {
                redirectAttributes.addFlashAttribute("mensajeOk", "¡El Empleado se ha eliminado satisfactoriamente!");
            } else {
                redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido eliminar!");
            }
            return "redirect:/administrador/empleados";
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido eliminar!");
            return "redirect:/administrador/empleados";
        }

    }
    // agregar empleado
    @PostMapping("/empleados/agregar")
    public String agregarEmpleado(RedirectAttributes redirectAttributes, @RequestParam String id_empleado,
            @RequestParam int tipo_id, @RequestParam String nombre_empleado, @RequestParam String apellido_empleado,
            @RequestParam String telefono_empleado, @RequestParam String direccion_empleado, @RequestParam int rol,
            @RequestParam int id_establecimiento,
            @RequestParam String password_empleado, @RequestParam String correo_empleado, @RequestParam int id_estado) {
        boolean respuesta = usuarioImplement.nuevoEmpleado(id_empleado, tipo_id, nombre_empleado, apellido_empleado,
                telefono_empleado, direccion_empleado, rol, id_establecimiento, password_empleado, correo_empleado,
                id_estado);
        try {
            if (respuesta == true) {
                redirectAttributes.addFlashAttribute("mensajeOk", "¡El Empleado se ha registrado satisfactoriamente!");
            } else {
                redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido guardar!");
            }
            return "redirect:/administrador/empleados";
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido guardar!");
            return "redirect:/administrador/empleados";
        }
    }
    // actualizar emplado
    @PostMapping("/empleado/actualizar")
    public String editarEmpleado(RedirectAttributes redirectAttributes, @RequestParam String id_empleado,
            @RequestParam int tipo_id, @RequestParam String nombre_empleado, @RequestParam String apellido_empleado,
            @RequestParam String telefono_empleado, @RequestParam String direccion_empleado, @RequestParam int rol,
            @RequestParam int id_establecimiento,
            @RequestParam String password_empleado, @RequestParam String correo_empleado, @RequestParam int id_estado) {
        try {
            boolean resultado = usuarioImplement.editarEmpleado(id_empleado, tipo_id, nombre_empleado,
                    apellido_empleado, telefono_empleado, direccion_empleado, rol, id_establecimiento,
                    password_empleado, correo_empleado, id_estado);
            System.out.println("ressputa" + resultado);
            if (resultado == true) {
                redirectAttributes.addFlashAttribute("mensajeOk", "¡El Empleado se ha actualizado satisfactoriamente!");
            } else {
                redirectAttributes.addFlashAttribute("mensajeError",
                        "¡Ha ocurrido un error y no se podido actualizar!");
            }
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido actualizar!");
        }
        return "redirect:/administrador/empleados";
    }

    // ADMINISTRADOR CONTROLLER CLIENTES

    @Autowired
    private ClienteImplement clienteImplement;
    @GetMapping("/clientes")
    public String clientes(Model model){
        List<clientes> listaCliente = clienteImplement.listaClientes();
        model.addAttribute("listaCliente", listaCliente);
        return "administrador/clientes";
    }


    // //ADMINISTRADOR CONTROLLER MENUS
    // @GetMapping("/menus")
    // public String menus(){

    //     return "administrador/menus";
    // }


    // //ADMINISTRADOR CONTROLLER RESERVACIONES
    // @GetMapping("/reservaciones")
    // public String reservaciones(){

    //     return "administrador/reservaciones";
    // }


    // //ADMINISTRADOR CONTROLLER MESAS
    // @GetMapping("/mesas")
    // public String mesas(){

    //     return "administrador/mesas";
    // }

    // //ADMINISTREADOR CONTROLLER VENTAS
    // @GetMapping("/ventas")
    // public String ventas(){

    //     return "administrador/ventas";
    // }


    // //ADMINISTRADOR CONTRLLER ESTABLECIMIENTO
    // @GetMapping("/establecimientos")
    // public String establecimientos(){

    //     return "administrador/establecimientos";
    // }

}
