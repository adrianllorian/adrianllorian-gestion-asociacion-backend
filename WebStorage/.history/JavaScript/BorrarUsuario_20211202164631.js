$(document).ready(function () {
    mostrarMensaje();
});

function mostrarMensaje(){
    $('a[id^="borrarUsuario"]').click(function () {
        console.log('Entra en el boton borrar')
        var preparandoClave = $(this).attr('id').split("verUsuario");
        var idUsuario = preparandoClave[1]; //id Usuario
        $('#modalConfirmarBorrar').modal({ backdrop: 'static', keyboard: false });
        botonesModelBorrar(idUsuario)
    });
}

function botonesModelBorrar(idUsuario){
    $('#cancelarBorrar').click(function(){
        $('#modalConfirmarBorrar').modal('toggle');
    });
    $('#confirmarBorrar').click(function(){
        var json={"id":idUsuario};
        $.ajax({
            url: "http://localhost:3000/borrar-socio",
            type: "POST",
            data: JSON.stringify(json),
            contentType: "application/json; charset=UTF-8",
            headers: {
              "Authorization": localStorage.getItem('token')
            }
          }).done(function (data, textStatus, jqXHR) {
            if (data != null) {
                $('#modalConfirmarBorrar').modal('toggle');
            }
            else{
                console.log("La solicitud no se ha confirmado: " + textStatus + ' son cosas que pasan');

            }
        
          })
            .fail(function (jqXHR, textStatus, errorThrown) {
              console.log("La solicitud a fallado: " + textStatus + ' son cosas que pasan');
        
            });
    });

}

