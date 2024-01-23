package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import com.mysql.cj.jdbc.CallableStatement;

public class Ejercicio8 {

	public static void main(String[] args) {
		
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/add_ejemplo", "root", "1234");

	            // Prepare the call to the stored function
	            String query = "{ ? = call obtenerInfoDepartamento(?) }";
	            CallableStatement cs = (CallableStatement) conexion.prepareCall(query);

	            // Register the return parameter (VARCHAR)
	            cs.registerOutParameter(1, Types.VARCHAR);

	            // Set the input parameter for the depto
	            cs.setInt(2, 10);  // Replace with the actual department value

	            // Execute the stored function
	            cs.execute();

	            // Retrieve the result
	            String result = cs.getString(1);

	            // Do something with the result, for example, print it
	            System.out.println("Result: " + result);

	            // Close resources
	            cs.close();
	            conexion.close();
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }

	    }
	}

