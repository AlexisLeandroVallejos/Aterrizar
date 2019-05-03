package test;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import modelo.*;

public class FechaTest {

	// Formato ISO8601 es: yyyy-MM-dd
	@Test
	public void convertirAFechaISO8601_fechaEnFormatoISO8601() throws ParseException {
		String stringDeFecha = "2003-04-12";
		Fecha fecha = new Fecha();
		Fecha.convertirAFechaISO8601(stringDeFecha);
	}

	@Test(expected = ParseException.class)
	public void convertirAFechaISO8601_fechaQueNoEstaEnFormatoISO8601() throws ParseException {
		String stringDeFecha = "12/03/1995";
		Fecha fecha = new Fecha();
		Fecha.convertirAFechaISO8601(stringDeFecha);
	}

	// Formato Latinoamericano es: dd/MM/yyyy
	@Test
	public void convertirAFechaLatinoamericana_fechaEnFormatoLatinoamericano() throws ParseException {
		String stringDeFecha = "10/09/1989";
		Fecha fecha = new Fecha();
		Fecha.convertirAFechaLatinoamericana(stringDeFecha);
	}

	@Test(expected = ParseException.class)
	public void convertirAFechaLatinoamericana_fechaQueNoEstaEnFormatoLatinoamericano() throws ParseException {
		String stringDeFecha = "09-12-1989";
		Fecha fecha = new Fecha();
		Fecha.convertirAFechaLatinoamericana(stringDeFecha);
	}

	// Formato Norteamericano es: MM-dd-yyyy
	@Test
	public void convertirAFechaNorteamericana_fechaEnFormatoNorteamericano() throws ParseException {
		String stringDeFecha = "12-24-2015";
		Fecha fecha = new Fecha();
		Fecha.convertirAFechaNorteamericana(stringDeFecha);
	}

	@Test(expected = ParseException.class)
	public void convertirAFechaNorteamericana_fechaQueNoEstaEnFormatoNorteamericano() throws ParseException {
		String stringDeFecha = "12/07/1995";
		Fecha fecha = new Fecha();
		Fecha.convertirAFechaNorteamericana(stringDeFecha);
	}

}
