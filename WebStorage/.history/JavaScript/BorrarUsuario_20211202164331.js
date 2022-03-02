$(document).ready(function () {
    mostrarMensaje();
});

function mostrarMensaje(){
    $('a[id^="borrarUsuario"]').click(function () {
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
        var json={"id":idUsuario}
        $.ajax({
            url: "http://localhost:3000/verAportacion",
            type: "POST",
            data: JSON.stringify(datas),
            contentType: "application/json; charset=UTF-8",
            headers: {
              "Authorization": localStorage.getItem('token')
            }
          }).done(function (data, textStatus, jqXHR) {
            if (data != null) {
              $.each(data, function (i, item) {
                console.log("Aportacion es" + data[i].estado)
                $('#cuerpo-anotacion').append('<tr><td>Fecha</td><td>'+data[i].descripcion +'</td><td id="aportacion"><a class="aportacionTabla">'+ data[i].aportacion +'</a><a class="aportacionTabla">€</a></td></tr>');
              });
            }
        
          })
            .fail(function (jqXHR, textStatus, errorThrown) {
              console.log("La solicitud a fallado: " + textStatus + ' son cosas que pasan');
        
            });
    });

}

