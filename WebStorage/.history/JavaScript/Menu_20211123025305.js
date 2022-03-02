$(document).ready(function () {
    botonCambiar()
});
var control = false;

function cambiarABuscarAportacion() {
    $('.opcionBuscarAportacion').css('display, block');
    $('.opcionBuscarUsuario').css('display', 'none');
    $('#buscadorUsuario').css('display', 'none');
    $('#botonBuscarUsuario').css('display', 'none');
    $('#buscadorAportacion').css('display', 'block');
    $('#botonBuscarAportacion').css('display', 'block');

}

function cambiarABuscarUsuario() {
    $('.opcionBuscarUsuario').css('display', 'block');
    $('.opcionBuscarAportacion').css('display, none'); 
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