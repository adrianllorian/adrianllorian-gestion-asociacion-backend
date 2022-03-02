$(document).ready(function () {
    //borrarUsuario
});

function borrarUsuarios(){
    $('a[id^="borrarUsuario"]').click(function () {
        var preparandoClave = $(this).attr('id').split("verUsuario");
        var idUsuario = preparandoClave[1]; //id Usuario
    });
}