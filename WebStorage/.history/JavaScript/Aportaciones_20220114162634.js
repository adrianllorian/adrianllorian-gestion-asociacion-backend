$(document).ready(function () {
    //##########################
// APORTACIONES
//##########################

function AportacionSumar() {    //Private
    $('button[id^="Aportacion"]').click(function () {
      var preparandoClave = $(this).attr('id').split("Aportacion");
  
      //Prepato el envio de la aportacion
      var idUsuario = preparandoClave[1]; //id Usuario
      var aportacion = $('#pantallaAportacion' + idUsuario).val();
      var descripcion = $('#pantallaConcepto' + idUsuario).val();
      var estado = "Aportacion";
      var data = {
        'idUsuario': idUsuario,
        'descripcion': descripcion,
        'aportacion': aportacion,
        'estado': estado
      };
      if (aportacion > 0) {
        enviarAportacion(data, idUsuario, estado, aportacion);
      }
  
    });
  }
  
  function AportacionRestar() {  //Pivate
    $('button[id^="Consumicion"]').click(function () {
      var preparandoClave = $(this).attr('id').split("Consumicion");
      
      //Preparo el envio de la Aportacion
      var idUsuario = preparandoClave[1]; //id Usuario
      var aportacion = $('#pantallaAportacion' + idUsuario).val();
      var descripcion = $('#pantallaConcepto' + idUsuario).val();
      var estado = "Consumicion";
      var data = {
        'idUsuario': idUsuario,
        'descripcion': descripcion,
        'aportacion': aportacion,
        'estado': estado
      };
      if (aportacion > 0) {
       
  
          console.log(descripcion);
          $('#pantallaConcepto' + idUsuario).css('border-color', 'rgb(205,212,219)');
          enviarAportacion(data, idUsuario, estado, aportacion);
        
        
        
  
      } else {
        alert('Debes de poner una cantidad mayor a 0')
      }
  
    });
  }
  
  function enviarAportacion(data, idUsuario, estado, aportacion) {
  
    $.ajax({
      url: "http://localhost:4000/authentication/guardarAportacion",
      type: "POST",
      data: JSON.stringify(data),
      contentType: "application/json; charset=UTF-8",
      headers: {
        "Authorization": localStorage.getItem('token')
      }
    }).done(function (data, textStatus, jqXHR) {
      if (data != null) {
        if (data == true) {
          $('#pantallaAportacion' + idUsuario).val('');
          $('#pantallaConcepto' + idUsuario).val('');
          var valor = $('#dinero' + idUsuario).text();
  
          if (estado == 'Aportacion') {
  
            $('#dinero' + idUsuario).text(parseFloat(valor) + parseFloat(aportacion));
          }
          else if (estado == 'Consumicion') {
            $('#dinero' + idUsuario).text(parseFloat(valor) - parseFloat(aportacion));
          }
  
        }
        else if (data == false) {
          alert("La " + estado + " no ha sido guardado")
        }
        else {
          alert("La " + estado + " no ha sido guardado")
        }
  
      }
  
    })
      .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("La solicitud a fallado: " + textStatus + ' son cosas que pasan');
  
      });
  }
});