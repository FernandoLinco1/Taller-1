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
		switch(decicion) {
		case "1":			
			System.out.print("Ingrese Usuario: ");
			String usu= respuesta.nextLine();
			System.out.print("Ingrese contraseña: ");
			String contra= respuesta.nextLine();
			String verificacion=verificadorUsuario(usu, contra); 
			switch(verificacion) {
			case "1":
				System.out.println();
				System.out.println("Acceso correcto!");
				System.out.println();
				System.out.println("Bienvenido "+usu+"!");
				System.out.println();
				String menuU="";
				while(!menuU.equals("5")){
				System.out.println("Que deseas realizar?\r\n"
						+ "1) Registrar actividad.\r\n"
						+ "2) Modificar actividad.\r\n"
						+ "3) Eliminar actividad.\r\n"
						+ "4) Cambiar contraseña.\r\n"
						+ "5) Salir.");
				System.out.print(">>> ");
				menuU= respuesta.nextLine();
				switch(menuU) {
				case "1":
					eleccionUsuario1(usu);
					break;
				case "2":
					eleccionUsuario2(usu);
					break;
				case "3":
					break;
				case "4":
					break;
				case "5":
					System.out.println();
					System.out.println("Cerrando el menu de usuario.");
					System.out.println();
					break;
				default:
					System.out.println();
					System.out.println("seleccione una opcion valida.");
					System.out.println();
					break;
				}
				}
				break;
			case "2":
				System.out.println();
				System.out.println("Usuario incorrecto.");
				System.out.println();
				break;
			case "3":
				System.out.println();
				System.out.println("Contraseña incorrecta.");
				System.out.println();
				break;
			case "4": 
				System.out.println();
				System.out.println("Usuario incorrecto y");
				System.out.println("Contraseña incorrecta.");
				System.out.println();
				break;
			}
					
			break;
		
		case "2":
			break;
			
		case "3": 
			System.out.println("Cerrando Sistema...");
			break;	
			
		default:
			System.out.println("seleccione una opcion valida.");
			System.out.println();
			break;		
			}
		
		
		
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

	public static String verificadorUsuario(String usuario,String contraseña) {
		String verificacion="";
		for(int i=0;i<matrizUsuario.length;i++) {

			if(matrizUsuario[i][0].equals(usuario) && matrizUsuario[i][1].equals(contraseña)){
				verificacion="1";
				break;
		    }
			if(!matrizUsuario[i][0].equals(usuario) && matrizUsuario[i][1].equals(contraseña)){
				verificacion="2";
				break;
		    }
			if(matrizUsuario[i][0].equals(usuario) && !matrizUsuario[i][1].equals(contraseña)){
				verificacion="3";
				break;
		    }
			if(!matrizUsuario[i][0].equals(usuario) && !matrizUsuario[i][1].equals(contraseña)){
				verificacion="4";
		    }
		}
		return verificacion;
	}
	
	public static void eleccionUsuario1(String usu) {
		System.out.print("Ingrese fecha(dd/mm/aaaa): ");
		String fecha= respuesta.nextLine();
		if(fechaCorrecta(fecha)){
		System.out.print("Ingrese horas de ocio: ");
		String horasDeOcio= respuesta.nextLine();
		if(horasCorrectas(horasDeOcio)) {
		System.out.print("Ingrese actividad: ");
		String actividad= respuesta.nextLine();
		
		
				matrizRegistros=registrarActividad(usu, fecha, horasDeOcio , actividad);
			}else {
				System.out.println();
				System.out.println("Error: La cadena no es un número entero válido.");
				System.out.println();
			}
		}else {
			System.out.println();
			System.out.println("Error: la fecha ingresada no es valida.");
			System.out.println();
		}
	}
	
	public static boolean fechaCorrecta(String fecha) {
		boolean correcto= false;
		try {
		String[] partes= fecha.split("/");
		if(Integer.valueOf(partes[0])<=31 || Integer.valueOf(partes[0])>=1) {
			if(Integer.valueOf(partes[1])==3 || Integer.valueOf(partes[1])==1 || Integer.valueOf(partes[1])==5 || Integer.valueOf(partes[1])==7 || Integer.valueOf(partes[1])==10 || Integer.valueOf(partes[1])==8 || Integer.valueOf(partes[1])==12) {
				if(Integer.valueOf(partes[2])<2099) {
					correcto=true;
				}
			}
		}
		if(Integer.valueOf(partes[0])<=30 || Integer.valueOf(partes[0])>=1) {
			if(Integer.valueOf(partes[1])==4 || Integer.valueOf(partes[1])==6 || Integer.valueOf(partes[1])==9 || Integer.valueOf(partes[1])==11) {
				if(Integer.valueOf(partes[2])<2099) {
					correcto=true;
				}
			}
		}
		if(Integer.valueOf(partes[0])<=29 || Integer.valueOf(partes[0])>=1) {
			if(Integer.valueOf(partes[1])==2) {
				if(Integer.valueOf(partes[2])<2099) {
					correcto=true;
				}
			}
		}
		}catch(NumberFormatException e) {}
		return correcto;
	}
	
	public static String correccionFecha(String fecha) {
		String[] fechapartes= fecha.split("/");
		String fechacorrecta="";
		if(Integer.valueOf(fechapartes[0])<10 && Integer.valueOf(fechapartes[1])<10) {
			fechacorrecta= "0" + String.valueOf(fechapartes[0]) +"/"+ "0"+String.valueOf(fechapartes[1]) + "/"+String.valueOf(fechapartes[2]);
		}else if(Integer.valueOf(fechapartes[0])<10 && Integer.valueOf(fechapartes[1])>=10) {
			fechacorrecta= "0" + String.valueOf(fechapartes[0]) +"/"+String.valueOf(fechapartes[1]) + "/"+String.valueOf(fechapartes[2]);
		}else if(Integer.valueOf(fechapartes[0])>=10 && Integer.valueOf(fechapartes[1])<10) {
			fechacorrecta=String.valueOf(fechapartes[0]) +"/"+ "0"+String.valueOf(fechapartes[1]) + "/"+String.valueOf(fechapartes[2]);
		}
		
		return fechacorrecta;
	}
	
	public static boolean horasCorrectas(String hora) {
		boolean horasValidas= false;
		try {
			 int valorHora = Integer.parseInt(hora); 
			 if(valorHora>0) {
				 horasValidas=true;
			 }
		}catch(NumberFormatException e) {}
		return horasValidas;
	}
	
	public static String[][] registrarActividad(String usuario, String fecha, String horas, String actividades){
		String[][] newMatriz= new String[(matrizRegistros.length)+1][4] ;
		int n=0;
		
		for(int i=0; i<matrizRegistros.length;i++) {
			newMatriz[i][0]= matrizRegistros[i][0];
			newMatriz[i][1]= matrizRegistros[i][1];
			newMatriz[i][2]= matrizRegistros[i][2];
			newMatriz[i][3]= matrizRegistros[i][3];
		}
		n=newMatriz.length-1;
		newMatriz[n][0]= usuario;
		newMatriz[n][1]= correccionFecha(fecha);
		newMatriz[n][2]= horas;
		newMatriz[n][3]= actividades;
		
		for(int j=0;j<newMatriz.length-1;j++) {
			for(int k=j+1;k<newMatriz.length;k++) {
				  String[] partes = newMatriz[j][1].split("/");
		            String[] partes2 = newMatriz[k][1].split("/");

		            int f1 = Integer.parseInt(partes[2] + partes[1] + partes[0]);
		            int f2 = Integer.parseInt(partes2[2] + partes2[1] + partes2[0]);

		            if(f2 <= f1) {
		                String[] aux = newMatriz[k];
		                newMatriz[k] = newMatriz[j];
		                newMatriz[j] = aux;
		                n = j;
		            }
			}
			
		}
		System.out.println();
		System.out.println("Actividad creada con exito!");
		System.out.println();
		
		return newMatriz;
	}
	
	public static void eleccionUsuario2(String usu) { 
		System.out.println("Cual actividad deseas modificar?");
		System.out.println();
		System.out.println("0) Regresar.");
		int j=1;
		for(int i=0;i<matrizRegistros.length;i++) {
			if(matrizRegistros[i][0].equals(usu)) {
			System.out.println((j)+") "+matrizRegistros[i][0]+";"+matrizRegistros[i][1]+";"+matrizRegistros[i][2]+";"+matrizRegistros[i][3]);
			j++;
			}
		}
		System.out.print(">>> ");
		String indice= respuesta.nextLine();
		try {
			int id= Integer.valueOf(indice);
		
		if(!indice.equals("0") && 0<id && id<j) {
			System.out.println("System.out.println(\"Que deseas modificar?\");\r\n"
					+ "		System.out.println();\r\n"
					+ "		System.out.println(\"0) Regresar.\");\r\n"
					+ "		System.out.println(\"1) Fecha\");\r\n"
					+ "		System.out.println(\"2) Duracion\");\r\n"
					+ "		System.out.println(\"3) Tipo de actividad\");");	
			System.out.print(">>> ");
			String indmodificacion= respuesta.nextLine();
			if(!indmodificacion.equals("0")) { 
					printModificarRegistro(indmodificacion);
					if(indmodificacion.equals("1") && indmodificacion.equals("2") && indmodificacion.equals("3")) {
						String modificacion= respuesta.nextLine();
						if(!modificacion.equals("0")) {
							if(indmodificacion.equals("1")){
								if(fechaCorrecta(modificacion)) {
									modificarRegistros(indice, indmodificacion,correccionFecha(modificacion), usu);
								}
							}else if(indmodificacion.equals("2")) { 
								if(horasCorrectas(modificacion)) {
									modificarRegistros(indice, indmodificacion, modificacion, usu);
								}
							}else if(indmodificacion.equals("3")){
								modificarRegistros(indice, indmodificacion, modificacion, usu);
							}
					}
				}else {
					System.out.println();
					System.out.println("Error: La cadena no es un número entero válido.");
					System.out.println();
				}
			}else {
				System.out.println();
				System.out.println("Error: La cadena no es un número entero válido.");
				System.out.println();
			}
		}else {
			System.out.println();
			System.out.println("Error: La cadena no es un número entero válido.");
			System.out.println();
			
		}
		}catch(NumberFormatException e) {
			System.out.println();
			System.out.println("Error: La cadena no es un número entero válido.");
			System.out.println();
		}
	}
	
	
	public static void modificarRegistros(String n,String m ,String modificacion ,String usuario) {
		try {
		int indice= Integer.valueOf(n);
		int indModificacion= Integer.valueOf(m);  
		int j=0;
		for(int i=0;i<matrizRegistros.length;i++) {
			if(matrizRegistros[i][0].equals(usuario)) {
				j++;
				if(j==indice) {
				matrizRegistros[i][indModificacion]=modificacion;
				System.out.println("Actividad modificada con exito!");
				}
				
			}
		
		}
		
		}catch(NumberFormatException e) {
			System.out.println("Error: La cadena no es un número entero válido.");
		}	
	}
	
	public static void printModificarRegistro(String indModificacion) {
		
		switch(indModificacion) {
		case "1":
				System.out.print("Ingrese nueva fecha (dd/mm/aaaa): ");
				break;
		case "2":
			System.out.print("Ingrese nuevo tiempo de ocio: ");
			break;
		case "3":
			System.out.print("Ingrese nuevo tipo de actividad:");
			break;

		}
		
	}
	
	public static void printModificarIndice(String usuario) {
		System.out.println("Cual actividad deseas modificar?");
		System.out.println();
		System.out.println("0) Regresar.");
		int j=1;
		for(int i=0;i<matrizRegistros.length;i++) {
			if(matrizRegistros[i][0].equals(usuario)) {
			System.out.println((j)+") "+matrizRegistros[i][0]+";"+matrizRegistros[i][1]+";"+matrizRegistros[i][2]+";"+matrizRegistros[i][3]);
			j++;
			}
		}
		System.out.print(">>> ");
	}
	
}
