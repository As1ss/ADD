package EjemploEmpleados;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EjemploAccesoAleatorioEscribir {

	public static void main(String[] args) throws IOException {
		/*
		 * 
		 * 
		 * PROGRAMA PARA INSERTAR LOS DATOS DE LOS EMPLEADOS DE UNA EMRPESA EN UN
		 * FICHERO ALEATORIO LLAMADO "AleatorioEmple.dat" donde los datos son
		 * 
		 * apellidos{"FERNANDEZ GIL LOPEZ RAMOS SEVILLA CASTILLA REY")==>10
		 * caracteres(20 bytes)
		 * 
		 * departamento {10 , 20 ,10 , 10, 30, 30, 20} ==>int(4bytes)
		 * 
		 * salario float{ 1000.45, 2400.60, 3000.00, 1500.56, 2200.00, 1435.87, 2000.00}
		 * ==> double(8bytes)
		 * 
		 * además para cada empleado se insertarña un ID > 0 (4bytes) siendo la longitud
		 * del registro de 36 bytes
		 * 
		 */
		// String path = "android.resource://"+get.PackageName()+"/"+R.raw.video;
		// Uri uri = Uri.parse(path)
		// video.setUri(uri)

		File fich = new File("AleatorioEmple.dat");
		RandomAccessFile raf = new RandomAccessFile(fich, "rw");
		String apellidos[] = { "FERNANDEZ", "GIL", "LOPEZ", "RAMOS", "SEVILLA", "CASTILLA", "REY" };
		int departamentos[] = { 10, 20, 10, 10, 30, 30, 20 };
		double salario[] = { 1000.45, 2400.60, 3000.00, 1500.56, 2200.00, 1435.87, 2000.00 };
		StringBuffer buffer = null;

		

		for (int i = 0; i < apellidos.length; i++) {

			raf.writeInt(i + 1);
			buffer = new StringBuffer(apellidos[i]);
			buffer.setLength(10);
			raf.writeChars(buffer.toString());
			raf.writeInt(departamentos[i]);
			raf.writeDouble(salario[i]);
		

		}
		raf.close();

	}
}
