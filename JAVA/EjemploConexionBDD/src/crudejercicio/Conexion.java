package crudejercicio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	
	public static Connection conexion;
	
	
	public Conexion() {
		
		
		abrirConexion();
		cerrarConexion();
	}

	public static void cerrarConexion() {
		
		
	try {
		conexion.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}

	public static Connection abrirConexion() {
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/add_ejemplo", "root", "1234");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conexion;
		
	}

	
	
}
