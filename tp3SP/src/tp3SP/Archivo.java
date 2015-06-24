package tp3SP;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class Archivo {

	// Map que almacena veces que aparece cada letra del alfabeto
	public static Map<Character, Integer> cantidadLetras = new HashMap<Character, Integer>();
	public static Map<Character, BigDecimal> porcentajeLetras = new HashMap<Character, BigDecimal>();

	long cantidadLetrasTotales = 0;
	int cantidadTemporal = 0;

	public void evaluarCaracter(char caracter) {

		// Solo ingresa al if si el caracter es una letra
		if (Character.isLetter(caracter)) {
			if (cantidadLetras.containsKey(caracter)) {
				// La letra ya existe en el map
				caracter = Character.toLowerCase(caracter);
				cantidadTemporal = cantidadLetras.get(caracter);
				cantidadLetras.put(caracter, cantidadTemporal + 1);
				cantidadLetrasTotales++;
			} else {
				// La letra no existe en el map
				cantidadLetras.put(caracter, 1);
				cantidadLetrasTotales++;
			}
		}
	}

	public void calcularFrecuencia() {
		String porcentajeAux;
		BigDecimal porcentaje;
		DecimalFormat formatoDecimal = new DecimalFormat("#.##");
		Iterator<Character> iterator = cantidadLetras.keySet().iterator();

		// Se itera el map de cantidad de letras
		while (iterator.hasNext()) {
			char key = iterator.next();
			Integer value = cantidadLetras.get(key);

			// Se calcula el % de aparicion de cada letra
			porcentajeAux = formatoDecimal.format((value * 100.00)/ this.cantidadLetrasTotales);
			porcentaje = new BigDecimal(porcentajeAux.replace(",", "."));

			// Se almacena en un nuevo map < LETRA > < % DE APARICION >
			porcentajeLetras.put(key, porcentaje);
		}
	}
}
