var documento_usuario_register = document.getElementById("documento_usuario_register");
var documento_label_register = document.getElementById("documento_label_register");
documento_usuario_register.addEventListener('input', function() {
    var valor = documento_usuario_register.value;
    if (valor.length < 15) {
              // Aquí podrías agregar más lógica, como deshabilitar el botón de envío o cambiar el estilo del campo
              documento_usuario_register.classList.remove("errorInput");
              documento_label_register.innerText = ' ';
    }else{
         // Si la longitud del valor ingresado es mayor a 15, muestra un mensaje de error
         console.error("Error: El documento no puede tener más de 15 caracteres.");
         documento_usuario_register.classList.add("errorInput");
         documento_label_register.innerText = '!error de documento!';

    }
});


/**
 ELIMINAR ITEM
 */
 var elimarItem = document.querySelectorAll(".eliminar-item");
 for(let item of elimarItem){
    item.addEventListener("click",(e)=>{
     console.log(item.id)
     const swalWithBootstrapButtons = Swal.mixin({
         
         customClass: {
           confirmButton: "btn-aceptar",
           cancelButton: "btn-eliminar"
         },
         buttonsStyling: false
       });
       swalWithBootstrapButtons.fire({
         title: "Estas Seguro?",
         text: "No podrás revertir esto.!",
         icon: "warning",
         showCancelButton: true,
         confirmButtonText: "Si, eliminar!", 
         cancelButtonText: "No, cancelar!",
         
       }).then((result) => {
         if (result.isConfirmed) {
           swalWithBootstrapButtons.fire({
             title: "Eliminado!",
             text: "Tu archivo ha sido eliminado.",
             icon: "success"
           });
         } else if (
           /* Read more about handling dismissals below */
           result.dismiss === Swal.DismissReason.cancel
         ) {
           swalWithBootstrapButtons.fire({
             title: "Cancelado",
             text: "Tu archivo imaginario está a salvo :)",
             icon: "error"
           });
         }
       });
    })
 }


 var AgregarItem = document.querySelectorAll(".agregar-item");
for(Agregar of AgregarItem){
    Agregar.addEventListener("click", (e)=>{
        const Toast = Swal.mixin({
            toast: true,
            position: "top-end",
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.onmouseenter = Swal.stopTimer;
              toast.onmouseleave = Swal.resumeTimer;
            }
          });
          Toast.fire({
            icon: "success",
            title: "Registro Completado!"
          });
    }) 
}