package if05.practica1;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	// Declaramos las variables estaticas que vamos a utilizar por toda la clase
	static File fich;
	static RandomAccessFile raf;
	static Scanner sc;

	public static void main(String[] args) throws IOException {
		// Inicializamos las variables
		fich = new File("Alumnos.dat");// Fichero con el que vamos a trabajar
		raf = new RandomAccessFile(fich, "rw"); // Clase con el que vamos a leer y escribir
		sc = new Scanner(System.in); // Scanner para recoger las entradas de los usuarios.

		// Iniciamos el menu principal con todas las operaciones disponibles.
		menuPrincipal();

		// Cerramos el fichero.
		raf.close();
	}

	private static void menuPrincipal() throws IOException {
		String opcion;
		do {
			System.out.println("✪    GESTIÓN DEL ALUMNADO    ✪");
			System.out.println("1) Realizar creación de alumno.");
			System.out.println("2) Realizar eliminación de alumno.");
			System.out.println("3) Modificar alumno.");
			System.out.println("4) Consultar alumnos.");
			System.out.println("5) Salir.");
			opcion = sc.next();
			sc.nextLine();

			switch (opcion) {
			case "1":
				System.out.println("Has escogido creación de alumno.");
				alta();
				break;
			case "2":
				System.out.println("Has escogido eliminación de alumno");
				consultarTodosAlumnos();// Mostramos la lista de todos los alumnos para poder identificar
				// al alumno que queremos dar de baja.
				baja();
				break;
			case "3":
				System.out.println("Has escogido modificacion de alumno.");
				consultarTodosAlumnos();// Mostramos la lista de todos los alumnos para poder identificar
				// al alumno que queremos modificar.
				modificar();
				break;
			case "4":
				System.out.println("Has escogido consultar alumnos.");
				consultarMenu();
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
		char[] dniChar = new char[Constantes.NUMCHARDNI];
		char aux;
		String dni;
		int posicion = 0;
		String dniConsulta;
		System.out.println("✪    MODIFICAR ALUMNO    ✪");
		System.out.println("Introduce el dni del alumno a modificar.");
		dniConsulta = sc.nextLine();
		while (true) {
			raf.seek(posicion);

			for (int i = 0; i < dniChar.length; i++) {
				aux = raf.readChar();//Leemos y almacenamos en esta variable los caracteres leidso
				dniChar[i] = aux; // Los almacenamos en un array de caracteres
			}
			dni = new String(dniChar);//Creamos un string con el array de caracteres

			if (dni.equals(dniConsulta)) {
				modificarAlumno(posicion);//Le pasamos la posición del registro con el que ha 
				//coincidido el dni.
				break;
			}

			posicion += Constantes.TAMAÑOREGISTRO;//Sumamos la posicion del puntero para leer
			//el siguiente registro

			if (raf.getFilePointer() == fich.length()) {//SI el puntero llega al final del fichero

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

			raf.seek(posicionOriginal); //Establecemos una posicion original que le hemos
			//pasado por parametro para poder volver al principio del registro
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
					System.out.println("¿Estás seguro de realizar el cambio de dni?");
					if (confirmarCambios()) {
						bufferDni = new StringBuffer(dni);
						bufferDni.setLength(Constantes.NUMCHARDNI);
						raf.writeChars(bufferDni.toString());
						System.out.println("Alumno actualizado.");
					}

				} else {
					System.out.println("El dni introducido no es válido.");

				}
				break;
			case "2":
				posicion += Constantes.TAMAÑODNI;//Sumamos el tamaño del dni para avanzar el
				//puntero hasta la parte de modificar el apellido
				raf.seek(posicion);
				System.out.println("Introduce los nuevos apellidos");
				String apellidos = sc.nextLine();
				if (comprobarNomApellCiclo(apellidos)) {
					System.out.println("¿Estás seguro de realizar el cambio de los apellidos?");
					if (confirmarCambios()) {
						bufferApellidos = new StringBuffer(apellidos);
						bufferApellidos.setLength(Constantes.NUMCHARAPELLIDOS);
						raf.writeChars(bufferApellidos.toString());
						System.out.println("Alumno actualizado.");
					}
				} else {
					System.out.println("El o los apellidos no son válidos.");
				}
				posicion = posicionOriginal;//Reiniciamos la posición del puntero
				break;
			case "3":
				posicion += Constantes.TAMAÑODNI + Constantes.TAMAÑOAPELLIDOS;
				raf.seek(posicion);
				System.out.println("Introduce el nuevo nombre.");
				String nombre = sc.nextLine();
				if (comprobarNomApellCiclo(nombre)) {
					System.out.println("¿Estás seguro de realizar el cambio de nombre?");
					if (confirmarCambios()) {
						bufferNombre = new StringBuffer(nombre);
						bufferNombre.setLength(Constantes.NUMCHARNOMBRE);
						raf.writeChars(bufferNombre.toString());
						System.out.println("Alumno actualizado.");
					}
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
					System.out.println("¿Estás seguro de realizar el cambio de ciclo?");
					if (confirmarCambios()) {
						bufferCiclo = new StringBuffer(ciclo);
						bufferCiclo.setLength(Constantes.NUMCHARCICLO);
						raf.writeChars(bufferCiclo.toString());
						System.out.println("Alumno actualizado.");
					}
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

				System.out.println("¿Estás seguro de realizar el cambio de curso?");
				if (confirmarCambios()) {

					raf.writeInt(curso);
					System.out.println("Alumno actualizado.");
				}

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
		System.out.println("\n✪    ELIMINAR ALUMNO   ✪");
		System.out.println("Introduce el dni del alumno a eliminar.");
		dniConsulta = sc.nextLine();//Leemos en una variable temporal para comparar el dni leido
		//introducida por el usuari
		while (true) {
			raf.seek(posicion);//Establecemos la posicion desde el principio que luego aumentaremos

			for (int i = 0; i < dniChar.length; i++) {
				aux = raf.readChar();
				dniChar[i] = aux;
			}
			dni = new String(dniChar);//Leemos y guardamos el dni del registro

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

			if (dniConsulta.equals(dni)) {//Comparamos valores de los dni

				System.out.println("Apellidos: " + apellidos.trim());
				System.out.println("Nombre: " + nombre.trim());
				System.out.println("DNI: " + dni.trim());
				System.out.println("Ciclo: " + ciclo.trim());
				System.out.println("Curso: " + curso);
				System.out.println("Estas seguro de eliminar este alumno?");//Mostramos el usuario antes de borrarlo

				if (confirmarCambios()) {//Pedimos confirmación de eliminacion
					raf.seek(posicion);
					buffer = new StringBuffer("0");//Asignamos este valor al campo dni
					buffer.setLength(Constantes.NUMCHARDNI);//Establecemos el tamaño del buffer
					raf.writeChars(buffer.toString());//Sobreescribimos el dni con el valor 0
					System.out.println("Alumno eliminado.");//Mostramos un mensaje para hacer saber que se ha
					//realizado la acción.
				}

				break;//Salimos del bucle
			}

			posicion += Constantes.TAMAÑOREGISTRO;//Sumamos posicion del puntero

			if (raf.getFilePointer() == fich.length()) {

				System.out.println("El dni introducido no coincide con ningún alumno.");
				break;
			}

		}

	}

	private static void consultarMenu() throws IOException {
		String opcion;
		do {
			System.out.println("✪    CONSULTAR ALUMNOS    ✪");
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

	private static void consultarAlumno(){
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
		try {
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

					System.out.println("DNI: " + dni.trim());
					System.out.println("Apellidos: " + apellidos.trim());
					System.out.println("Nombre: " + nombre.trim());
					System.out.println("Ciclo: " + ciclo.trim());
					System.out.println("Curso: " + curso);
					System.out.println();
					break;

				}

				posicion += Constantes.TAMAÑOREGISTRO;

				if (raf.getFilePointer() == fich.length()) {
					System.err.println("Alumno no encontrado o el dni no es válido.");
				}

			}
		} catch (EOFException e) {
			System.out.println();

		} catch (IOException e) {
			System.err.println("Ha ocurrido algún tipo de error.");
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
		System.out.println("✪    ALTA DE USUARIO    ✪");

		System.out.println("Introduce el nombre");
		nombre = sc.nextLine();

		if (!comprobarNomApellCiclo(nombre)) {//Comprobamso que sea el nombre válido (no dígitos)
			System.err.println("Nombre no válido. Solo se aceptan caracteres alfabeticos.");
			System.out.println("Volviendo al menú principal.");
			System.out.println();
			menuPrincipal();
		}

		bufferNombre = new StringBuffer(nombre);
		bufferNombre.setLength(Constantes.NUMCHARNOMBRE);

		System.out.println("Introduce los apellidos");
		apellidos = sc.nextLine();

		if (!comprobarNomApellCiclo(apellidos)) {//Comprobamso que sea el apellidos válido (no dígitos)
			System.err.println("Apellido no válido. Solo se aceptan caracteres alfabeticos.");
			System.out.println("Volviendo al menú principal.");
			System.out.println();
			menuPrincipal();
		}
		bufferApellidos = new StringBuffer(apellidos);
		bufferApellidos.setLength(Constantes.NUMCHARAPELLIDOS);

		System.out.println("Introduce el DNI");
		dni = sc.nextLine();

		if (!comprobarDNI(dni)) { //Comprobamso que sea el dni válido (no dígitos)
			System.err.println("Dni no válido");
			System.out.println("Volviendo al menú principal.");
			System.out.println();
			menuPrincipal();

		}

		bufferDni = new StringBuffer(dni);
		bufferDni.setLength(Constantes.NUMCHARDNI);

		System.out.println("Introduce el ciclo");
		ciclo = sc.nextLine();
		if (!comprobarNomApellCiclo(ciclo)) { //Comprobamso que sea el ciclo válido (no dígitos)
			System.err.println("Ciclo no válido. Solo se aceptan caracteres alfabeticos.");
			System.out.println("Volviendo al menú principal.");
			System.out.println();
			menuPrincipal();
		}

		bufferCiclo = new StringBuffer(ciclo);
		bufferCiclo.setLength(Constantes.NUMCHARCICLO);

		System.out.println("Introduce el curso");

		curso = sc.nextInt();
		sc.nextLine();

		if (curso!=1 && curso!=2) { //Comprobamso que sea el curso sea válido(no dígito diferente a 1 y2
			System.err.println("Curso introducido no válido");
			System.out.println("Volviendo al menu principal.");
			System.out.println();
			menuPrincipal();
		}
		

		System.out.println("Apellidos: " + apellidos);
		System.out.println("Nombre: " + nombre);
		System.out.println("DNI: " + dni);
		System.out.println("Ciclo: " + ciclo);
		System.out.println("Curso: " + curso);
		System.out.println("Estas seguro de crear este alumno?");
		if (confirmarCambios()) {//Pedimos confirmación para crear el alumno
			raf.writeChars(bufferDni.toString());
			raf.writeChars(bufferApellidos.toString());
			raf.writeChars(bufferNombre.toString());
			raf.writeChars(bufferCiclo.toString());
			raf.writeInt(curso);
			System.out.println("Alumno creado.");
			System.out.println();

		} else {
			System.out.println("Cancelado creación del alumno.");
			System.out.println("Volviendo al menú principal.");
			System.out.println();
			menuPrincipal();
		}

	}

	private static boolean comprobarNomApellCiclo(String valor) {
		String patronValido = "^[^\\d]*$"; //No admite dígitos
		Pattern pattern = Pattern.compile(patronValido);
		Matcher matcher = pattern.matcher(valor);
		return matcher.matches();
	}

	private static boolean comprobarDNI(String dni) {
		String patronValido = "\\d{8}[A-HJ-NP-TV-Z]"; //Admite 8 digitos y una letra mayúscula
		Pattern pattern = Pattern.compile(patronValido);
		Matcher matcher = pattern.matcher(dni);

		return matcher.matches();
	}

	private static boolean confirmarCambios() {
		String opcion;
		System.out.println("1) Si.");
		System.out.println("2) No.");
		System.out.println();
		opcion = sc.nextLine();
		if (opcion.equals("1")) {
			return true;
		}
		return false;
	}
}
