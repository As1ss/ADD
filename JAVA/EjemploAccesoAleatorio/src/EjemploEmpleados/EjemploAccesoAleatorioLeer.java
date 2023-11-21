package EjemploEmpleados;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EjemploAccesoAleatorioLeer {

	public static void main(String[] args) throws IOException {
		File fichero = new File("AleatorioEmple.dat");
		RandomAccessFile file = new RandomAccessFile(fichero, "r"); // Declarar el fichero de acceso aleatorio
		int id, dep, posicion;
		char apellido[] = new char[10], aux;
		Double salario;
		posicion = 0; // Para situarse al principio
		for(;;) { // Recorrer el fichero
			file.seek(posicion); // Posicionarse en "posicion"
			id = file.readInt(); // Obtener el Id de empleado
			for (int i = 0; i < apellido.length; i++) { // Recorrer uno a uno los caracteres del apellido
				aux = file.readChar();
				apellido[i] = aux; // Y guardarlos en el array
			}
			String apellidos = new String(apellido); // Convertir a String el array
			dep = file.readInt(); // Obtener el Departamento
			salario = file.readDouble(); // Obtener el Salario
			if(id > 0)
				System.out.printf("ID: %s, Apellido: %s, Departamento: %d, Salario: %.2f %n", id, apellidos.trim(), dep, salario);
			posicion = posicion + 36; // Posicionarse para el siguiente empleado (cada empleado ocupa 36 bytes)
			if (file.getFilePointer() == file.length()) // Si se han recorrido todos los bytes se sale del bucle "for"
				break;
		}
		file.close(); // Cerrar el fichero
	}
}
