$(document).ready(function () {
    //##########################
// VER USUARIO  
//##########################

var IdUsuarioParaVer;
function verUsuario() { //Private
  $('button[id^="verUsuario"]').click(function () {
    var preparandoClave = $(this).attr('id').split("verUsuario");
    var idUsuario = preparandoClave[1]; //id Usuario
    IdUsuarioParaVer = { "id": idUsuario };
    
    $.ajax({
      url: "http://localhost:4000/authentication/ver-socio",
      type: "POST",
      data: JSON.stringify(IdUsuarioParaVer),
      contentType: "application/json; charset=UTF-8",
      headers: {
        "Authorization": localStorage.getItem('token')
      }
    }).done(function (data, textStatus, jqXHR) {
      if (data != null) {
       
        var json = { "id": data.id };
        $('#verUsuarioDni').text(data.dni);
        $('#verUsuarioNombre').text(data.nombre);
        $('#verUsuarioApellidos').text(data.apellidos);
        $('#verUsuarioTelefono').text(data.telefono);
        $('#verUsuarioDomicilio').text(data.domicilio);
        $('#verUsuarioMunicipio').text(data.municipio);
        $('#verUsuarioProvincia').text(data.provincia);
        $('#verUsuarioRol').text(data.rol);
       $('#verUsuarioTotalAportacionUsuario').text(data.totalAnotacion);
      mostrarAportacionPorUsuario();
      descargarFoto()
      }


    })
      .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("La solicitud a fallado: " + textStatus + ' son cosas que pasan');

      });

  });
}

function mostrarAportacionPorUsuario() {
  $.ajax({
    url: "http://localhost:4000/authentication/verAportacion",
    type: "POST",
    data: JSON.stringify(IdUsuarioParaVer),
    contentType: "application/json; charset=UTF-8",
    headers: {
      "Authorization": localStorage.getItem('token')
    }
  }).done(function (data, textStatus, jqXHR) {
    if (data != null) {
      $('#cuerpo-anotacion').find('tr').remove();
      $.each(data, function (i, item) {
        $('#cuerpo-anotacion').append('<tr><td>'+data[i].fecha+'</td><td class="align-middle">' + data[i].descripcion + '</td><td id="aportacion" class="align-middle"><a id="signoAportacionTabla'+ data[i].id +'"></a><a class="aportacionTabla'+ data[i].id +'">' + data[i].aportacion + '</a><a class="aportacionTabla'+ data[i].id +'">€</a></td></tr>');
        if(data[i].estado == "Aportacion"){
          $('#signoAportacionTabla'+ data[i].id).text('+').addClass('text-success');
          $('.aportacionTabla'+ data[i].id).addClass('text-success')
        }
        else if(data[i].estado == "Consumicion"){
          $('#signoAportacionTabla'+ data[i].id).text('-').addClass('text-danger')
          $('.aportacionTabla'+ data[i].id).addClass('text-danger')

        }
      });
    }

  })
    .fail(function (jqXHR, textStatus, errorThrown) {
      console.log("La solicitud a fallado: " + textStatus + ' son cosas que pasan');

    });
}

//###############################
//       Funciones CARGAR Fotos Usuario
//###############################

function descargarFoto(){
  $.ajax({
    url: "http://localhost:4000/authentication/extraerFotosUsuario",
    type: "POST",
    data: JSON.stringify(IdUsuarioParaVer),
    contentType: "application/json; charset=UTF-8",
    headers: {
      "Authorization": localStorage.getItem('token')
    }
  }).done(function (data, textStatus, jqXHR) {
    if (data != null) {
      var cadenaDelantera = 'data:image/jpg;base64,' + data.fotoDelantera;
      $('#fotoDelantera').attr('src', cadenaDelantera);
      var cadenaTrasera = 'data:image/jpg;base64,' + data.fotoTrasera;
      $('#fotoTrasera').attr('src', cadenaTrasera);
    }
    else{
      console.log("No llegan las fotos")
    }

  })
    .fail(function (jqXHR, textStatus, errorThrown) {
      console.log("La solicitud a fallado: " + textStatus + ' son cosas que pasan');

    });
}


//################################
//       Funciones BORRAR Usuario
//##############################

var idUsuarioCache;

function mostrarMensajeBorrar() {
  botonesModelBorrar();
  $('a[id^="borrarUsuario"]').click(function () {
    var preparandoClave = $(this).attr('id').split("borrarUsuario");
    idUsuarioCache = preparandoClave[1]; //id Usuario
    $('#modalConfirmarBorrar').modal({ backdrop: 'static', keyboard: false });
  });
}


function botonesModelBorrar() {


  $('#cancelarBorrarUsuario').click(function () {
    $('#modalConfirmarBorrar').modal('toggle');
  });

  $('#confirmarBorrarUsuario').click(function () {


    var json = { "id": idUsuarioCache };

    $.ajax({
      url: "http://localhost:4000/authentication/borrar-socio",
      type: "POST",
      data: JSON.stringify(json),
      contentType: "application/json; charset=UTF-8",
      headers: {
        "Authorization": localStorage.getItem('token')
      }
    }).done(function (data, textStatus, jqXHR) {
      if (data != null) {
        $('#modalConfirmarBorrar').modal('toggle');
        $('#filaTablaUsuariosAdmin' + idUsuarioCache).remove();
      }
      else {
        console.log("La solicitud no se ha confirmado: " + textStatus + ' son cosas que pasan');

      }

    })
      .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("La solicitud a fallado: " + textStatus + ' son cosas que pasan');

      });
  });

}



//#####################################
//    Funciones EDITAR Usuario
//#####################################

var idUsuarioCacheEditar;
var aportacionCahe;

function mostrarFormularioEditar() {
  $('a[id^="editarUsuario"]').click(function () {
    var preparandoClave = $(this).attr('id').split("editarUsuario");
    idUsuarioCacheEditar = preparandoClave[1]; //id Usuario
    console.log('EL id de usuario a EDITAR que se envia es ' + idUsuarioCacheEditar);
    $('#modalAddUsuario').modal({ backdrop: 'static', keyboard: false });

    var json = { "id": idUsuarioCacheEditar };

    $.ajax({
      url: "http://localhost:4000/authentication/ver-socio",
      type: "POST",
      data: JSON.stringify(json),
      contentType: "application/json; charset=UTF-8",
      headers: {
        "Authorization": localStorage.getItem('token')
      }
    }).done(function (data, textStatus, jqXHR) {
      if (data != null) {
        $('#DniCrearUsuario').val(data.dni);
        $('#NombreCrearUsuario').val(data.nombre);
        $('#ApellidoCrearUsuario').val(data.apellidos);
        $('#TelefonoCrearUsuario').val(data.telefono);
        $('#DomicilioCrearUsuario').val(data.domicilio);
        $('#MunicipioCrearUsuario').val(data.municipio);
        $('#ProvinciaCrearUsuario').val(data.provincia);
        aportacionCahe = data.totalAnotacion;
        if (data.rol == 'admin') {
          $("#UsuarioCheck").prop('checked', false);
          $("#AdminCheck").prop('checked', true);
        }

        else {
          $("#UsuarioCheck").prop('checked', true);
          $("#AdminCheck").prop('checked', false);
        }

        $('#tituloFormularioUsuario').text('Editar Usuario');
        $('#botonCrearUsuario').css('display', 'none');
        $('#editarCrearUsuario').css('display', 'block');
        $('#cajaContraseñaFormulario').css('display', 'none');
        botonGuardarEditarUsuario();
      }


    })
      .fail(function (jqXHR, textStatus, errorThrown) {
        console.log("La solicitud a fallado: " + textStatus + ' son cosas que pasan');

      });

  });
}


function botonGuardarEditarUsuario() {
  $('#editarCrearUsuario').click(function () {
    var nombre = $('#NombreCrearUsuario').val();
    var apellidos = $('#ApellidoCrearUsuario').val();
    var telefono = $('#TelefonoCrearUsuario').val();
    var domicilio = $('#DomicilioCrearUsuario').val();
    var municipio = $('#MunicipioCrearUsuario').val();
    var provincia = $('#ProvinciaCrearUsuario').val();
    var contraseña = $('#ContraseñaCrearUsuario').val();
    var dni = $('#DniCrearUsuario').val();
    var rol = $('input:radio[name=permisos]:checked').val();

    var json = {
      "id": idUsuarioCacheEditar,
      "nombre": nombre,
      "apellidos": apellidos,
      "telefono": telefono,
      "domicilio": domicilio,
      "municipio": municipio,
      "provincia": provincia,
      "contraseña": contraseña,
      "rol": rol,
      "dni": dni
    }
    console.log(json);
    if (idUsuarioCacheEditar != null && nombre != null && apellidos != '' && telefono != '' && domicilio != '' && municipio != '' && provincia != '' && rol != '') {

      enviarUsuarioEditar(json);
      $('#datosVaciosformulario').css('display', 'none');
    }
    else {
      console.log('No envia hay datos vacios')
      $('#datosVaciosformulario').css('display', 'block');
    }



  });
}

function enviarUsuarioEditar(json) {
  $.ajax({
    url: "http://localhost:4000/authentication/guardar-socio",
    type: "POST",
    data: JSON.stringify(json),
    contentType: "application/json; charset=UTF-8",
    headers: {
      "Authorization": localStorage.getItem('token')
    }
  }).done(function (data, textStatus, jqXHR) {
    if (data != null) {

      if (data == true) {


        $('#modalAddUsuario').modal('toggle');
        $('#filaTablaUsuariosAdmin' + idUsuarioCacheEditar).find('td').remove();
        $('#filaTablaUsuariosAdmin' + idUsuarioCacheEditar).append('<td>' + json.fotoDNI + '</td><td>' + json.nombre + '</td><td>' + json.apellidos + '</td><td><button class="btn btn-primary" id="verUsuario' + json.id + '" data-toggle="modal" data-target="#modalVerUsuario"><a>Ver</a><a>&nbsp;</a><i class="fa fa-user"></i></button></td><td><input type="text" maxlength="26" placeholder="Concepto" id="pantallaConcepto' + json.id + '" class="mx-2"></td><td><div class="d-flex justify-content-around"><button class="btn btn-danger" id="Consumicion' + json.id + '"> - </button><input min="0" max="10000" step="1" placeholder="€" type="number" id="pantallaAportacion' + json.id + '" class="mx-2"><button class="btn btn-success" id="Aportacion' + json.id + '"> + </button></div></td><td><a id="dinero' + json.id + '">' + aportacionCahe + '</a><a>€</a></td><td><a type="button"><i id="borrarUsuario' + json.id + '" class="fa fa-close text-danger borrar"></i></a></td><td><a type="button" id="editarUsuario' + json.id + '"><i class="fa fa-edit text-warning editar"></i></a></td></tr>');
        $('#modalAddUsuario').modal('toggle');
        AportacionSumar();
        AportacionRestar();
        verUsuario();
        mostrarMensajeBorrar();
        mostrarFormularioEditar();
      }
      else {
        alert("El Usuario NO ha sido guardado");
      }

    }

  })
    .fail(function (jqXHR, textStatus, errorThrown) {
      console.log("La solicitud a fallado: " + textStatus + ' son cosas que pasan');

    });

}
});