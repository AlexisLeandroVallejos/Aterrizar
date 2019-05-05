package modelo;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.time.format.DateTimeFormatter;

public class Fecha {
	private DateTimeFormatter formatoLatinoamericano = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private DateTimeFormatter formatoNorteamericano = DateTimeFormatter.ofPattern("MM-dd-yyyy");
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

	public LocalDate convertirAFechaISO8601(String fecha) throws ParseException {
		LocalDate date = LocalDate.parse(fecha); //parse(x) utiliza iso8601 por defecto.
		return date;
	}

	public LocalDate convertirAFechaLatinoamericana(String fecha) throws ParseException {
		LocalDate date = LocalDate.parse(fecha, formatoLatinoamericano);
		return date;
	}

	public LocalDate convertirAFechaNorteamericana(String fecha) throws ParseException {
		LocalDate date = LocalDate.parse(fecha, formatoNorteamericano);
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
	//TODO: Seguir Refactorizando.

}
