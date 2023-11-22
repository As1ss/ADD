package EjemploEmpleadosRepaso;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LeerEmpleados {
	final static int TAMAÑOREGISTRO=36;

	public static void main(String[] args) throws IOException {
		File fich = new File("AleatorioEmplea2.dat");
		RandomAccessFile raf = new RandomAccessFile(fich,"r");
		char[] apellidos = new char[10];
		char aux;
		int id,dpto,posicion;
		double salario;
		String apellido;
		posicion=0;
		
		while(true) {
			raf.seek(posicion);
			id=raf.readInt();
			for (int i =0;i<apellidos.length;i++) {
				aux=raf.readChar();
				apellidos[i]=aux;
			}
			apellido = new String(apellidos);
			dpto=raf.readInt();
			salario=raf.readDouble();
			
			if(id>0) {
				System.out.println("ID: "+id);
				System.out.println("Apellido: "+apellido);
				System.out.println("Departamento: "+dpto);
				System.out.println("Salario: "+salario);
				System.out.println();
			}
			posicion+=TAMAÑOREGISTRO;
			if (raf.getFilePointer()==fich.length()) {
				break;
			}
			
		}
		
		
		
	}

}
