var checkbox_menu_lateral = document.getElementById("checkbox_menu_lateral");
var menu_lateral = document.getElementById("menu_lateral");

checkbox_menu_lateral.addEventListener("click", () => {
    if (checkbox_menu_lateral.checked) {
        // Checkbox marcado, cerrar el menú
        menu_lateral.classList.add("CerrarMenuLateral");
        menu_lateral.classList.remove("AbrirMenuLateral");
    } else {
        // Checkbox no marcado, abrir el menú
        menu_lateral.classList.add("AbrirMenuLateral");
        menu_lateral.classList.remove("CerrarMenuLateral");
    }
});
function ajustarMenuSegunAnchoVentana() {
    if (window.innerWidth < 800) {
        // Establecer el estado inicial del menú si el ancho de la ventana es menor a 800px
        menu_lateral.classList.add("CerrarMenuLateral");
        menu_lateral.classList.remove("AbrirMenuLateral");

        // Marcar el checkbox
        checkbox_menu_lateral.checked = true;
    } else {
        // Restaurar el estado inicial si el ancho de la ventana es mayor o igual a 800px
        menu_lateral.classList.remove("CerrarMenuLateral");
        menu_lateral.classList.add("AbrirMenuLateral");

        // Desmarcar el checkbox
        checkbox_menu_lateral.checked = false;
    }
}
// Verificar el tamaño de la pantalla al cargar la página
ajustarMenuSegunAnchoVentana();
// Verificar el tamaño de la pantalla al cambiar su tamaño
window.addEventListener("resize", ajustarMenuSegunAnchoVentana);
