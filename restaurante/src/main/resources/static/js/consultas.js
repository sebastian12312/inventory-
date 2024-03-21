
// buscar empleado
var valorEdit = document.querySelectorAll(".valorEdit");
var rol =document.getElementById("rol");
var tipo_id =document.getElementById("tipo_id");
var id_empleado =document.getElementById("id_empleado");
var nombre_empleado =document.getElementById("nombre_empleado");
var apellido_empleado =document.getElementById("apellido_empleado");
var correo_empleado =document.getElementById("correo_empleado");
var telefono_empleado =document.getElementById("telefono_empleado");
var direccion_empleado =document.getElementById("direccion_empleado");
var password_empleado =document.getElementById("password_empleado");
var id_estado =document.getElementById("id_estado");
var id_establecimiento =document.getElementById("id_establecimiento");
function abrirModal() {
    const modal = new bootstrap.Modal(document.getElementById('EditarEmpleado'));
    modal.show();
}
valorEdit.forEach((e) =>{
    e.addEventListener("click", async ()=>{
        e.setAttribute("data-bs-toggle", "modal");
        e.setAttribute("data-bs-target", "#EditarEmpleado");
        const idEmpleado = e.getAttribute("data-empleado")
        const consulta = await fetch('/api/controlador/buscar/empleado/'+ idEmpleado, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
       });
       const respuestaEmpleado = await consulta.json();
       id_empleado.value = respuestaEmpleado.id_empleado
       nombre_empleado.value = respuestaEmpleado.nombre_empleado
       apellido_empleado.value = respuestaEmpleado.apellido_empleado      
       correo_empleado.value = respuestaEmpleado.correo_empleado
       rol.value = respuestaEmpleado.rol
       tipo_id.value = respuestaEmpleado.tipo_id
       telefono_empleado.value = respuestaEmpleado.telefono_empleado
       password_empleado.value = respuestaEmpleado.password_empleado
       direccion_empleado.value = respuestaEmpleado.direccion_empleado
       id_estado.value = respuestaEmpleado.id_estado
       id_establecimiento.value = respuestaEmpleado.id_establecimiento
       abrirModal();
    });
})
