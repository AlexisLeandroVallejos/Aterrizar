package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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

	@Test(expected = DateTimeParseException.class)
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

	@Test(expected = DateTimeParseException.class)
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

	@Test(expected = DateTimeParseException.class)
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
		SimpleDateFormat formatoNuevo = new SimpleDateFormat("MMM dd yyyy");
		fecha.agregarFormatoFlexible("MMM dd yyyy");
		fecha.convertirAFechaFlexible(stringDeFecha);
		Assert.assertEquals(fecha.getFormatoFlexible().get(0).parse(stringDeFecha), formatoNuevo.parse(stringDeFecha));
	}

	@Test(expected = ParseException.class)
	public void convertirAFechaFlexible_fechaQueNoEstaEnFormatoFlexible() throws ParseException {
		String stringDeFecha = "Apr-01-2002";
		Fecha fecha = new Fecha();
		fecha.agregarFormatoFlexible("dd/MM/yyyy");
		fecha.convertirAFechaFlexible(stringDeFecha);
		fecha.getFormatoFlexible().get(0).parse(stringDeFecha);
	}

	@Test
	public void diasDeDiferencia_dosFechasQueDanQuinceDiasDeDiferencia() {
		Fecha fecha = new Fecha();
		LocalDate dateDesde = LocalDate.of(1995, 12, 1);
		LocalDate dateHasta = LocalDate.of(1995, 12, 16);
		Assert.assertEquals(fecha.diasDeDiferencia(dateDesde, dateHasta), 15);

	}

	@Test
	public void diasDeDiferencia_dosFechasQueNoDanQuinceDiasDeDiferencia() {
		Fecha fecha = new Fecha();
		LocalDate dateDesde = LocalDate.of(1995, 12, 1);
		LocalDate dateHasta = LocalDate.of(1997, 7, 20);
		Assert.assertNotEquals(fecha.diasDeDiferencia(dateDesde, dateHasta), 15);

	}

	@Test
	public void esFechaAnterior_primeraFechaEsAnteriorALaSegunda() {
		Fecha fecha = new Fecha();
		LocalDate dateObjetivo = LocalDate.of(2005, 4, 1);
		LocalDate dateAComparar = LocalDate.of(2007, 3, 20);
		Assert.assertSame(fecha.esFechaAnterior(dateObjetivo, dateAComparar), true);
	}

	@Test
	public void esFechaAnterior_primeraFechaEsPosteriorALaSegunda() {
		Fecha fecha = new Fecha();
		LocalDate dateObjetivo = LocalDate.of(2015, 2, 14);
		LocalDate dateAComparar = LocalDate.of(2007, 8, 22);
		Assert.assertSame(fecha.esFechaAnterior(dateObjetivo, dateAComparar), false);
	}

	@Test
	public void esFechaAnterior_primeraFechaEsIgualALaSegunda() {
		Fecha fecha = new Fecha();
		LocalDate dateObjetivo = LocalDate.of(2011, 10, 30);
		LocalDate dateAComparar = LocalDate.of(2011, 10, 30);
		Assert.assertSame(fecha.esFechaAnterior(dateObjetivo, dateAComparar), false);
	}

}
