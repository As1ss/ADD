package Ejemplos;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class EjemploListas2 {

	public static void main(String[] args) {
		/*
		 * Programa java que use el método listFiles() para mostrar la lista de ficheros
		 * o directoriso de un directorio caulquiera o del directorio actual
		 */

		menuPrincipal();

	}

	private static void obtenerLista(String entradaUsuario) {

		String direccion = "";
		String directorio = "D: ";
		String fichero = "F: ";

		if (entradaUsuario == null || entradaUsuario.isEmpty()) {
			direccion = ".";
		} else {
			direccion = entradaUsuario;
		}
		File dirActual = new File(direccion);

		for (File list : dirActual.listFiles()) {

			if (list.isFile()) {
				System.out.println(fichero + list);
			} else {
				System.out.println(directorio + list);
			}
		}
	}

	private static void menuPrincipal() {
		int opcion = 0;
		String direccion = "";
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("Escoge una opción");
			System.out.println("1.- Mostar directorios / ficheros del directorio actual");
			System.out.println("2.- Introducir directorio y mostrar directorios / ficheros");
			System.out.println("3.- Salir.");
			try {
				opcion = sc.nextInt();
				sc.nextLine();
				switch (opcion) {

				case 1:
					obtenerLista(null);
					break;
				case 2:
					System.out.println("Introduce la dirección del directorio:");
					direccion = sc.nextLine();
					sc.close();
					obtenerLista(direccion);
					break;
				case 3:
					System.out.println("Buenas noches.");
					break;
				default:
					System.out.println("Introduce una opción valida");

				}
			}

			catch (Exception e) {
				System.out.println("Debes introducir un dígito");
				menuPrincipal();
			}
		} while (opcion != 3);
		
	}

}
