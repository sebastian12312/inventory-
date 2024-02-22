var modify_password = document.querySelectorAll('.modify_password');

for (let modify of modify_password) {
  modify.addEventListener('click', (e) => {
    var dataValue = modify.getAttribute("data-id");

    fetch('/api/administrador/usuariosList')
      .then(response => {
        if (!response.ok) {
          throw new Error('Hubo un problema al realizar la solicitud: ' + response.status);
        }
        return response.json(); // Convertir la respuesta a JSON
      })
      .then(data => {
        // Iterar sobre los datos recibidos
        data.forEach(element => {
          console.log(element.nombre); // Modificado para reflejar el campo correcto del usuario
          // Aquí puedes hacer lo que necesites con los datos, como mostrarlos en la interfaz de usuario
        });
      })
      .catch(error => {
        console.error('Error:', error);
        // Manejar errores de la solicitud
      });
  });
}
var usuarioDocument = document.querySelectorAll(".EditarUser")  
var documento = "";
usuarioDocument.forEach(usuario => {
  usuario.addEventListener('click', () => {
    let documento = usuario.getAttribute('data-document');
    fetch('/api/administrador/buscar/usuario/' + documento, {
      method: 'post',
      headers: {
        'Content-Type': 'application/json'
      }
    })
      .then(response => {
        if (!response.ok) {
          throw new Error('Hubo un problema al realizar la solicitud: ' + response.status);
        } else {
          return response.json(); // Convertir la respuesta a JSON
        }
      })
      .then(data => {
        $('#codigo_usuario_edit').val(data.codigo_usuario);
        $('#tipo_documento_Edit').val(data.id_documento);
        $('#documento_usuario_edit').val(data.documento_usuario)
        $('#nombre_usuario_edit').val(data.nombre)
        $('#apellido_paterno_edit').val(data.apellido_paterno)
        $('#apellido_materno_edit').val(data.apellido_materno)
        $('#telefono_edit').val(data.telefono)
        $('#correo_edit').val(data.correo)
        $('#id_rol_edit').val(data.id_rol)
        $('#id_rango_edit').val(data.id_rango)
        $('#contrasena_edit').val(data.contrasena)
        $('#id_estado_edit').val(data.id_estado)
      })
      .catch(error => {
        console.error('Error:', error);
      });
  });
});


function myFunction() {
  var input, filter, table, tr, td, i, j, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  // Recorre todas las filas de la tabla
  for (i = 0; i < tr.length; i++) {
    // Asegúrate de que estás trabajando solo con las filas del cuerpo de la tabla
    if (tr[i].parentNode.tagName.toLowerCase() === 'tbody') {
      // Recorre todas las celdas de cada fila
      for (j = 0; j < tr[i].cells.length; j++) {
        td = tr[i].cells[j];
        if (td) {
          txtValue = td.textContent || td.innerText;
          // Comprueba si el texto de la celda contiene el filtro
          if (txtValue.toUpperCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
            break; // Si encuentra coincidencia, detén el bucle interno
          } else {
            tr[i].style.display = "none";
          }
        }
      }
    }
  }
}