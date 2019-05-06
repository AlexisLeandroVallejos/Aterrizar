package test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

import modelo.*;

public class FechaTest {

	@Test
	public void agregarFormatoFlexible_agregoFormatoYLaColeccionDeFechasFlexiblesAumentaEn1() {
		Fecha fecha = new Fecha();
		DateTimeFormatter nuevoFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		fecha.agregarFormatoFlexible(nuevoFormato);
		Assert.assertEquals(fecha.getFormatoFlexible().size(), 1);
	}

	// Formato ISO8601 es: yyyy-MM-dd
	@Test
	public void convertirAFechaISO8601_fechaEnFormatoISO8601() {
		String stringDeFecha = "2003-04-12";
		Fecha fecha = new Fecha();
		fecha.convertirAFechaISO8601(stringDeFecha);
	}

	@Test(expected = DateTimeParseException.class)
	public void convertirAFechaISO8601_fechaQueNoEstaEnFormatoISO8601() {
		String stringDeFecha = "12/03/1995";
		Fecha fecha = new Fecha();
		fecha.convertirAFechaISO8601(stringDeFecha);
	}

	// Formato Latinoamericano es: dd/MM/yyyy
	@Test
	public void convertirAFechaLatinoamericana_fechaEnFormatoLatinoamericano() {
		String stringDeFecha = "10/09/1989";
		Fecha fecha = new Fecha();
		fecha.convertirAFechaLatinoamericana(stringDeFecha);
	}

	@Test(expected = DateTimeParseException.class)
	public void convertirAFechaLatinoamericana_fechaQueNoEstaEnFormatoLatinoamericano() {
		String stringDeFecha = "09-12-1989";
		Fecha fecha = new Fecha();
		fecha.convertirAFechaLatinoamericana(stringDeFecha);
	}

	// Formato Norteamericano es: MM-dd-yyyy
	@Test
	public void convertirAFechaNorteamericana_fechaEnFormatoNorteamericano() {
		String stringDeFecha = "12-24-2015";
		Fecha fecha = new Fecha();
		fecha.convertirAFechaNorteamericana(stringDeFecha);
	}

	@Test(expected = DateTimeParseException.class)
	public void convertirAFechaNorteamericana_fechaQueNoEstaEnFormatoNorteamericano() {
		String stringDeFecha = "12/07/1995";
		Fecha fecha = new Fecha();
		fecha.convertirAFechaNorteamericana(stringDeFecha);
	}

	@Test
	public void convertirAFechaFlexible_fechaValidaConFormatoIgualYValido() {
		String stringDeFecha = "09 12 1989";
		Fecha fecha = new Fecha();
		DateTimeFormatter formatoAUsar = DateTimeFormatter.ofPattern("dd MM yyyy");
		fecha.agregarFormatoFlexible(formatoAUsar);
		Assert.assertEquals(fecha.convertirAFechaFlexible(stringDeFecha), LocalDate.parse(stringDeFecha, formatoAUsar));
	}

	// LocalDate puede reconocer todos los patrones excepto el invalido.
	@Test
	public void convertirAFechaFlexible_fechaValidaConVariosFormatos() {
		String stringDeFecha = "01 24 2017";
		Fecha fecha = new Fecha();
		DateTimeFormatter formatoValido1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		DateTimeFormatter formatoValido2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatoObjetivo = DateTimeFormatter.ofPattern("MM dd yyyy");
		DateTimeFormatter formatoInvalido = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
		fecha.agregarFormatoFlexible(formatoValido1);
		fecha.agregarFormatoFlexible(formatoValido2);
		fecha.agregarFormatoFlexible(formatoObjetivo);
		fecha.agregarFormatoFlexible(formatoInvalido);
		Assert.assertEquals(fecha.convertirAFechaFlexible(stringDeFecha),
				LocalDate.parse(stringDeFecha, formatoObjetivo));
	}

	// LocalDate no reconoce el patron "MMM-dd-yyyy".
	// El stream queda vacio al no haber ningun patron valido.
	@Test(expected = NoSuchElementException.class)
	public void convertirAFechaFlexible_fechaInvalidaConFormatoIgualQueNoEsValido() {
		String stringDeFecha = "Apr-01-2002";
		Fecha fecha = new Fecha();
		DateTimeFormatter formatoAUsar = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
		fecha.agregarFormatoFlexible(formatoAUsar);
		fecha.convertirAFechaFlexible(stringDeFecha);
	}

	// El stream queda vacio al no haber ningun patron valido.
	@Test(expected = NoSuchElementException.class)
	public void convertirAFechaFlexible_fechaValidaQueNoTieneUnFormatoIgual() {
		String stringDeFecha = "2002 24 04";
		Fecha fecha = new Fecha();
		DateTimeFormatter formatoAUsar = DateTimeFormatter.ofPattern("yyyy/dd/MM");
		fecha.agregarFormatoFlexible(formatoAUsar);
		fecha.convertirAFechaFlexible(stringDeFecha);
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
		Assert.assertEquals(fecha.esFechaAnterior(dateObjetivo, dateAComparar), true);
	}

	@Test
	public void esFechaAnterior_primeraFechaEsPosteriorALaSegunda() {
		Fecha fecha = new Fecha();
		LocalDate dateObjetivo = LocalDate.of(2015, 2, 14);
		LocalDate dateAComparar = LocalDate.of(2007, 8, 22);
		Assert.assertEquals(fecha.esFechaAnterior(dateObjetivo, dateAComparar), false);
	}

	@Test
	public void esFechaAnterior_primeraFechaEsIgualALaSegunda() {
		Fecha fecha = new Fecha();
		LocalDate dateObjetivo = LocalDate.of(2011, 10, 30);
		LocalDate dateAComparar = LocalDate.of(2011, 10, 30);
		Assert.assertEquals(fecha.esFechaAnterior(dateObjetivo, dateAComparar), false);
	}

}
