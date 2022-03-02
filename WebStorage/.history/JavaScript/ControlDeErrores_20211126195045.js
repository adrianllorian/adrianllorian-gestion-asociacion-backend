$(document).ready(function () {

    restringirCalculadora();
   
  });

  function restringirCalculadora(){
    
    var pasta = $('input[id^="pantallaAportacion"]');
    var concepto = $('input[id^="pantallaConcepto"]'); 
    pasta.on('input', function () { 
        this.value = this.value.replace(/[^0-9]/g,'');
    });
}