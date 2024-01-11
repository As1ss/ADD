package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.DatabaseMetaData;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Ejercicio3 {
	/*
	 * 3.- VISUALIZAR LA INFORMACIÓN DE TODAS LAS COLUMNAS DE LA TABLA DEPARTAMENTOS
	 */

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/add_ejemplo","root","1234");
			DatabaseMetaData dbmd = (DatabaseMetaData) conexion.getMetaData();
			
			ResultSet resultado = dbmd.getColumns(null, null, "departamento", null);
			
			if(resultado.next()) {
				System.out.println("Catálogo: "+ resultado.getString(1));
				System.out.println("Esquema: "+ resultado.getString(2));
				System.out.println("Nombre de la tabla: "+ resultado.getString(3));
				System.out.println("Columnas");
			}
			
			while(resultado.next()) {
				System.out.print( resultado.getString(4)+"|");
			}
			
			
				
			resultado.close();
			conexion.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
