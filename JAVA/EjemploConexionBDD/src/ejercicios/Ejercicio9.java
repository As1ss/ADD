package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class Ejercicio9 {

	public static void main(String[] args) {


			String reportSource = "./src/ejercicios/plantilla.jrxml";
			String reportHTML = "./informes/Informe.html";
			String reportPDF = "./informes/Informe.pdf";
			String reportXML = "./informes/Informe.xml";

			// Parámetros del informe
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("titulo", "RESUMEN DATOS DE DEPARTAMENTOS.");
			params.put("autor", "JC");
			params.put("fecha", (new java.util.Date()).toString());

			try {
				// Compilar el fichero JRXML
				JasperReport jasperReport = JasperCompileManager.compileReport(reportSource);
				// Cargar el driver
				Class.forName("com.mysql.cj.jdbc.Driver");
				// Establecer la conexión con la BD
				Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/add_ejemplo", "root", "1234");
				

		            // Conectar a la base de datos y obtener los datos
		            // Aquí debes reemplazar "obtenerDatosDesdeBD()" con tu lógica para obtener los datos de la base de datos
		            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(obtenerDatosDesdeBD());

		            // Parámetros del informe (puede ser útil para pasar algunos datos adicionales al informe)
		            Map<String, Object> parameters = new HashMap<>();

		            // Llenar el informe con los datos y generar el informe en formato PDF
		            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		            JasperExportManager.exportReportToPdfFile(jasperPrint, "ruta/del/informe.pdf");

		            System.out.println("Informe generado exitosamente.");

				System.out.println("ARCHIVOS CREADOS");
			}
			catch (CommunicationsException c) {System.out.println(" Error de comunicación con la BD. No está arrancada.");}
			catch (ClassNotFoundException e) {System.out.println(" Error driver. ");}
			catch (SQLException e) {System.out.println(" Error al ejecutar sentencia SQL ");}
			catch (JRException ex) {
				System.out.println(" Error Jasper.");
				ex.printStackTrace();
			}

		}

	private static Collection<?> obtenerDatosDesdeBD() {
		
		return null;
	}


	

}
