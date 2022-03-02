$(document).ready(function () {

});

function cambiarABuscarAportacion() {
    $('#opcionBuscar').text('Cambiar a buscar Aportación');
    $('#buscadorUsuario').css('display', 'none');
    $('#botonBuscarUsuario').css('display', 'none');
    $('#buscadorAportacion').css('display', 'block');
    $('#botonBuscarAportacion').css('display', 'block');

}