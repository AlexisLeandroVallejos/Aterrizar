package modelo;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

	public long diasDeDiferencia(LocalDate dateDesde, LocalDate dateHasta) {
		long cantidadDeDiasEntreFechas = ChronoUnit.DAYS.between(dateDesde, dateHasta);
		return cantidadDeDiasEntreFechas;
	}

	public boolean esFechaAnterior(LocalDate dateObjetivo, LocalDate dateAComparar) {
		return dateObjetivo.isBefore(dateAComparar);
	}
	//TODO: Seguir Refactorizando.

}
