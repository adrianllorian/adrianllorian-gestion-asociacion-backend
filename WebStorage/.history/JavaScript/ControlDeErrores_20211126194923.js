$(document).ready(function () {

    restringirCalculadora();
   
  });

  function restringirCalculadora(){
    var pasta = $('#pantallaAportacion' + idUsuario);
    var concepto = $('#pantallaConcepto' + idUsuario); 
    pasta.on('input', function () { 
        this.value = this.value.replace(/[^0-9]/g,'');
    });
}