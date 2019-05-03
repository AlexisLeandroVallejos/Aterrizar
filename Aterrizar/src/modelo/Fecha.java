package modelo;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

import exceptions.FechaInvalidaException;

public class Fecha {

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

	// TODO: Flexible debe tener varios formatos para usar y comparar.
	public static Date convertirAFechaFlexible(String fecha) throws ParseException {
		Date date = new Date();
		SimpleDateFormat fechaEnFormatoFlexible = new SimpleDateFormat("MMM dd yyyy");
		date = fechaEnFormatoFlexible.parse(fecha);
		return date;

	}

	public static int diasDeDiferencia(Date date1, Date date2) {
		long date3 = date2.getTime() - date1.getTime();
		return (int) (date3 / (1000 * 60 * 60 * 24));
	}

	public static int esFechaAnterior(Date date1, Date date2) {
		return date1.compareTo(date2);
	}

}
