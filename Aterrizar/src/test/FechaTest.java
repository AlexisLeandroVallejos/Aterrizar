package test;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import modelo.*;

public class FechaTest {

	// Formato ISO8601 es: yyyy-MM-dd
	@Test
	public void convertirAFechaISO8601_fechaEnFormatoISO8601() throws ParseException {
		String stringDeFecha = "2003-04-12";
		Fecha fecha = new Fecha();
		fecha.convertirAFechaISO8601(stringDeFecha);
	}

	@Test(expected = ParseException.class)
	public void convertirAFechaISO8601_fechaQueNoEstaEnFormatoISO8601() throws ParseException {
		String stringDeFecha = "12/03/1995";
		Fecha fecha = new Fecha();
		fecha.convertirAFechaISO8601(stringDeFecha);
	}

	// Formato Latinoamericano es: dd/MM/yyyy
	@Test
	public void convertirAFechaLatinoamericana_fechaEnFormatoLatinoamericano() throws ParseException {
		String stringDeFecha = "10/09/1989";
		Fecha fecha = new Fecha();
		fecha.convertirAFechaLatinoamericana(stringDeFecha);
	}

	@Test(expected = ParseException.class)
	public void convertirAFechaLatinoamericana_fechaQueNoEstaEnFormatoLatinoamericano() throws ParseException {
		String stringDeFecha = "09-12-1989";
		Fecha fecha = new Fecha();
		fecha.convertirAFechaLatinoamericana(stringDeFecha);
	}

	// Formato Norteamericano es: MM-dd-yyyy
	@Test
	public void convertirAFechaNorteamericana_fechaEnFormatoNorteamericano() throws ParseException {
		String stringDeFecha = "12-24-2015";
		Fecha fecha = new Fecha();
		fecha.convertirAFechaNorteamericana(stringDeFecha);
	}

	@Test(expected = ParseException.class)
	public void convertirAFechaNorteamericana_fechaQueNoEstaEnFormatoNorteamericano() throws ParseException {
		String stringDeFecha = "12/07/1995";
		Fecha fecha = new Fecha();
		fecha.convertirAFechaNorteamericana(stringDeFecha);
	}

	// TODO: Formato Flexible es: "MMM dd yyyy" y otros mas...
	@Test
	public void convertirAFechaFlexible_fechaEnFormatoFlexible() throws ParseException {
		String stringDeFecha = "Oct 12 2013";
		Fecha fecha = new Fecha();
		fecha.convertirAFechaFlexible(stringDeFecha);

	}

	@Test(expected = ParseException.class)
	public void convertirAFechaFlexible_fechaQueNoEstaEnFormatoFlexible() throws ParseException {
		String stringDeFecha = "Apr-1-2002";
		Fecha fecha = new Fecha();
		fecha.convertirAFechaFlexible(stringDeFecha);

	}

	@Test
	public void diasDeDiferencia_dosFechasQueDanQuinceDiasDeDiferencia() {
		Date date1 = new Date(1995,12,1);
		Date date2 = new Date(1995,12,16);
		Fecha fecha = new Fecha();
		Assert.assertSame(fecha.diasDeDiferencia(date1, date2), 15);

	}
	
	@Test
	public void diasDeDiferencia_dosFechasQueNoDanQuinceDiasDeDiferencia() {
		Date date1 = new Date(1995,12,1);
		Date date2 = new Date(1997,7,20);
		Fecha fecha = new Fecha();
		Assert.assertNotSame(fecha.diasDeDiferencia(date1, date2), 15);

	}

}
