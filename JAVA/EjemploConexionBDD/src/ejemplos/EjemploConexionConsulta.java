package ejemplos;
import java.sql.*;

public class EjemploConexionConsulta {

	public static void main(String[] args) {
		
		 String jdbcUrl = "jdbc:mysql://localhost:3306/add_ejemplo";
		 String usuario = "root";
		 String password = "1234";
		
		   try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(jdbcUrl,usuario,password);
			
			
			Statement sentencia = conexion.createStatement();
			String query ="SELECT * FROM departamento";
			ResultSet resultado = sentencia.executeQuery(query);
			
			while (resultado.next()) {
			    System.out.println("Departamento |     Nombre   | Localización");
			    System.out.println("----------------------------------------------");
			    System.out.println(String.format("%12s | %12s | %12s",
			            resultado.getInt(1), resultado.getString(2), resultado.getString(3)));
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

}
