package ejercicios;
import java.sql.*;

public class Ejercicio2 {

	/*
	 * OBTENER APELLIDO Y SALARIO Y EL NOMBRE DEL DEPARTAMENTO DEL EMPLEADO QUE MAS COBRE DE LA EMPRESA
	 */
	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/add_ejemplo";
		
		
		String user ="root";
		String password= "1234";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(jdbcUrl,user,password);
			Statement sentencia = conexion.createStatement();
			String query = "SELECT apellido,salario,dnombre FROM empleado e,departamento d\r\n"
					+ "WHERE e.dept_no = d.dept_no ORDER BY salario DESC LIMIT 1;";
			ResultSet resultado = sentencia.executeQuery(query);
			
			while(resultado.next()) {
				System.out.println("Apellido           |Salario            |NombreDept         ");
				StringBuffer apellido = new StringBuffer(resultado.getString(1));
				int longitudDeseada= 20;
				
				formatearCampos(apellido,longitudDeseada);
				
			
			
				StringBuffer salario = new StringBuffer(String.valueOf(resultado.getFloat(2)));
				formatearCampos(salario,longitudDeseada);
				StringBuffer nomDept = new StringBuffer(resultado.getString(3));
				formatearCampos(nomDept,longitudDeseada);
				
				System.out.println(""+apellido+salario+nomDept);
			}
			sentencia.close();
			resultado.close();
			conexion.close();
			
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		


	}

	private static void formatearCampos(StringBuffer apellido, int longitudDeseada) {
		// Asegurarse de que la longitud sea al menos 20
		if (apellido.length() < longitudDeseada) {
		    // Calcular cuántos espacios en blanco se deben agregar
		    int espaciosRestantes = longitudDeseada - apellido.length();
		    
		    // Agregar el apellido y rellenar con espacios en blanco
		    apellido.append(" ".repeat(espaciosRestantes));
		} else if (apellido.length() > longitudDeseada) {
		    // Si el apellido es más largo de lo deseado, truncarlo a la longitud deseada
		    apellido.setLength(longitudDeseada);
		}
	
		
	}

}
