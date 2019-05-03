package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exceptions.FechaInvalidaException;

public class FechaEnISO8601 implements IFechaConvertidor{

	public static void convertir(String fecha) throws ParseException {
		SimpleDateFormat fechaEnISO8601 = new SimpleDateFormat("yyyy-MM-dd");
		Date date = fechaEnISO8601.parse(fecha);
	}

}
