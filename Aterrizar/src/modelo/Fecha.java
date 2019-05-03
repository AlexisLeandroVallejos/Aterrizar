package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exceptions.FechaInvalidaException;

public class Fecha{

	public static Date convertirAFechaISO8601(String fecha) throws ParseException {
		Date date = new Date();
		SimpleDateFormat fechaEnFormatoISO8601 = new SimpleDateFormat("yyyy-MM-dd");
		date = fechaEnFormatoISO8601.parse(fecha);
		return date;
	}
	
	public static Date convertirAFechaLatinoamericana(String fecha) throws ParseException {
		Date date = new Date();
		SimpleDateFormat fechaEnFormatoLatinoamericano = new SimpleDateFormat("dd/MM/yyyy");
		date = fechaEnFormatoLatinoamericano.parse(fecha);
		return date;
	}

	public static Date convertirAFechaNorteamericana(String fecha) throws ParseException {
		Date date = new Date();
		SimpleDateFormat fechaEnFormatoNorteamericano = new SimpleDateFormat("MM-dd-yyyy");
		date = fechaEnFormatoNorteamericano.parse(fecha);
		return date;
		
	}

}
