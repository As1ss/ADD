package EjempoDataInputOutput;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EjemploDataInputOutputStream {

	public static void main(String[] args) throws IOException {
		HashMap<String, Integer> personas = new HashMap<>();
		personas.put("Ana", 14);
		personas.put("Luis", 15);
		personas.put("Alicia", 13);
		personas.put("Pedro", 15);
		personas.put("Manuel", 16);
		personas.put("Andrés", 12);
		personas.put("Julio", 16);
		personas.put("Antonio", 14);
		personas.put("María", 13);
		personas.put("Jesús", 15);

		try {
			File fich = new File("Fichdata.dat");
			fich.createNewFile();
			FileInputStream fichInput = new FileInputStream(fich);
			FileOutputStream fichOutput = new FileOutputStream(fich);
			DataInputStream dataIs = new DataInputStream(fichInput);
			DataOutputStream dataOs = new DataOutputStream(fichOutput);

			for (Map.Entry<String, Integer> entry : personas.entrySet()) {
				String nombre = entry.getKey();
				int edad = entry.getValue();
				dataOs.writeUTF("Nombre: " + nombre + " Edad: " + edad + "\n");
			}
			String i = "";
			try {
				while ((i = dataIs.readUTF()) != null) {

					System.out.println(i);

				}
			} catch (Exception e) {
				System.out.println("Documento leído");
			}

			dataIs.close();
			dataOs.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
