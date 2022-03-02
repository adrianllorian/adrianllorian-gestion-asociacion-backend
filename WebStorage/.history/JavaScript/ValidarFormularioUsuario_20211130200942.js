$(document).ready(function () {
    validarNombre();
});

function validarNombre(){
    $("#NombreCrearUsuario").bind('keypress', function(event) {
        var regex = new RegExp("^[a-zA-Z ]+$");
        var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
        if (!regex.test(key)) {
          event.preventDefault();
          return false;
        }
      });
}

function validarApellido(){
    $("#ApellidoCrearUsuario").bind('keypress', function(event) {
        var regex = new RegExp("^[a-zA-Z ]+$");
        var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
        if (!regex.test(key)) {
          event.preventDefault();
          return false;
        }
      });
}

function validarTelefono(){
    $("TelefonoCrearUsuario").bind('keypress', function(event) {
        var regex = new RegExp("^[0-9+]+$");
        var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
        if (!regex.test(key)) {
          event.preventDefault();
          return false;
        }
      });
      


}


