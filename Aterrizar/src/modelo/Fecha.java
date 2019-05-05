package modelo;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Fecha {
	private SimpleDateFormat formatoISO8601 = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat formatoLatinoamericano = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat formatoNorteamericano = new SimpleDateFormat("MM-dd-yyyy");
	private ArrayList<SimpleDateFormat> formatoFlexible = new ArrayList<SimpleDateFormat>();

	public ArrayList<SimpleDateFormat> getFormatoFlexible() {
		return formatoFlexible;
	}

	public void setFormatoFlexible(ArrayList<SimpleDateFormat> nuevoFormatoFlexible) {
		formatoFlexible = nuevoFormatoFlexible;
	}

	public void agregarFormatoFlexible(String formato) {
		SimpleDateFormat formatoNuevo = new SimpleDateFormat(formato);
		formatoFlexible.add(formatoNuevo);
	}

	public Date convertirAFechaISO8601(String fecha) throws ParseException {
		Date date = new Date();
		date = formatoISO8601.parse(fecha);
		return date;
	}

	public Date convertirAFechaLatinoamericana(String fecha) throws ParseException {
		Date date = new Date();
		date = formatoLatinoamericano.parse(fecha);
		return date;
	}

	public Date convertirAFechaNorteamericana(String fecha) throws ParseException {
		Date date = new Date();
		date = formatoNorteamericano.parse(fecha);
		return date;

	}

	// TODO: Flexible debe tener varios formatos para usar y comparar.
	public void convertirAFechaFlexible(String fecha) throws ParseException {
		formatoFlexible.stream().map(formato -> {
			try {
				return formato.parse(fecha);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				return e;
			}
		});

	}

	public int diasDeDiferencia(Date date1, Date date2) {
		long date3 = date2.getTime() - date1.getTime();
		return (int) (date3 / (1000 * 60 * 60 * 24));
	}

	public int esFechaAnterior(Date date1, Date date2) {
		return date1.compareTo(date2);
	}
	// Sacando los archivos demas.

}
