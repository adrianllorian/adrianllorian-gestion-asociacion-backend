$(document).ready(function () {
    botonCambiar()
});
var control = false;

function cambiarABuscarAportacion() {
    $('#opcionBuscar').text('Cambiar a Buscar Usuario');
    $('#buscadorUsuario').css('display', 'none');
    $('#botonBuscarUsuario').css('display', 'none');
    $('#buscadorAportacion').css('display', 'block');
    $('#botonBuscarAportacion').css('display', 'block');

}

function cambiarABuscarUsuario() {
    $('#opcionBuscar').text('Cambiar a Buscar Aportacion');
    $('#buscadorUsuario').css('display', 'block');
    $('#botonBuscarUsuario').css('display', 'block');
    $('#buscadorAportacion').css('display', 'none');
    $('#botonBuscarAportacion').css('display', 'none');
}

function botonCambiar(){
    $('#opcionBuscar').click(function(){
        if(control== true){
            cambiarABuscarUsuario()
            control= false;
        }
        else{
            cambiarABuscarAportacion()
            control= true;
        }
    })      
    
}