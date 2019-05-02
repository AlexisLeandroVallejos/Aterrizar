package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaEnIso8601 {
	
	public void convertirAIso8601(String algo) throws Exception {
		Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(algo);
	}
	
}
