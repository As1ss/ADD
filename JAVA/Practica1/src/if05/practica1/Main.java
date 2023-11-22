package if05.practica1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	static File fich;
	static RandomAccessFile raf;
	static Scanner sc;
	public static void main(String[]args) throws IOException{
		fich = new File("Alumnos.dat");
		raf = new RandomAccessFile(fich,"rw");
		sc = new Scanner(System.in);
		
		menuPrincipal();
		
	
		raf.close();
	}
	private static void menuPrincipal() throws IOException {
		String opcion;
		do {
			System.out.println("Gestión de alumnado.");
			System.out.println("1) Realizar alta de alumno.");
			System.out.println("2) Realizar baja de alumno.");
			System.out.println("3) Modificar alumno.");
			System.out.println("4) Consultar aluno.");
			System.out.println("5) Salir.");
			opcion=sc.next();
			sc.nextLine();
			
		
			
			
			switch(opcion) {
			case "1":
				System.out.println("Has escogido alta");
				alta();
				break;
			case "2":
				System.out.println("Has escogido baja");
				break;
			case "3":
				System.out.println("Has escogido modificacion");
				break;
			case "4":
				System.out.println("Has escogido Consulta");
				consultar();
				break;
			case "5":
				System.out.println("Has escogido salir.");
				break;
			default:
				System.out.println("Escoge una opción válida.");
				break;
			}
		}while(opcion!="5");
	}
	
	
	
	private static void consultar() throws IOException {
		int opcion;
		do {
			System.out.println("Introduce una opción de consulta:");
			System.out.println("1) Mostrar todos los alumnos.");
			consultarTodosAlumnos();
			System.out.println("2) Mostrar un alumno en concreto.");
			System.out.println("3) Salir.");
			opcion = sc.nextInt();
			sc.nextLine();
			
		}while(opcion==3);
		
	}
	private static void consultarTodosAlumnos() throws IOException{
		char[] nombreChar = new char[20];
		char [] apellidosChar =new char[30];
		char [] dniChar = new char[9];
		char [] cicloChar = new char[5];
		char aux;
		String dni,apellidos,nombre,ciclo;
		int curso;
		int posicion=0;
		
		while(true) {
			raf.seek(posicion);
			
			for (int i=0;i<dniChar.length;i++) {
				aux=raf.readChar();
				dniChar[i]=aux;
			}
			dni = new String(dniChar);
			
			for (int i=0;i<apellidosChar.length;i++) {
				aux=raf.readChar();
				apellidosChar[i]=aux;
			}
			apellidos=new String(apellidosChar);
			
		
			
			for (int i = 0;i<nombreChar.length;i++) {
				aux=raf.readChar();
				nombreChar[i]=aux;
			}
			nombre= new String(nombreChar);
			
			for (int i =0;i<cicloChar.length;i++) {
				aux=raf.readChar();
				cicloChar[i]=aux;
			}
			ciclo=new String(cicloChar);
			curso=raf.readInt();
		
		
			
			System.out.println("DNI: "+dni.trim());
			System.out.println("Apellidos: "+apellidos.trim());
			System.out.println("Nombre: "+nombre.trim());
			System.out.println("Ciclo: "+ciclo.trim());
			System.out.println("Curso: "+curso);
			System.out.println();
			
			posicion+=Constantes.TAMAÑOREGISTRO;
			
			if (raf.getFilePointer()==fich.length()) {
				break;
			}
		}
		
		
		
	}
	private static void alta() throws IOException{
		raf.seek(fich.length());
		StringBuffer bufferNombre,bufferApellidos,bufferDni,bufferCiclo;
		String nombre,apellidos,dni,ciclo;
		int curso;
		System.out.println("Alta de usuario.");

		System.out.println("Introduce el nombre");
		nombre = sc.next();
		sc.nextLine();
		bufferNombre=new StringBuffer(nombre);
		bufferNombre.setLength(20);

		
		System.out.println("Introduce los apellidos");
		apellidos = sc.nextLine();
		bufferApellidos=new StringBuffer(apellidos);
		bufferApellidos.setLength(30);
	
		
		System.out.println("Introduce el DNI");
		dni = sc.next();
		sc.nextLine();
		if (!comprobarDNI(dni)) {
			System.out.println("Dni no válido");
			menuPrincipal();
			
		}
		
		bufferDni= new StringBuffer(dni);
		bufferDni.setLength(9);

		
		System.out.println("Introduce el ciclo");
		ciclo = sc.next();
		sc.nextLine();
		bufferCiclo = new StringBuffer(ciclo);
		bufferCiclo.setLength(5);

		
		System.out.println("Introduce el curso");
		curso = sc.nextInt();
		sc.nextLine();
		
		raf.writeChars(bufferDni.toString());
		raf.writeChars(bufferApellidos.toString());
		raf.writeChars(bufferNombre.toString());
		raf.writeChars(bufferCiclo.toString());
		raf.writeInt(curso);
		
		

		
		
		
		
	}
	 static boolean comprobarDNI(String dni) {
	        String patronValido = "\\d{8}[A-HJ-NP-TV-Z]";
	        Pattern pattern = Pattern.compile(patronValido);
	        Matcher matcher = pattern.matcher(dni);

	        return matcher.matches();
	    }
}
