//MAIN
$(document).ready(function () {
    
  });

  function Aportacion(){
      var cantidad = $('#pantallaAportacion').val();
      $('button[id^="Aportacion-"]').click(function(){
		var preparandoClave = $(this).attr('id').split("Aportacion-");
		var claveDelMensaje = preparandoClave[1]; //id Mensaje
  }