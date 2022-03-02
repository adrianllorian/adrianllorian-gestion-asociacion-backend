$(document).ready(function () {
    botonCambiar()
});
var control = false;

function cambiarABuscarAportacion() {
    $('.opcionBuscarAportacion').css('display, none');
    $('.opcionBuscarUsuario').css('display', 'block');
    $('#buscadorUsuario').css('display', 'none');
    $('#botonBuscarUsuario').css('display', 'none');
    $('#buscadorAportacion').css('display', 'block');
    $('#botonBuscarAportacion').css('display', 'block');

}

function cambiarABuscarUsuario() {
    $('.opcionBuscarUsuario').css('display', 'none');
    $('.opcionBuscarAportacion').css('display, block'); 
    $('#buscadorUsuario').css('display', 'block');
    $('#botonBuscarUsuario').css('display', 'block');
    $('#buscadorAportacion').css('display', 'none');
    $('#botonBuscarAportacion').css('display', 'none');
}

function botonCambiar(){
    $('#botonOpcion').click(function(){
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