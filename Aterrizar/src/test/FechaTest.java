package test;

import org.junit.Assert;
import org.junit.Test;

import modelo.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class FechaTest {

	@Test
	public void convertirAFecha_fechaEnIso8601(){
		String fecha = "2003-04-12";
		Fecha.convertir(fecha);
	}
}
/*
 * @Test public void convertirAFecha_fechaEnLatinoamericano() { String
 * nuevaFecha = "13/02/1998"; FechaEnIso8601 fecha = new FechaEnIso8601();
 * FechaEnIso8601.convertirAFecha(nuevaFecha); }
 * 
 * @Test public void convertirAFecha_fechaEnNorteamericano() { String nuevaFecha
 * = "12-01-2007"; FechaEnIso8601 fecha = new FechaEnIso8601();
 * FechaEnIso8601.convertirAFecha(nuevaFecha); }
 * 
 * @Test public void convertirAFecha_fechaEnFlexible() { String nuevaFecha =
 * "Agosto 21, 2012"; FechaEnIso8601 fecha = new FechaEnIso8601();
 * FechaEnIso8601.convertirAFecha(nuevaFecha); }
 * 
 * }
 */
