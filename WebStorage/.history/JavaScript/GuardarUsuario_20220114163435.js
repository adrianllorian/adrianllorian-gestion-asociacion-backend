$(document).ready(function () {
    preparaFormulario();
    //botonCrearUsuario();
    ponerContraseñaFormulario();
    botonGenerarContraseña();

    
});
/*
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


