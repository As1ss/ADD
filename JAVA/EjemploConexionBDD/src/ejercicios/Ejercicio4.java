package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.DatabaseMetaData;

public class Ejercicio4 {
/*
 * 4.-VISUALIZAR INFORMACIÓN DE LAS CLAVES PRIMARIAS Y AJENAS DE LA TABLA DEPARTAMENTOS Y EMPLEADOS

 */
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/add_ejemplo","root","1234");
			DatabaseMetaData dbmd = (DatabaseMetaData) conexion.getMetaData();
			
			ResultSet resultadoPKDept = dbmd.getPrimaryKeys(null, null, "departamento");
			ResultSet resultadoFKDept = dbmd.getExportedKeys(null, null, "departamento");
			ResultSet resultadoPKEmpl = dbmd.getPrimaryKeys(null, null, "empleado");
			ResultSet resultadoFKEmpl = dbmd.getExportedKeys(null, null, "empleado");
			
			
				System.out.println("PRIMARY KEY DEPARTAMENTO");
				while(resultadoPKDept.next()) {
					System.out.println(resultadoPKDept.getString(4));
				}
			
			
				//CLAVE PRIMARIA FORANEA DE DEPARTAMENTO
				System.out.println("FOREIGN KEY DEPARTAMENTO");
				while(resultadoFKDept.next()) {
					System.out.println(resultadoFKDept.getString(8));
				}
			
				
				System.out.println("PRIMARY KEY EMPLEADO");
				while(resultadoPKEmpl.next()) {
					System.out.println(resultadoPKEmpl.getString(4));
				}
				
				//CLAVE PRIMARIA FORANEA DE DEPARTAMENTO
				System.out.println("FOREIGN KEY DEPARTAMENTO");
				while(resultadoFKEmpl.next()) {
					System.out.println(resultadoFKEmpl.getString(8));
				}
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
