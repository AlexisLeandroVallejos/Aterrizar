package test;

import java.text.ParseException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import exceptions.FechaInvalidaException;
import modelo.*;

public class FechaTest {

	@Test
	public void convertir_fechaEnIso8601() {
		String stringDeFecha = "2003-04-12";
		IFechaConvertidor.convertir(stringDeFecha);
	}

	@Test
	public void convertir_fechaQueNoEstaEnIso8601() {
		String stringDeFecha = "24-09-1994";
		IFechaConvertidor.convertir(stringDeFecha);
		
	}
}
