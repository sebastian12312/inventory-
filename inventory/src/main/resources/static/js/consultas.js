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