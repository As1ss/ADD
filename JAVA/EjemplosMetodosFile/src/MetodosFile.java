import java.io.File;

public class MetodosFile {

	public static void main(String[] args) {
		String dirActual = "D:\\DAM2T\\ADD\\JAVA\\EjemplosMetodosFile\\";
		
		File file = new File(dirActual);
		
		
		mostrarDatos(file);

	}

	private static void mostrarDatos(File file) {
		if (file.exists()) {
			System.out.println("Nombre: "+file.getName());
			System.out.println("Ruta relativa: "+file.getPath());
			System.out.println("Ruta absoluta: "+file.getAbsolutePath());
			System.out.println("Es legible: "+file.canRead());
			System.out.println("Es modificable: "+file.canWrite());
			System.out.println("Tamaño: "+file.getUsableSpace()+" Bytes.");
			System.out.println("Es directorio: "+file.isDirectory());
			System.out.println("Es fichero: "+file.isFile());
			System.out.println("Nombre del directorio padre: "+file.getParent());
		}
		else {
			System.out.println("No existe");
		}
		
	}

}
