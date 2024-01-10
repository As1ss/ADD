package ejercicios;
import java.sql.*;

public class Ejercicio1 {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/add_ejemplo";
		String user= "root";
		String password = "1234";
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(jdbcUrl, user, password);
			
			
			Statement statement = conexion.createStatement();
			String query ="SELECT apellido,oficio,salario FROM empleado WHERE empleado.dept_no IN (SELECT dept_no FROM departamento WHERE departamento.dept_no = 10);";
			ResultSet resultado = statement.executeQuery(query);
			
			while(resultado.next()) {
				System.out.println("     Apellido    |    Oficio    |    Salario    ");
				System.out.println("    "+resultado.getString(1)+"    |    "+resultado.getString(2)+"    |    "+resultado.getFloat(3));
			}
			statement.close();
			resultado.close();
			conexion.close();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
