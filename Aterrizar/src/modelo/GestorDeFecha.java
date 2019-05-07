package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class GestorDeFecha {
	private DateTimeFormatter formatoLatinoamericano = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private DateTimeFormatter formatoNorteamericano = DateTimeFormatter.ofPattern("MM-dd-yyyy");
	private ArrayList<DateTimeFormatter> formatoFlexible = new ArrayList<DateTimeFormatter>();

	public ArrayList<DateTimeFormatter> getFormatoFlexible() {
		return formatoFlexible;
	}

	public void setFormatoFlexible(ArrayList<DateTimeFormatter> nuevoFormatoFlexible) {
		formatoFlexible = nuevoFormatoFlexible;
	}

	public void agregarFormatoFlexible(DateTimeFormatter formatoNuevo) {
		formatoFlexible.add(formatoNuevo);
	}

	public LocalDate convertirAFechaISO8601(String fecha) {
		return LocalDate.parse(fecha); // parse(x) utiliza iso8601 por defecto.

	}

	public LocalDate convertirAFechaLatinoamericana(String fecha) {
		return LocalDate.parse(fecha, formatoLatinoamericano);

	}

	public LocalDate convertirAFechaNorteamericana(String fecha) {
		return LocalDate.parse(fecha, formatoNorteamericano);

	}

	public LocalDate convertirAFechaFlexible(String fecha) {
		return formatoFlexible.stream()
				.filter(formato -> esParseable(fecha, formato))
				.map(formato -> LocalDate.parse(fecha, formato))
				.findFirst()
				.get();

	}

	public boolean esParseable(String fecha, DateTimeFormatter formato) {
		try {
			LocalDate.parse(fecha, formato);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public long diasDeDiferencia(LocalDate dateDesde, LocalDate dateHasta) {
		long cantidadDeDiasEntreFechas = ChronoUnit.DAYS.between(dateDesde, dateHasta);
		return cantidadDeDiasEntreFechas;
	}

	public boolean esFechaAnterior(LocalDate dateObjetivo, LocalDate dateAComparar) {
		return dateObjetivo.isBefore(dateAComparar);
	}

}
