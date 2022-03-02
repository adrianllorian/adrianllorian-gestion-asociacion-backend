$(document).ready(function () {
    //borrarUsuario
});

function mostrarMensaje(){
    $('a[id^="borrarUsuario"]').click(function () {
        var preparandoClave = $(this).attr('id').split("verUsuario");
        var idUsuario = preparandoClave[1]; //id Usuario
        $('#modalConfirmarBorrar').modal({ backdrop: 'static', keyboard: false });
    });
}

function botonesModelBorrar(idUsuario){
    $('#cancelarBorrar').click(function(){
        $('#modalConfirmarBorrar').modal('toggle');
    });
    $('#confirmarBorrar').click(function(){
        
    });

}

