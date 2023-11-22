package EjemploEmpleadosRepaso;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EscribirEmpleados {

	public static void main(String[] args)throws IOException {
		String [] apellidos= { "FERNANDEZ", "GIL", "LOPEZ", "RAMOS", "SEVILLA", "CASTILLA", "REY" };
		int [] departamentos={10, 20, 10, 10, 30, 30, 20};
		double [] salarios = {1000.45, 2400.60, 3000.00, 1500.56, 2200.00, 1435.87, 2000.00}; 
		StringBuffer buffer=  null;
		File fich = new File("AleatorioEmplea2.dat");
		RandomAccessFile raf = new RandomAccessFile(fich,"rw");
		int position = 0;
		raf.seek(position);
		for (int i =0;i<apellidos.length;i++) {
		
			raf.writeInt(i+1);
			buffer = new StringBuffer(apellidos[i]);
			buffer.setLength(10);
			raf.writeChars(buffer.toString());
			raf.writeInt(departamentos[i]);
			raf.writeDouble(salarios[i]);
		}
		raf.close();
		
		
	}

}
