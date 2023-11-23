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

	public static void main(String[] args) throws IOException {
		fich = new File("Alumnos.dat");
		raf = new RandomAccessFile(fich, "rw");
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
			System.out.println("4) Consultar alumnos.");
			System.out.println("5) Salir.");
			opcion = sc.next();
			sc.nextLine();

			switch (opcion) {
			case "1":
				System.out.println("Has escogido alta");
				alta();
				break;
			case "2":
				System.out.println("Has escogido baja");
				baja();
				break;
			case "3":
				System.out.println("Has escogido modificacion");
				consultarTodosAlumnos();
				modificar();
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
		} while (opcion != "5");
	}

	private static void modificar() throws IOException {
		char[] nombreChar = new char[Constantes.NUMCHARNOMBRE];
		char[] apellidosChar = new char[Constantes.NUMCHARAPELLIDOS];
		char[] dniChar = new char[Constantes.NUMCHARDNI];
		char[] cicloChar = new char[Constantes.NUMCHARCICLO];
		char aux;
		String dni, apellidos, nombre, ciclo;
		int curso;
		int posicion = 0;
		String dniConsulta;
		StringBuffer buffer;
		System.out.println("Introduce el dni del alumno a modificar.");
		dniConsulta = sc.nextLine();
		while (true) {
			raf.seek(posicion);

			for (int i = 0; i < dniChar.length; i++) {
				aux = raf.readChar();
				dniChar[i] = aux;
			}
			dni = new String(dniChar);

			for (int i = 0; i < apellidosChar.length; i++) {
				aux = raf.readChar();
				apellidosChar[i] = aux;
			}
			apellidos = new String(apellidosChar);

			for (int i = 0; i < nombreChar.length; i++) {
				aux = raf.readChar();
				nombreChar[i] = aux;
			}
			nombre = new String(nombreChar);

			for (int i = 0; i < cicloChar.length; i++) {
				aux = raf.readChar();
				cicloChar[i] = aux;
			}
			ciclo = new String(cicloChar);
			curso = raf.readInt();

			if (dni.equals(dniConsulta)) {
				modificarAlumno(posicion);
				break;
			}

			posicion += Constantes.TAMAÑOREGISTRO;

			if (raf.getFilePointer() == fich.length()) {

				System.out.println("El dni introducido no coincide con ningún alumno.");
				break;
			}

		}

	}

	private static void modificarAlumno(int posicion) throws IOException {
		int posicionOriginal = posicion;
		StringBuffer bufferDni, bufferApellidos, bufferNombre, bufferCiclo;
		int curso;
		String opcion;
		do {

			raf.seek(posicionOriginal);
			System.out.println("Introduce el cambio que quieres realizar.");
			System.out.println("1) DNI");
			System.out.println("2) Apellidos");
			System.out.println("3) Nombre");
			System.out.println("4) Ciclo");
			System.out.println("5) Curso");
			System.out.println("6) Salir");
			System.out.println();
			opcion = sc.nextLine();
			switch (opcion) {
			case "1":
				System.out.println("Introduce un nuevo dni válido");
				String dni = sc.nextLine();
				if (comprobarDNI(dni)) {
					bufferDni = new StringBuffer(dni);
					bufferDni.setLength(Constantes.NUMCHARDNI);
					raf.writeChars(bufferDni.toString());
					System.out.println("Alumno actualizado.");

				} else {
					System.out.println("El dni introducido no es válido.");

				}
				break;
			case "2":
				posicion += Constantes.TAMAÑODNI;
				raf.seek(posicion);
				System.out.println("Introduce los nuevos apellidos");
				String apellidos = sc.nextLine();
				if (comprobarNomApellCiclo(apellidos)) {
					bufferApellidos = new StringBuffer(apellidos);
					bufferApellidos.setLength(Constantes.NUMCHARAPELLIDOS);
					raf.writeChars(bufferApellidos.toString());
					System.out.println("Alumno actualizado.");
				} else {
					System.out.println("El o los apellidos no son válidos.");
				}
				posicion = posicionOriginal;
				break;
			case "3":
				posicion += Constantes.TAMAÑODNI + Constantes.TAMAÑOAPELLIDOS;
				raf.seek(posicion);
				System.out.println("Introduce el nuevo nombre.");
				String nombre = sc.nextLine();
				if (comprobarNomApellCiclo(nombre)) {
					bufferNombre = new StringBuffer(nombre);
					bufferNombre.setLength(Constantes.NUMCHARNOMBRE);
					raf.writeChars(bufferNombre.toString());
					System.out.println("Alumno actualizado.");
				} else {
					System.out.println("El nombre introducido no es válido.");
				}
				posicion = posicionOriginal;
				break;
			case "4":
				posicion += Constantes.TAMAÑODNI + Constantes.TAMAÑOAPELLIDOS + Constantes.TAMAÑONOMBRE;
				raf.seek(posicion);
				System.out.println("Introduce el nuevo ciclo.");
				String ciclo = sc.nextLine();
				if (comprobarNomApellCiclo(ciclo)) {
					bufferCiclo = new StringBuffer(ciclo);
					bufferCiclo.setLength(Constantes.NUMCHARCICLO);
					raf.writeChars(bufferCiclo.toString());
					System.out.println("Alumno actualizado.");
				} else {
					System.out.println("El ciclo introducido no es válido.");
				}
				posicion = posicionOriginal;
				break;
			case "5":
				posicion += Constantes.TAMAÑODNI + Constantes.TAMAÑOAPELLIDOS + Constantes.TAMAÑONOMBRE
						+ Constantes.TAMAÑOCICLO;
				raf.seek(posicion);
				System.out.println("Introduce el nuevo curso.");
				curso = sc.nextInt();
				sc.nextLine();

				raf.writeInt(curso);
				System.out.println("Alumno actualizado.");

				posicion = posicionOriginal;
				break;

			}
		} while (!opcion.equals("6"));

	}

	private static void baja() throws IOException {
		char[] nombreChar = new char[Constantes.NUMCHARNOMBRE];
		char[] apellidosChar = new char[Constantes.NUMCHARAPELLIDOS];
		char[] dniChar = new char[Constantes.NUMCHARDNI];
		char[] cicloChar = new char[Constantes.NUMCHARCICLO];
		char aux;
		String dni, apellidos, nombre, ciclo;
		int curso;
		int posicion = 0;
		String dniConsulta;
		StringBuffer buffer;
		System.out.println("Introduce el dni del alumno a consultar.");
		dniConsulta = sc.nextLine();
		while (true) {
			raf.seek(posicion);

			for (int i = 0; i < dniChar.length; i++) {
				aux = raf.readChar();
				dniChar[i] = aux;
			}
			dni = new String(dniChar);
			
			if (dniConsulta.equals(dni)) {
				raf.seek(posicion);
				buffer = new StringBuffer("0");
				buffer.setLength(9);
				raf.writeChars(buffer.toString());
				System.out.println("Alumno eliminado.");
				break;
			}

			for (int i = 0; i < apellidosChar.length; i++) {
				aux = raf.readChar();
				apellidosChar[i] = aux;
			}
			apellidos = new String(apellidosChar);

			for (int i = 0; i < nombreChar.length; i++) {
				aux = raf.readChar();
				nombreChar[i] = aux;
			}
			nombre = new String(nombreChar);

			for (int i = 0; i < cicloChar.length; i++) {
				aux = raf.readChar();
				cicloChar[i] = aux;
			}
			ciclo = new String(cicloChar);
			curso = raf.readInt();

			

			posicion += Constantes.TAMAÑOREGISTRO;

			if (raf.getFilePointer() == fich.length()) {

				System.out.println("El dni introducido no coincide con ningún alumno.");
				break;
			}

		}

	}

	private static void consultar() throws IOException {
		String opcion;
		do {
			System.out.println("Introduce una opción de consulta:");
			System.out.println("1) Mostrar todos los alumnos.");
			System.out.println("2) Mostrar un alumno en concreto.");
			System.out.println("3) Salir.");
			opcion = sc.nextLine();

			switch (opcion) {
			case "1":
				consultarTodosAlumnos();
				break;
			case "2":
				consultarAlumno();
				break;
			case "3":
				System.out.println("Volviendo a menú principal.\n");
				break;
			default:
				System.out.println("Introduce una opción válida, por favor.\n");
				break;
			}

		} while (!opcion.equals("3"));

	}

	private static void consultarAlumno() throws IOException {
		char[] nombreChar = new char[Constantes.NUMCHARNOMBRE];
		char[] apellidosChar = new char[Constantes.NUMCHARAPELLIDOS];
		char[] dniChar = new char[Constantes.NUMCHARDNI];
		char[] cicloChar = new char[Constantes.NUMCHARCICLO];
		char aux;
		String dni, apellidos, nombre, ciclo;
		int curso;
		int posicion = 0;
		String dniConsulta;
		System.out.println("Introduce el dni del alumno a consultar.");
		dniConsulta = sc.nextLine();
		while (true) {
			raf.seek(posicion);

			for (int i = 0; i < dniChar.length; i++) {
				aux = raf.readChar();
				dniChar[i] = aux;
			}
			dni = new String(dniChar);

			for (int i = 0; i < apellidosChar.length; i++) {
				aux = raf.readChar();
				apellidosChar[i] = aux;
			}
			apellidos = new String(apellidosChar);

			for (int i = 0; i < nombreChar.length; i++) {
				aux = raf.readChar();
				nombreChar[i] = aux;
			}
			nombre = new String(nombreChar);

			for (int i = 0; i < cicloChar.length; i++) {
				aux = raf.readChar();
				cicloChar[i] = aux;
			}
			ciclo = new String(cicloChar);
			curso = raf.readInt();

			posicion += Constantes.TAMAÑOREGISTRO;

			if (raf.getFilePointer() == fich.length()) {
				if (dni.equals(dniConsulta)) {

					System.out.println("DNI: " + dni.trim());
					System.out.println("Apellidos: " + apellidos.trim());
					System.out.println("Nombre: " + nombre.trim());
					System.out.println("Ciclo: " + ciclo.trim());
					System.out.println("Curso: " + curso);
					System.out.println();
					break;
				} else {
					System.out.println("El dni introducido no coincide con ningún alumno registrado.\n");
					break;
				}
			}

		}
	}

	private static void consultarTodosAlumnos() throws IOException {
		char[] nombreChar = new char[Constantes.NUMCHARNOMBRE];
		char[] apellidosChar = new char[Constantes.NUMCHARAPELLIDOS];
		char[] dniChar = new char[Constantes.NUMCHARDNI];
		char[] cicloChar = new char[Constantes.NUMCHARCICLO];
		char aux;
		String dni, apellidos, nombre, ciclo;
		int curso;
		int posicion = 0;

		while (true) {
			raf.seek(posicion);

			for (int i = 0; i < dniChar.length; i++) {
				aux = raf.readChar();
				dniChar[i] = aux;
			}
			dni = new String(dniChar);

			for (int i = 0; i < apellidosChar.length; i++) {
				aux = raf.readChar();
				apellidosChar[i] = aux;
			}
			apellidos = new String(apellidosChar);

			for (int i = 0; i < nombreChar.length; i++) {
				aux = raf.readChar();
				nombreChar[i] = aux;
			}
			nombre = new String(nombreChar);

			for (int i = 0; i < cicloChar.length; i++) {
				aux = raf.readChar();
				cicloChar[i] = aux;
			}
			ciclo = new String(cicloChar);
			curso = raf.readInt();

			if (!dni.trim().equals("0")) {
				System.out.println("DNI: " + dni.trim());
				System.out.println("Apellidos: " + apellidos.trim());
				System.out.println("Nombre: " + nombre.trim());
				System.out.println("Ciclo: " + ciclo.trim());
				System.out.println("Curso: " + curso);
				System.out.println();
			}

			posicion += Constantes.TAMAÑOREGISTRO;

			if (raf.getFilePointer() == fich.length()) {
				break;
			}
		}

	}

	private static void alta() throws IOException {
		raf.seek(fich.length());
		StringBuffer bufferNombre, bufferApellidos, bufferDni, bufferCiclo;
		String nombre, apellidos, dni, ciclo;
		int curso;
		System.out.println("Alta de usuario.");

		System.out.println("Introduce el nombre");
		nombre = sc.nextLine();
	
		bufferNombre = new StringBuffer(nombre);
		bufferNombre.setLength(Constantes.NUMCHARNOMBRE);

		System.out.println("Introduce los apellidos");
		apellidos = sc.nextLine();
		bufferApellidos = new StringBuffer(apellidos);
		bufferApellidos.setLength(Constantes.NUMCHARAPELLIDOS);

		System.out.println("Introduce el DNI");
		dni = sc.nextLine();
		
		if (!comprobarDNI(dni)) {
			System.out.println("Dni no válido");
			menuPrincipal();

		}

		bufferDni = new StringBuffer(dni);
		bufferDni.setLength(Constantes.NUMCHARDNI);

		System.out.println("Introduce el ciclo");
		ciclo = sc.nextLine();
	
		bufferCiclo = new StringBuffer(ciclo);
		bufferCiclo.setLength(Constantes.NUMCHARCICLO);

		System.out.println("Introduce el curso");
		curso = sc.nextInt();
		sc.nextLine();

		raf.writeChars(bufferDni.toString());
		raf.writeChars(bufferApellidos.toString());
		raf.writeChars(bufferNombre.toString());
		raf.writeChars(bufferCiclo.toString());
		raf.writeInt(curso);

	}

	private static boolean comprobarNomApellCiclo(String valor) {
		String patronValido = "^[a-zA-Z\\s]+$";
		Pattern pattern = Pattern.compile(patronValido);
		Matcher matcher = pattern.matcher(valor);
		return matcher.matches();
	}

	static boolean comprobarDNI(String dni) {
		String patronValido = "\\d{8}[A-HJ-NP-TV-Z]";
		Pattern pattern = Pattern.compile(patronValido);
		Matcher matcher = pattern.matcher(dni);

		return matcher.matches();
	}
}
