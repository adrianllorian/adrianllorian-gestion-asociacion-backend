$(document).ready(function () {

});
var control = true;

function cambiarABuscarAportacion() {
    $('#opcionBuscar').text('Cambiar a buscar Usuario');
    $('#buscadorUsuario').css('display', 'none');
    $('#botonBuscarUsuario').css('display', 'none');
    $('#buscadorAportacion').css('display', 'block');
    $('#botonBuscarAportacion').css('display', 'block');

}

function cambiarABuscarUsuario() {
    $('#opcionBuscar').text('Cambiar a buscar Aportacion');
    $('#buscadorUsuario').css('display', 'block');
    $('#botonBuscarUsuario').css('display', 'block');
    $('#buscadorAportacion').css('display', 'none');
    $('#botonBuscarAportacion').css('display', 'none');
}

function control(){
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