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