package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Ejercicio5 {

	public static void main(String[] args) {

		try {
			// Cargar el driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Establecer la conexion con la BD
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/add_ejemplo", "root", "1234");

			// Preparar la consulta
			Statement sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery("SELECT * FROM empleado");
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();

			int nColumnas = rsmd.getColumnCount();
			String nula;
			System.out.printf("N�mero de columnas recuperadas: %d%n", nColumnas);
			for (int i = 1; i <= nColumnas; i++) {
				System.out.printf("Columna %d: %n ", i);
				System.out.printf("  Nombre: %s %n   Tipo: %s %n ", rsmd.getColumnName(i), rsmd.getColumnTypeName(i));
				if (rsmd.isNullable(i) == 0)
					nula = "NO";
				else
					nula = "SI";
				System.out.printf("  Puede ser nula?: %s %n ", nula);
				System.out.printf("  M�ximo ancho de la columna: %d %n", rsmd.getColumnDisplaySize(i));
			} // for

			sentencia.close();
			rs.close();
			conexion.close();
		} catch (ClassNotFoundException cn) {
			cn.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} // Fin de main
}
