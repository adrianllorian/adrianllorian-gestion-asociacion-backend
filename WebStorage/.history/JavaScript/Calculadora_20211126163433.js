//MAIN
$(document).ready(function () {
    
  });

  function Aportacion(){
      
      $('button[id^="Aportacion-"]').click(function(){
		var preparandoClave = $(this).attr('id').split("Aportacion-");

		var idUsuario = preparandoClave[1]; //id Mensaje
        var cantidadS = $('#pantallaAportacion-' + idUsuario).val();
      });
  }