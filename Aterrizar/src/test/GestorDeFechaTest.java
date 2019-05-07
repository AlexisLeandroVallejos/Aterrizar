package test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

import modelo.*;

public class GestorDeFechaTest {

	@Test
	public void agregarFormatoFlexible_agregoFormatoYLaColeccionDeFechasFlexiblesAumentaEn1() {
		GestorDeFecha gestor = new GestorDeFecha();
		DateTimeFormatter nuevoFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		gestor.agregarFormatoFlexible(nuevoFormato);
		Assert.assertEquals("No se agrego fecha flexible.",
				gestor.getFormatoFlexible().size(), 1);
	}
	
	@Test
	public void agregarFormatoFlexible_agregoFormatosYLaColeccionDeFechasFlexiblesAumentaEn2() {
		GestorDeFecha gestor = new GestorDeFecha();
		DateTimeFormatter nuevoFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter nuevoFormato2 = DateTimeFormatter.ofPattern("dd MM yyyy");
		gestor.agregarFormatoFlexible(nuevoFormato);
		gestor.agregarFormatoFlexible(nuevoFormato2);
		Assert.assertEquals("No se agrego fecha flexible.",
				gestor.getFormatoFlexible().size(), 2);
	}

	// Formato ISO8601 es: yyyy-MM-dd
	@Test
	public void convertirAFechaISO8601_fechaEnFormatoISO8601() {
		String stringDeFecha = "2003-04-12";
		GestorDeFecha gestor = new GestorDeFecha();
		gestor.convertirAFechaISO8601(stringDeFecha);
	}

	@Test(expected = DateTimeParseException.class)
	public void convertirAFechaISO8601_fechaQueNoEstaEnFormatoISO8601() {
		String stringDeFecha = "12/03/1995";
		GestorDeFecha gestor = new GestorDeFecha();
		gestor.convertirAFechaISO8601(stringDeFecha);
	}

	// Formato Latinoamericano es: dd/MM/yyyy
	@Test
	public void convertirAFechaLatinoamericana_fechaEnFormatoLatinoamericano() {
		String stringDeFecha = "10/09/1989";
		GestorDeFecha gestor = new GestorDeFecha();
		gestor.convertirAFechaLatinoamericana(stringDeFecha);
	}

	@Test(expected = DateTimeParseException.class)
	public void convertirAFechaLatinoamericana_fechaQueNoEstaEnFormatoLatinoamericano() {
		String stringDeFecha = "09-12-1989";
		GestorDeFecha gestor = new GestorDeFecha();
		gestor.convertirAFechaLatinoamericana(stringDeFecha);
	}

	// Formato Norteamericano es: MM-dd-yyyy
	@Test
	public void convertirAFechaNorteamericana_fechaEnFormatoNorteamericano() {
		String stringDeFecha = "12-24-2015";
		GestorDeFecha gestor = new GestorDeFecha();
		gestor.convertirAFechaNorteamericana(stringDeFecha);
	}

	@Test(expected = DateTimeParseException.class)
	public void convertirAFechaNorteamericana_fechaQueNoEstaEnFormatoNorteamericano() {
		String stringDeFecha = "12/07/1995";
		GestorDeFecha gestor = new GestorDeFecha();
		gestor.convertirAFechaNorteamericana(stringDeFecha);
	}

	@Test
	public void convertirAFechaFlexible_fechaValidaConFormatoIgualYValido() {
		String stringDeFecha = "09 12 1989";
		GestorDeFecha gestor = new GestorDeFecha();
		DateTimeFormatter formatoAUsar = DateTimeFormatter.ofPattern("dd MM yyyy");
		gestor.agregarFormatoFlexible(formatoAUsar);
		Assert.assertEquals("No se pudo convertir la fecha porque el formato usado no coincidia.",
				gestor.convertirAFechaFlexible(stringDeFecha),
				LocalDate.parse(stringDeFecha, formatoAUsar));
	}

	// LocalDate puede reconocer todos los patrones excepto el invalido.
	@Test
	public void convertirAFechaFlexible_fechaValidaConVariosFormatos() {
		String stringDeFecha = "01 24 2017";
		GestorDeFecha gestor = new GestorDeFecha();
		DateTimeFormatter formatoValido1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		DateTimeFormatter formatoValido2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatoObjetivo = DateTimeFormatter.ofPattern("MM dd yyyy");
		DateTimeFormatter formatoInvalido = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
		gestor.agregarFormatoFlexible(formatoValido1);
		gestor.agregarFormatoFlexible(formatoValido2);
		gestor.agregarFormatoFlexible(formatoObjetivo);
		gestor.agregarFormatoFlexible(formatoInvalido);
		Assert.assertEquals("No se pudo convertir la fecha porque el formato usado no coincidia.",
				gestor.convertirAFechaFlexible(stringDeFecha),
				LocalDate.parse(stringDeFecha, formatoObjetivo));
	}

	// LocalDate no reconoce el patron "MMM-dd-yyyy".
	// El stream queda vacio al no haber ningun patron valido.
	@Test(expected = NoSuchElementException.class)
	public void convertirAFechaFlexible_fechaInvalidaConFormatoIgualQueNoEsValido() {
		String stringDeFecha = "Apr-01-2002";
		GestorDeFecha gestor = new GestorDeFecha();
		DateTimeFormatter formatoAUsar = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
		gestor.agregarFormatoFlexible(formatoAUsar);
		gestor.convertirAFechaFlexible(stringDeFecha);
	}

	// El stream queda vacio al no haber ningun patron valido.
	@Test(expected = NoSuchElementException.class)
	public void convertirAFechaFlexible_fechaValidaQueNoTieneUnFormatoIgual() {
		String stringDeFecha = "2002 24 04";
		GestorDeFecha gestor = new GestorDeFecha();
		DateTimeFormatter formatoAUsar = DateTimeFormatter.ofPattern("yyyy/dd/MM");
		gestor.agregarFormatoFlexible(formatoAUsar);
		gestor.convertirAFechaFlexible(stringDeFecha);
	}

	@Test
	public void diasDeDiferencia_dosFechasQueDanQuinceDiasDeDiferencia() {
		GestorDeFecha gestor = new GestorDeFecha();
		LocalDate dateDesde = LocalDate.of(1995, 12, 1);
		LocalDate dateHasta = LocalDate.of(1995, 12, 16);
		Assert.assertEquals("La cantidad de dias no coincide.",
				gestor.diasDeDiferencia(dateDesde, dateHasta), 15);

	}

	@Test
	public void diasDeDiferencia_dosFechasQueNoDanQuinceDiasDeDiferencia() {
		GestorDeFecha gestor = new GestorDeFecha();
		LocalDate dateDesde = LocalDate.of(1995, 12, 1);
		LocalDate dateHasta = LocalDate.of(1997, 7, 20);
		Assert.assertNotEquals("La cantidad de dias no coincide.",
				gestor.diasDeDiferencia(dateDesde, dateHasta), 15);

	}

	@Test
	public void esFechaAnterior_primeraFechaEsAnteriorALaSegunda() {
		GestorDeFecha gestor = new GestorDeFecha();
		LocalDate dateObjetivo = LocalDate.of(2005, 4, 1);
		LocalDate dateAComparar = LocalDate.of(2007, 3, 20);
		Assert.assertEquals("La fecha no es anterior.",
				gestor.esFechaAnterior(dateObjetivo, dateAComparar), true);
	}

	@Test
	public void esFechaAnterior_primeraFechaEsPosteriorALaSegunda() {
		GestorDeFecha gestor = new GestorDeFecha();
		LocalDate dateObjetivo = LocalDate.of(2015, 2, 14);
		LocalDate dateAComparar = LocalDate.of(2007, 8, 22);
		Assert.assertEquals("La fecha no es anterior.",
				gestor.esFechaAnterior(dateObjetivo, dateAComparar), false);
	}

	@Test
	public void esFechaAnterior_primeraFechaEsIgualALaSegunda() {
		GestorDeFecha gestor = new GestorDeFecha();
		LocalDate dateObjetivo = LocalDate.of(2011, 10, 30);
		LocalDate dateAComparar = LocalDate.of(2011, 10, 30);
		Assert.assertEquals("La fecha no es anterior.",
				gestor.esFechaAnterior(dateObjetivo, dateAComparar), false);
	}

}
