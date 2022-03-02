package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class CrearNombreDeImagen {

	
	
	private  String crearNombreFoto() {
		String salida= "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		Date date = new Date();
		salida = dateFormat.format(date);
		return salida;
	}
	
	private  String crearClaveAleatoria() {
		Random random = new Random();
		int numero = random.nextInt(999 + 100) + 100;
		return String.valueOf(numero);
	}
	
	public String getClaveFinal() {
		return crearNombreFoto() + "-" + crearClaveAleatoria();
	}
}
