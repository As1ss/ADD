package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
 * 
5.- UTILIZAR LA INTERFAZ PREPARESTATEMENT PARA VISUALIZAR EL APELLIDO, EL SALARIO Y EL OFICIO DE LOS EMPLEADOS DE UN UNICO 
DEPARTMENTO, VISUALIZAR TAMBIEN EL NOMBRE DEL DEPTO EL SALARIO AVG DE ESE DPTO Y EL NUMERO DE EMPLEADOS DE ESE DPTO. 
SI EL DPTO NO EXISTE MOSTRAR UN MSG

 */
import java.util.Scanner;

public class Ejercicio7 {

	public static void main(String[] args) {
		Connection conexion;
		Scanner sc = null;

		try {
			sc = new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/add_ejemplo", "root", "1234");
			System.out.println("Introduce un numero de departamento.");
			String input = sc.nextLine();
			;

			String query = "SELECT  apellido, salario, oficio FROM EMPLEADO " + "WHERE dept_no = " + "?";

			PreparedStatement ps = (PreparedStatement) conexion.prepareStatement(query);

			ps.setInt(1, Integer.parseInt(input));

			ResultSet rs = ps.executeQuery();

			if (!rs.isBeforeFirst()) {

				System.err.println("NO SE ENCUENTRA EL DEPARTAMENTO EN LA BASE DE DATOS.");
			} else {


				while (rs.next()) {
					System.out.println("APELLIDO: " + rs.getString(1));
					System.out.println("SALARIO: " + rs.getFloat(2));
					System.out.println("OFICIO: " + rs.getString(3));
					System.out.println();

				}
			}
			rs.close();
			ps.close();

			String newQuery = "SELECT COUNT(e.emp_no) as numero_empleados,AVG(e.salario) as salario_promedio,d.dnombre FROM empleado e, departamento d\r\n"
					+ "WHERE e.dept_no = " + Integer.parseInt(input) + " AND d.dept_no = e.dept_no\r\n"
					+ "GROUP BY d.dnombre;";

			PreparedStatement ps2 = (PreparedStatement) conexion.prepareStatement(newQuery);

			ResultSet rs2 = ps2.executeQuery();

			while (rs2.next()) {
				System.out.println("Nº TRABAJADORES: " + rs2.getString(1));
				System.out.println("SALARIO PROMEDIO: " + rs2.getFloat(2));
				System.out.println("NOMBRE DEPARTAMENTO: " + rs2.getString(3));
				System.out.println();
			}
			rs2.close();
			ps2.close();

			conexion.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
