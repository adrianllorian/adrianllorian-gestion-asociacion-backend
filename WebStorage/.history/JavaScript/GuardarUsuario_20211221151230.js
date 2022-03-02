$(document).ready(function () {
    preparaFormulario();
    //botonCrearUsuario();
    ponerContraseñaFormulario();
    botonGenerarContraseña();

    
});

function preparaFormulario() {  
    $('#abrirModalCrearUsuario').click(function(){
        $('#tituloFormularioUsuario').text('Agregar Usuario');
        $('#botonCrearUsuario').css('display', 'block');
        $('#editarCrearUsuario').css('display', 'none');
        $('#cajaContraseñaFormulario').css('display','block');
        $('#NombreCrearUsuario').val('');
        $('#ApellidoCrearUsuario').val('');
        $('#TelefonoCrearUsuario').val('');
        $('#DomicilioCrearUsuario').val('');
        $('#MunicipioCrearUsuario').val('');
        $('#ProvinciaCrearUsuario').val('');
        $('#DniCrearUsuario').val('');
        $("#UsuarioCheck").prop('checked', true);
        $("#AdminCheck").prop('checked', false);
        $('#delante').attr("src","Imagenes/transparecia.png");
        $('#atras').attr("src","Imagenes/transparecia.png");
    });

}
/*
var fotoDNIDelantera;
var fotoDNITrasera;
function botonCrearUsuario(){
    $('#botonCrearUsuario').click(function(){
       
        var nombre = $('#NombreCrearUsuario').val();
        var apellidos = $('#ApellidoCrearUsuario').val();
        var telefono = $('#TelefonoCrearUsuario').val();
        var domicilio = $('#DomicilioCrearUsuario').val();
        var municipio = $('#MunicipioCrearUsuario').val();
        var provincia = $('#ProvinciaCrearUsuario').val();
        var contraseña = $('#ContraseñaCrearUsuario').val();
        var dni = $('#DniCrearUsuario').val();
        var rol = $('input:radio[name=permisos]:checked').val();
        fotoDNIDelantera = $('#inputFotoDniDelantera')[0].files[0];
        fotoDNITrasera = $('#inputFotoDniTrasera')[0].files[0]

        var json= {
            "nombre": nombre,
            "apellidos": apellidos,
            "telefono": telefono,
            "domicilio": domicilio,
            "municipio": municipio,
            "provincia": provincia,
            "contraseña": contraseña,
            "rol":rol,
            "dni":dni,
            
        }
        if(nombre!= null && apellidos!= '' && telefono!= '' && domicilio!= '' && municipio!= '' && provincia!= '' && contraseña!= '' && rol!= ''){
            
            enviarUsuario(json)
           
            $('#datosVaciosformulario').css('display', 'none');
        }
        else{
            console.log('No envia hay datos vacios')
            $('#datosVaciosformulario').css('display', 'block');
        }
       
    });
}

function enviarUsuario(json){
    
    $.ajax({
        url: "http://localhost:3000/guardar-socio",
        type: "POST",
        data: JSON.stringify(json),
        contentType: "application/json; charset=UTF-8",
        headers: {
          "Authorization": localStorage.getItem('token')
        }
      }).done(function (data, textStatus, jqXHR) {
        if (data != null) {
            if(data == true){
                $('#modalAddUsuario').modal('toggle');
                $('#cuerpoTablaSocios-Admin').append('<tr id="filaTablaUsuariosAdmin'+data.id+'"><td>' + data.fotoDNI + '</td>><td>' + data.nombre + '</td>><td>' + data.apellidos + '</td>><td><button class="btn btn-primary" id="verUsuario'+ data.id + '" data-toggle="modal" data-target="#modalVerUsuario">Ver</button></td><td><input type="text" maxlength="26" placeholder="Concepto" id="pantallaConcepto' + data.id + '" class="mx-2"></td><td><div class="d-flex justify-content-around"><button class="btn btn-danger" id="Consumicion' + data.id + '"> - </button><input min="0" max="10000" step="1" placeholder="€" type="number" id="pantallaAportacion' + data.id + '" class="mx-2"><button class="btn btn-success" id="Aportacion' + data.id + '"> + </button></div></td><td><a id="dinero' + data.id  +'">' + data.totalAnotacion + '</a><a>€</a></td><td><a type="button"><i id="borrarUsuario'+data.id +'" class="fa fa-close text-danger borrar"></i></a></td><td><a type="button" id="editarUsuario'+data.id +'"><i class="fa fa-edit text-warning editar"></i></a></td></tr>')       
            }
            alert("El Usuario NO ha sido guardado");
            
            
        }
    
      })
        .fail(function (jqXHR, textStatus, errorThrown) {
          console.log("La solicitud a fallado: " + textStatus + ' son cosas que pasan');
    
        });
}
*/
function ponerContraseñaFormulario(){
    $('#abrirModalCrearUsuario').click(function(){
        var con= crearContraseña();
        $('#ContraseñaCrearUsuario').prop('disabled', false);
        $('#ContraseñaCrearUsuario').val(con);
        $('#ContraseñaCrearUsuario').prop('disabled', true);
    })
    }

    function botonGenerarContraseña(){
        $('#generarContrasena').click(function(){
            var con= crearContraseña(); 
            $('#ContraseñaCrearUsuario').prop('disabled', false);
            $('#ContraseñaCrearUsuario').val(con);
            $('#ContraseñaCrearUsuario').prop('disabled', true);
        })
    }


function crearContraseña(){
    var letras=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','p','q','r','s','t','u','v','w','x','y','z'];
    var clave="";
    var theRandomNumber;
    for(var i=0; i<3; i++){
        clave= clave + letras[ Math.floor(Math.random() * letras.length)];
    }
    return clave;
}


