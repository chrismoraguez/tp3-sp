package tp3SP;

import java.io.*;
import java.lang.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;
public class ProcesadorTextoMultiHilo extends Thread
{
        InputStream in;
        ProcesadorTextoMultiHilo(String fname) throws Exception
        {
                in=new FileInputStream(fname);
                this.start();
        }
        public void run()
        {
        	File txtBaseIdiomas = null;
    		File texto = null;
    		String linea;
    		FileReader fr = null;
    		BufferedReader br = null;

    		int caracter;
    		String ruta = "";
    		Archivo arch=new Archivo();
    		
    		try {
    			/**
        		 * Se carga la base de frecuencia de idiomas a partir de un TXT
        		 */
           
        		FrecuenciaIdioma frec = new FrecuenciaIdioma();
        		String idiomaActual = "";
        		String idiomaAnterior = "";

        		txtBaseIdiomas = new File("C:\\base_idiomas.txt"); 
        		fr = new FileReader(txtBaseIdiomas);
        		br = new BufferedReader(fr);
        		synchronized(br) {
            		while ((linea = br.readLine()) != null) {
            			String listaarray[] = linea.split("\\Q|\\E");
            			idiomaActual = listaarray[0].trim();
            			
            			if (!(idiomaAnterior.equalsIgnoreCase(idiomaActual))) {
            				// Se crea un nuevo mapa cantidadLetras para el nuevo idioma, luego se agrega la letra y su frecuencia
            				frec.cantidadLetras = new HashMap<String, BigDecimal>();
            				frec.cantidadLetras.put(listaarray[1], new BigDecimal(listaarray[2]));
            				frec.listaIdiomas.put(idiomaActual, frec.cantidadLetras);
            			} else {
            				// Se agrega la letra y su frecuencia
            				frec.cantidadLetras.put(listaarray[1], new BigDecimal(listaarray[2]));
            			} 
            	
            			idiomaAnterior = listaarray[0].trim();
            		}
        		}

        		
        		/**
        		 * Se lee el TXT caracter por caracter
        		 */
        		synchronized(in) {
            		while ((caracter = in.read()) != -1) {
        				arch.evaluarCaracter(Character.toLowerCase((char) caracter));  
        		}
        		}


        		//System.out.println();
        		
        		//Se ejecuta metodo para calcular la frecuencia de letras para el texto leido
        		arch.calcularFrecuencia();
        		
        		frec.compararIdiomas(arch);
    		} catch (Exception e){
    			e.printStackTrace();
    		}
    		
                try
                {
                        in.close();
                }catch(Exception e){}
        }
        public static void main(String a[]) throws Exception
        {
        		String ruta = "";    
        		int n=10; //Nro. de threads
             // Se solicita que se ingrese la ruta del TXT que va a ser leido
        		System.out.println("Por favor, ingresar la ruta del directorio en donde se encuentra el archivo de texto a procesar:");
        		Scanner scanner = new Scanner(System.in);
        		ruta = scanner.nextLine();
                ProcesadorTextoMultiHilo fr[]=new ProcesadorTextoMultiHilo[n];
                long tim;
                tim=System.currentTimeMillis();
                for(int i=0;i<n;i++){
                	fr[i]=new ProcesadorTextoMultiHilo(ruta);
                	System.out.println("Creé Thread Nro: " + (i + 1));
                }

                for(int i=0;i<n;i++)
                {
                        try
                        {
                                fr[i].join();
                        }catch(Exception e){}
                }
                System.out.println("Tiempo requerido para la ejecución : "+(System.currentTimeMillis()-tim)+" miliseconds.");
        }
}
