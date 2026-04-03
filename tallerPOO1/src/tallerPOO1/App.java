package tallerPOO1;
import java.util.*;
import java.io.*;
//Nombre: Fernando Javier Lincopan Araya
//Rut: 21.860.800-0 
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
				System.out.println("\r\n"+"Acceso correcto!"+"\r\n"+"\r\n"+"Bienvenido "+usu+"!");
				String menuU="";
				while(!menuU.equals("5")){
				System.out.println("\r\n"+"Que deseas realizar?\r\n"+ "1) Registrar actividad.\r\n"+ "2) Modificar actividad.\r\n"+ "3) Eliminar actividad.\r\n"+ "4) Cambiar contraseña.\r\n"+ "5) Salir.");
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
					eleccionUsuario3(usu);
					break;
				case "4":
					eleccionUsuario4(usu);
					break;
				case "5":
					System.out.println("\r\n"+"Cerrando el menu de usuario."+"\r\n");
					break;
				default:
					System.out.println("\r\n"+"seleccione una opcion valida."+"\r\n");
					break;
				}
				}
				break;
			case "2":
				System.out.println("\r\n"+"Usuario incorrecto."+"\r\n");
				break;
			case "3":
				System.out.println("\r\n"+"Contraseña incorrecta."+"\r\n");
				break;
			case "4": 
				System.out.println("\r\n"+"Usuario incorrecto y");
				System.out.println("Contraseña incorrecta."+"\r\n");
				break;
			}
					
			break;
		
		case "2":
			System.out.println("\r\n"+"Bienvenido al menu de analisis!");
			String menuR= "";
			while(!menuR.equals("5")) {
				System.out.println("\r\n"+"Que deseas realizar?\r\n"+ "\r\n"+ "1) Actividad más realizada\r\n"+ "2) Actividad más realizada por cada usuario\r\n"+ "3) Usuario con mayor procrastinacion\r\n"+ "4) Ver todas las actividades\r\n"+ "5) Salir");
				System.out.print(">>> ");
				menuR= respuesta.nextLine();
				switch(menuR) {
				case "1":
					eleccionAnalisis1();
					break;
				case "2":
					eleccionAnalisis2();
					break;
				case "3":
					eleccionAnalisis3();
					break;
				case "4":
					eleccionAnalisis4();
					break;
				case "5":
					System.out.println("\r\n"+"Cerrando el menu de analisis."+"\r\n");
					break;
				default:
					System.out.println("\r\n"+"seleccione una opcion valida.");
					break;
				}
			}
			break;
			
		case "3": 
			System.out.println("Cerrando Sistema...");
			break;	
			
		default:
			System.out.println("\r\n"+"seleccione una opcion valida.");
			break;		
			}
		
		
		
		}
		
		try {
		       BufferedWriter bw = new BufferedWriter(new FileWriter("Usuario.txt"));
		       for(int i=0;i<matrizUsuario.length;i++) {
		        	 bw.write(matrizUsuario[i][0]+";"+matrizUsuario[i][1]);
				     bw.newLine();
		       }
		       bw.close();

		    } catch (IOException e) {
		        System.out.println("Error");
		    }
		try {
		       BufferedWriter bw = new BufferedWriter(new FileWriter("Registros.txt"));
		       for(int i=0;i<matrizRegistros.length;i++) {
		    	   if(i!=matrizRegistros.length-1) {
		        	 bw.write(matrizRegistros[i][0]+";"+matrizRegistros[i][1]+";"+matrizRegistros[i][2]+";"+matrizRegistros[i][3]);
				     bw.newLine();
		    	   }else{
		    		   bw.write(matrizRegistros[i][0]+";"+matrizRegistros[i][1]+";"+matrizRegistros[i][2]+";"+matrizRegistros[i][3]); 
		    	   }
		       }
		       bw.close();

		    } catch (IOException e) {
		        System.out.println("Error");
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
		System.out.println();
		System.out.print("Ingrese fecha(dd/mm/aaaa): ");
		String fecha= respuesta.nextLine();
		if(fechaCorrecta(fecha)){
		System.out.print("Ingrese horas de ocio: ");
		String horasDeOcio= respuesta.nextLine();
		if(numeroCorrecto(horasDeOcio)) {
		System.out.print("Ingrese actividad: ");
		String actividad= respuesta.nextLine();
		
		
				matrizRegistros=registrarActividad(usu, fecha, horasDeOcio , actividad);
			}else {
				System.out.println("\r\n"+"Error: La cadena no es un número entero válido.");
			}
		}else {
			System.out.println("\r\n"+"Error: la fecha ingresada no es valida.");
		}
	}
	
	public static boolean fechaCorrecta(String fecha) {
		boolean correcto= false;
		try {
		String[] partes= fecha.split("/");
		if(Integer.valueOf(partes[0])<=31 && Integer.valueOf(partes[0])>=1) {
			if(Integer.valueOf(partes[1])==3 || Integer.valueOf(partes[1])==1 || Integer.valueOf(partes[1])==5 || Integer.valueOf(partes[1])==7 || Integer.valueOf(partes[1])==10 || Integer.valueOf(partes[1])==8 || Integer.valueOf(partes[1])==12) {
				if(Integer.valueOf(partes[2])<2099) {
					correcto=true;
				}
			}
		}
		if(Integer.valueOf(partes[0])<=30 && Integer.valueOf(partes[0])>=1) {
			if(Integer.valueOf(partes[1])==4 || Integer.valueOf(partes[1])==6 || Integer.valueOf(partes[1])==9 || Integer.valueOf(partes[1])==11) {
				if(Integer.valueOf(partes[2])<2099) {
					correcto=true;
				}
			}
		}
		if(Integer.valueOf(partes[0])<=29 && Integer.valueOf(partes[0])>=1) {
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
		if(Integer.valueOf(fechapartes[0])<10 && Integer.valueOf(fechapartes[1])<10 && !fechapartes[0].equals("01") && !fechapartes[0].equals("02") && !fechapartes[0].equals("03") && !fechapartes[0].equals("04") && !fechapartes[0].equals("05") && !fechapartes[0].equals("06") && !fechapartes[0].equals("07") && !fechapartes[0].equals("08") && !fechapartes[0].equals("09") && !fechapartes[1].equals("01") && !fechapartes[1].equals("02") && !fechapartes[1].equals("03") && !fechapartes[1].equals("04") && !fechapartes[1].equals("05") && !fechapartes[1].equals("06") && !fechapartes[1].equals("07") && !fechapartes[1].equals("08") && !fechapartes[1].equals("09")) {
			fechacorrecta= "0" + String.valueOf(fechapartes[0]) +"/"+ "0"+String.valueOf(fechapartes[1]) + "/"+String.valueOf(fechapartes[2]);
			return fechacorrecta;
		}else if(Integer.valueOf(fechapartes[0])<10 && Integer.valueOf(fechapartes[1])>=10 && !fechapartes[0].equals("01") && !fechapartes[0].equals("02") && !fechapartes[0].equals("03") && !fechapartes[0].equals("04") && !fechapartes[0].equals("05") && !fechapartes[0].equals("06") && !fechapartes[0].equals("07") && !fechapartes[0].equals("08") && !fechapartes[0].equals("09")) {
			fechacorrecta= "0" + String.valueOf(fechapartes[0]) +"/"+String.valueOf(fechapartes[1]) + "/"+String.valueOf(fechapartes[2]);
			return fechacorrecta;
		}else if(Integer.valueOf(fechapartes[0])>=10 && Integer.valueOf(fechapartes[1])<10 && !fechapartes[1].equals("01") && !fechapartes[1].equals("02") && !fechapartes[1].equals("03") && !fechapartes[1].equals("04") && !fechapartes[1].equals("05") && !fechapartes[1].equals("06") && !fechapartes[1].equals("07") && !fechapartes[1].equals("08") && !fechapartes[1].equals("09")) {
			fechacorrecta=String.valueOf(fechapartes[0]) +"/"+ "0"+String.valueOf(fechapartes[1]) + "/"+String.valueOf(fechapartes[2]);
			return fechacorrecta;
		}else {
			fechacorrecta=fecha;
		}
		
		return fechacorrecta;
	}
	
	public static boolean numeroCorrecto(String hora) {
		boolean horasValidas= false;
		try {
			 int valorHora = Integer.valueOf(hora); 
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

		            if(f2 <f1) {
		                String[] aux = newMatriz[k];
		                newMatriz[k] = newMatriz[j];
		                newMatriz[j] = aux;
		            }
			}
			
		}
		System.out.println("\r\n"+"Actividad creada con exito!");
		
		return newMatriz;
	}
	
	public static void eleccionUsuario2(String usu) { 
		System.out.println();
		System.out.println("Cual actividad deseas modificar?");
		System.out.println();
		System.out.println("0) Regresar.");
		int j=1;
		for(int i=0;i<matrizRegistros.length;i++) {
			if(usu.equals(matrizRegistros[i][0])) {
			System.out.println((j)+") "+matrizRegistros[i][0]+";"+matrizRegistros[i][1]+";"+matrizRegistros[i][2]+";"+matrizRegistros[i][3]);
			j++;
			}
		}
		System.out.print(">>> ");
		String indice= respuesta.nextLine();
		try {
			int id= Integer.valueOf(indice);
		
		if(!indice.equals("0") && -1<id && id<j) {
			System.out.println("Que deseas modificar?\r\n"+ "\r\n"+ "0) Regresar.\r\n"+ "1) Fecha\r\n"+ "2) Duración\r\n"+ "3) Tipo de actividad");	
			System.out.print(">>> ");
			String indmodificacion= respuesta.nextLine();
			if(!indmodificacion.equals("0")) { 
				switch(indmodificacion) {
					case "1":
							System.out.print("\r\n"+"Ingrese nueva fecha (dd/mm/aaaa): ");
							break;
					case "2":
						System.out.print("\r\n"+"Ingrese nuevo tiempo de ocio: ");
						break;
					case "3":
						System.out.print("\r\n"+"Ingrese nuevo tipo de actividad:");
						break;

					}
					if(indmodificacion.equals("1") || indmodificacion.equals("2") || indmodificacion.equals("3")) {
						String modificacion= respuesta.nextLine();
						if(!modificacion.equals("0")) {
							if(indmodificacion.equals("1")){
								if(fechaCorrecta(modificacion)) {
									modificarRegistros(indice, indmodificacion,correccionFecha(modificacion), usu);
								}else {
									System.out.println("\r\n"+"Error: La cadena no es un número entero válido.");
								}
							}else if(indmodificacion.equals("2")) { 
								if(numeroCorrecto (modificacion)) {
									modificarRegistros(indice, indmodificacion, modificacion, usu);
								}else {
									System.out.println("\r\n"+"Error: La cadena no es un número entero válido.");
								}
							}else if(indmodificacion.equals("3")){
								modificarRegistros(indice, indmodificacion, modificacion, usu);
							}
					}
				}else{
					System.out.println("\r\n"+"Error: La cadena no es un número entero válido.");
				}
					
			}else if(!indmodificacion.equals("0")){
				System.out.println("\r\n"+"Error: La cadena no es un número entero válido.");
			}
			
		}else if(!indice.equals("0")){
			System.out.println("\r\n"+"Error: La cadena no es un número entero válido.");
			
		}
		}catch(NumberFormatException e) {
			System.out.println("\r\n"+"Error: La cadena no es un número entero válido.");
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
				System.out.println("\r\n"+"Actividad modificada con exito!");
				
				}
				
			}
		
		}
		
		}catch(NumberFormatException e) {
			System.out.println("\r\n"+"Error: La cadena no es un número entero válido.");
		}	
	}
	
	public static void eleccionUsuario3(String usu) {
		System.out.println();
		System.out.println("Cual actividad deseas eliminar?");
		System.out.println();
		System.out.println("0) Regresar.");
		int j=1;
		for(int i=0;i<matrizRegistros.length;i++) {
			if(usu.equals(matrizRegistros[i][0])) {
			System.out.println((j)+") "+matrizRegistros[i][0]+";"+matrizRegistros[i][1]+";"+matrizRegistros[i][2]+";"+matrizRegistros[i][3]);
			j++;
			}
		}
		System.out.print(">>> ");
		String indice= respuesta.nextLine();
		if(numeroCorrecto(indice)){
		matrizRegistros= borrarRegistros(indice, usu);
		
		}
		
		
	}
	
	public static String[][] borrarRegistros(String n,String usuario) {
		String[][] newMatriz= new String[matrizRegistros.length-1][4];
		try {
		int indice= Integer.valueOf(n); 
		int j=0;
		int x=0;
		for(int i=0;i<matrizRegistros.length;i++) {
			if(usuario.equals(matrizRegistros[i][0])) {
				j++;
				if(j==indice) {
					x=i;
				}
				if(j!=indice && i!=matrizRegistros.length-1) {
				newMatriz[i][0]= matrizRegistros[i][0];
				newMatriz[i][1]= matrizRegistros[i][1];
				newMatriz[i][2]= matrizRegistros[i][2];
				newMatriz[i][3]= matrizRegistros[i][3];
				}else if(j!=indice && i==matrizRegistros.length-1) {
					newMatriz[x][0]= matrizRegistros[i][0];
					newMatriz[x][1]= matrizRegistros[i][1];
					newMatriz[x][2]= matrizRegistros[i][2];
					newMatriz[x][3]= matrizRegistros[i][3];
					
				}
			}
			if(!usuario.equals(matrizRegistros[i][0]) &&  i!=matrizRegistros.length-1){
				newMatriz[i][0]= matrizRegistros[i][0];
				newMatriz[i][1]= matrizRegistros[i][1];
				newMatriz[i][2]= matrizRegistros[i][2];
				newMatriz[i][3]= matrizRegistros[i][3];
			}else if(!usuario.equals(matrizRegistros[i][0]) &&  i==matrizRegistros.length-1) {
				newMatriz[x][0]= matrizRegistros[i][0];
				newMatriz[x][1]= matrizRegistros[i][1];
				newMatriz[x][2]= matrizRegistros[i][2];
				newMatriz[x][3]= matrizRegistros[i][3];
				
				
			}
			
		
		}
		
		}catch(NumberFormatException e) {
			System.out.println("\r\n"+"Error: La cadena no es un número entero válido.");
		}
		for(int j=0;j<newMatriz.length-1;j++) {
			for(int k=j+1;k<newMatriz.length;k++) {
				  String[] partes = newMatriz[j][1].split("/");
		            String[] partes2 = newMatriz[k][1].split("/");

		            int f1 = Integer.parseInt(partes[2] + partes[1] + partes[0]);
		            int f2 = Integer.parseInt(partes2[2] + partes2[1] + partes2[0]);

		            if(f2<f1) {
		                String[] aux = newMatriz[k];
		                newMatriz[k] = newMatriz[j];
		                newMatriz[j] = aux;
		            }
			}
			
		}
		System.out.println("\r\n"+ "Actividad borrada con exito!");
		return newMatriz;
	}
		
	public static void eleccionUsuario4(String usu) {
		System.out.print("\r\n"+ "Ingrese nueva contraseña: ");
		String contraN1= respuesta.nextLine();
		System.out.print("Confirme la nueva contraseña: ");
		String contraN2= respuesta.nextLine();
		if(contraN1.equals(contraN2)) {
			cambiarContraseña(usu, contraN1);
		}else {
			System.out.println("\r\n"+"Error: la contraseñas no coincide.");
		}
		
	}
	
	public static void cambiarContraseña(String usu, String contraseña) {
		for(int i=0;i<matrizUsuario.length;i++) {
			if(usu.equals(matrizUsuario[i][0]) && matrizUsuario[i][0]!=null) {
				matrizUsuario[i][1]=contraseña;
				System.out.println("\r\n"+"Contraseña modificada con exito!");
			}
		}
	}
	
	public static void eleccionAnalisis1() {
		String[] listaAnalisisGeneral= analisisGeneral();
		System.out.println("\r\n"+"Actividad más realizada por:\r\n");
		System.out.println("* "+listaAnalisisGeneral[0]+" -> con "+listaAnalisisGeneral[1]+" horas registradas");
		
		
	}
	
	public static String[] analisisGeneral(){
		String[] listaAnalisisGeneral= new String[2];
		String[][] matrizAnalisisRegistros= new String[1][2];
		for(int i=0;i<matrizRegistros.length;i++) {
			if(i!=0) {
				if(existeEnMatriz(matrizAnalisisRegistros, matrizRegistros[i][3])) {
					for (int k = 0; k < matrizAnalisisRegistros.length; k++) {
				        if (matrizAnalisisRegistros[k][0] != null && matrizAnalisisRegistros[k][0].equals(matrizRegistros[i][3])) {
				            String valor=  String.valueOf(Integer.valueOf(matrizAnalisisRegistros[k][1])+Integer.valueOf(matrizRegistros[i][2])) ;
				            matrizAnalisisRegistros[k][1]=valor ; 
				        }
				        
					}
				}else {
					String[][] newmatrizN= new String[matrizAnalisisRegistros.length+1][2];
					for(int z=0;z<matrizAnalisisRegistros.length;z++) {
						newmatrizN[z][0]= matrizAnalisisRegistros[z][0];
						newmatrizN[z][1]= matrizAnalisisRegistros[z][1];
					}
					newmatrizN[newmatrizN.length-1][0]=matrizRegistros[i][3]; 
					newmatrizN[newmatrizN.length-1][1]=matrizRegistros[i][2];
					matrizAnalisisRegistros=newmatrizN;
				}
			}else {
				matrizAnalisisRegistros[i][0]= matrizRegistros[i][3];
				matrizAnalisisRegistros[i][1]= matrizRegistros[i][2]; 
			}
			
			
		
		}
		for(int x=0;x<matrizAnalisisRegistros.length-1;x++) {
			for(int y=x+1;y<matrizAnalisisRegistros.length;y++) {
				if(Integer.valueOf(matrizAnalisisRegistros[x][1])<Integer.valueOf(matrizAnalisisRegistros[y][1])) {
					String aux1= matrizAnalisisRegistros[x][0];
					String aux2= matrizAnalisisRegistros[x][1];
					matrizAnalisisRegistros[x][0]= matrizAnalisisRegistros[y][0];
					matrizAnalisisRegistros[x][1]= matrizAnalisisRegistros[y][1];
					matrizAnalisisRegistros[y][0]= aux1;
					matrizAnalisisRegistros[y][1]= aux2;
				}
			}
		}
		listaAnalisisGeneral[0]=matrizAnalisisRegistros[0][0];
		listaAnalisisGeneral[1]=matrizAnalisisRegistros[0][1];
		return listaAnalisisGeneral;
	}
	
	public static void eleccionAnalisis2(){
		String[][] matrizAnalisisIndividual= analisisIndividual();
		System.out.println("\r\n"+"Actividades mas realizadas por cada usuario:\r\n");
		for(int i=0;i<matrizAnalisisIndividual.length;i++) {
			System.out.println("* "+matrizAnalisisIndividual[i][0]+" -> "+matrizAnalisisIndividual[i][1]+" -> con "+matrizAnalisisIndividual[i][2]+" horas registradas");
		}
	}
	
	public static String[][] analisisIndividual(){
		String[][] listaAnalisisIndividual= new String[matrizUsuario.length][3];
		for(int i=0;i<matrizUsuario.length;i++) {
			int nveces=0;
			String[][] matrizActividadM= new String[1][2];
			String usuarioN= matrizUsuario[i][0];
			for(int j=0;j<matrizRegistros.length;j++) {
				if(usuarioN.equals(matrizRegistros[j][0])) {
					nveces++;
					if(nveces!=1) {
						if(existeEnMatriz(matrizActividadM,matrizRegistros[j][3])) {
							for (int k = 0; k < matrizActividadM.length; k++) {
						        if (matrizActividadM[k][0] != null && matrizActividadM[k][0].equals(matrizRegistros[j][3])) {
						            String valor=  String.valueOf(Integer.valueOf(matrizActividadM[k][1])+Integer.valueOf(matrizRegistros[j][2])) ;
						        	matrizActividadM[k][1]=valor ; 
						        }
						        
							}
						}else {
							String[][] newmatrizN= new String[matrizActividadM.length+1][2];
							for(int z=0;z<matrizActividadM.length;z++) {
								newmatrizN[z][0]= matrizActividadM[z][0];
								newmatrizN[z][1]= matrizActividadM[z][1];
							}
							newmatrizN[newmatrizN.length-1][0]=matrizRegistros[j][3]; 
							newmatrizN[newmatrizN.length-1][1]=matrizRegistros[j][2];
							matrizActividadM=newmatrizN;
						}
					}else {
						matrizActividadM[0][0]=matrizRegistros[j][3]; 
						matrizActividadM[0][1]=matrizRegistros[j][2]; 
					}
				}	
			}
			for(int x=0;x<matrizActividadM.length-1;x++) {
				for(int y=x+1;y<matrizActividadM.length;y++) {
					if(Integer.valueOf(matrizActividadM[x][1])<Integer.valueOf(matrizActividadM[y][1])) {
						String aux1= matrizActividadM[x][0];
						String aux2= matrizActividadM[x][1];
						matrizActividadM[x][0]= matrizActividadM[y][0];
						matrizActividadM[x][1]= matrizActividadM[y][1];
						matrizActividadM[y][0]= aux1;
						matrizActividadM[y][1]= aux2;
					}
				}
			}
		    
			listaAnalisisIndividual[i][0]= usuarioN;
			listaAnalisisIndividual[i][1]= matrizActividadM[0][0];
			listaAnalisisIndividual[i][2]= matrizActividadM[0][1];
			
		}
		return listaAnalisisIndividual;
	}
	
	public static boolean existeEnMatriz(String[][] matriz, String valor) {
	    for (int i = 0; i < matriz.length; i++) {
	        for (int j = 0; j < matriz[i].length; j++) {
	            if (matriz[i][j] != null && matriz[i][j].equals(valor)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	public static void eleccionAnalisis3() {
		String[] listaAnalisisOsioMayor=personasMayorOsio();
		System.out.println("\r\n"+"Persona con mas horas de osio:"+"\r\n"+"* "+listaAnalisisOsioMayor[0]+" -> con "+listaAnalisisOsioMayor[1]+" horas registradas");
	}
	
	public static String[] personasMayorOsio(){
		String[] listaAnalisisOsioMayor= new String[2];
		String[][] MatrizUsu= new String[matrizUsuario.length][2];
		for(int i=0;i<matrizUsuario.length;i++) {
			int nveces=0;
			String usuarioN= matrizUsuario[i][0];
			for(int j=0;j<matrizRegistros.length;j++) {
				if(usuarioN.equals(matrizRegistros[j][0])) {
					nveces++;
					if(nveces!=1){
						int valor= Integer.valueOf(MatrizUsu[i][1])+Integer.valueOf(matrizRegistros[i][2]);
						MatrizUsu[i][1]=String.valueOf(valor); 
					}else {
						MatrizUsu[i][0]=usuarioN;
						MatrizUsu[i][1]=matrizRegistros[i][2];
					}
				}
			}
		    
		}
		for(int x=0; x<MatrizUsu.length-1;x++) {
			for(int y=x+1;y<MatrizUsu.length;y++) {
				if(Integer.valueOf(MatrizUsu[x][1])<Integer.valueOf(MatrizUsu[y][1])) {
					String aux1= MatrizUsu[x][0];
					String aux2= MatrizUsu[x][1];
					MatrizUsu[x][0]= MatrizUsu[y][0];
					MatrizUsu[x][1]= MatrizUsu[y][1];
					MatrizUsu[y][0]= aux1;
					MatrizUsu[y][1]= aux2;
				}
			}
		}
		listaAnalisisOsioMayor[0]=MatrizUsu[0][0];
		listaAnalisisOsioMayor[1]=MatrizUsu[0][1];
		return listaAnalisisOsioMayor;
	}
	
	public static void eleccionAnalisis4() {
		for(int i=0;i<matrizRegistros.length;i++) {
			System.out.println("* "+matrizRegistros[i][1]+" -> "+matrizRegistros[i][0]+" -> "+matrizRegistros[i][3]+" -> con "+matrizRegistros[i][2]+" horas registradas");
		}
		
		
	}
	
}
