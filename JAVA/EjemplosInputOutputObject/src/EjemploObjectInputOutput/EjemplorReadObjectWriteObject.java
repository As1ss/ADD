package EjemploObjectInputOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;

public class EjemplorReadObjectWriteObject {

	public static void main(String[] args) {
		Persona[] personas = new Persona[11];
		personas[0] = new Persona("Alicia", 15);
		personas[1] = new Persona("Ana", 14);
		personas[2] = new Persona("Luis", 15);
		personas[3] = new Persona("Gervasio", 13);
		personas[4] = new Persona("Pedro", 15);
		personas[5] = new Persona("Manuel", 16);
		personas[6] = new Persona("Andrés", 12);
		personas[7] = new Persona("Julio", 16);
		personas[8] = new Persona("Antonio", 14);
		personas[9] = new Persona("María", 13);
		personas[10] = new Persona("Jesús", 15);

		try {
			File fich = new File("FichPersona.dat");
			fich.createNewFile();
		
			ObjectOutputStream oS = new ObjectOutputStream(new FileOutputStream(fich,true));
			
			
			DecimalFormat formato = new DecimalFormat("#.##");
			

			try {
				for (Persona p : personas) {
					oS.writeObject(p);
					
				}
				
				oS.close();
				ObjectInputStream oI = new ObjectInputStream(new FileInputStream(fich));
				
				try {
					Persona p;
					while((p=(Persona) oI.readObject())!=null) {
						System.out.println("Nombre: "+p.getNombre()+" Edad: "+p.getEdad());
						System.out.println();
					}
				} catch (Exception e) {
					System.out.println("Fin del documento");
				}
				

				oI.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
