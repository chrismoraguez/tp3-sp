package tp3SP;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class FrecuenciaIdioma {

	public Map<String,BigDecimal> cantidadLetras = new HashMap<String, BigDecimal>();
	public Map<String, Map> listaIdiomas = new HashMap<String, Map>();
	public Map<String, Integer> contadorCoincidencias = new HashMap <String, Integer>();
	BigDecimal margenSuperior,margenInferior, numeroMargen = new BigDecimal("2.5");
	
	
	public void compararIdiomas(Archivo arch) {
		Iterator<String> iteratorIdiomas = this.listaIdiomas.keySet().iterator();
		Map<String, Map> mapTmp = new HashMap<String, Map>();
		int contCoincidencias;
			while (iteratorIdiomas.hasNext()) {
				//Se itera cada idioma
				contCoincidencias = 0;
				String key = iteratorIdiomas.next();
				mapTmp = this.listaIdiomas.get(key);
				//System.out.println(key);
					//Se itera la frecuencia de cada idioma
					Iterator<String> iteratorFrec = mapTmp.keySet().iterator();
					while (iteratorFrec.hasNext()) {
						//Se itera cada frecuencia
						String key2 = iteratorFrec.next();
						BigDecimal value = (BigDecimal) mapTmp.get(key2);	
						//System.out.println(key2 + value); 
						
						char[] charArray = key2.toCharArray();
						char letra = charArray[0];
						
						margenSuperior = value.add(numeroMargen);
						margenInferior = value.subtract(numeroMargen);
			
						System.out.println("Margen Superior: " + margenSuperior);
						System.out.println("Value: " + value);
						System.out.println("Margen Inferior: " + margenInferior);
						System.out.println("Letra: " + letra);
						System.out.println("Porcentaje Letra: " + arch.porcentajeLetras.get(letra));
						
						BigDecimal porcentajeLetra = arch.porcentajeLetras.get(letra);
						
						if(  porcentajeLetra != null ){
							if ( (porcentajeLetra.compareTo(margenInferior) >= 0) && (porcentajeLetra.compareTo(margenSuperior) <= 0)){
								contCoincidencias++;	
							}
						}
					System.out.println("Key Idioma: " + key);
					System.out.println("Cantidad Coincidencias: " + contCoincidencias);
					contadorCoincidencias.put(key, contCoincidencias);	
					}
			}
					 
				
				//System.out.println("Key Idioma: " + key);
				//System.out.println("Cantidad Coincidencias: " + contCoincidencias);
				
				
				
								
						//}
						
						//BigDecimal tmp=arch.porcentajeLetras.get(key2);
						//margenInferior=margenInferior.min(tmp);
						
						//value
						
						//System.out.println(arch.porcentajeLetras);
			System.out.println(contadorCoincidencias);		
	}
}
