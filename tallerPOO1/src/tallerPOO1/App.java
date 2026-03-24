package tallerPOO1;
import java.util.*;
import java.io.*;
public class App {
	
	public static String[][] matrizUsuario;
	public static String[][] matrizRegistros;
	public static Scanner respuesta= new Scanner(System.in);
	public static void main(String[] args) {
		matrizUsuario= crearMatriz("Usuario.txt");
		matrizRegistros= crearMatriz("Registros.txt");
		String decicion="";
		while(!decicion.equals("3")){
		System.out.println("Bienvenido al control de procastinación\r\n"
					+ "1) Menu de usuarios\r\n"
					+ "2) Menu de análisis\r\n"
					+ "3) Salir\r\n"
					+ "");
		System.out.print(">>> ");
		decicion= respuesta.nextLine();
		System.out.println();
		}
		
	}
	
	public static String[][] crearMatriz(String texto) {
		String[][] matriz=null;
		try {
			int[] filCol= contMatriz(texto);
			matriz= new String[filCol[0]][filCol[1]];
			File arch= new File(texto);
			Scanner lector2= new Scanner(arch);
			int n=0;
			while(lector2.hasNextLine()) {
				String linea= lector2.nextLine();
				String[] partes= linea.split(";");
				for(int i=0; i<partes.length;i++) {
					matriz[n][i]=partes[i];
				}
				n++;
			}
			lector2.close();
			}catch(FileNotFoundException e) {
				System.out.println("Error no se a encotrado el archivo.");
			}
			
		    return matriz;
	}
	
	public static int[] contMatriz(String texto) {
		int[] filCol= new int[2];
		int Fil=0;
		int Col=0;
		try {
			File arch= new File(texto);
			Scanner lector= new Scanner(arch);
			while(lector.hasNextLine()) {
				String linea= lector.nextLine();
				if(Fil==0) {
					String[] partes= linea.split(";");
					Col= partes.length;		
				}
				Fil++;	
			}
			filCol[0]=Fil;
			filCol[1]=Col;
			lector.close();
		}catch(FileNotFoundException e) {
			System.out.println("Error no se encontrado el archivo.");
		}
		return filCol;
	}

	
	
}
