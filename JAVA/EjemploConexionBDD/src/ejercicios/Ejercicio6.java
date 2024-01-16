package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
/*
 * Crear un programa java que inserte un empleado en la tabla empleados, para
 * ello recibirá desde la línea de argumentos los valores a insertar son :
 * emp_no, apellido, oficio, director, salario, comision, dept_no Antes de
 * insertar comprobar que el emp_no no exista, que el dept_no exista, que el
 * salario sea mayor que 0, que el director exista y no sea null, apellido y
 * oficio no pueden ser nulos. Visualizar un mensaje si se ha insertado
 * correctamente o no y con su condicion.
 */

class Empleado {
	private int emp_no;
	private String apellido;
	private String oficio;
	private int director;
	private float salario;
	private float comision;
	private int dept_no;

	public Empleado() {

	}

	public Empleado(int emp_no, String apellido, String oficio, int director, float salario, float comision,
			int dept_no) {
		this.emp_no = emp_no;
		this.apellido = apellido;
		this.oficio = oficio;
		this.director = director;
		this.salario = salario;
		this.comision = comision;
		this.dept_no = dept_no;
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public int getDept_no() {
		return dept_no;
	}

	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}

	public int getDirector() {
		return director;
	}

	public void setDirector(int director) {
		this.director = director;
	}

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}

}

public class Ejercicio6 {

	public static void main(String[] args) {
		Empleado emple;

		try {
			emple = obtenerEmpleado(args);
			Class.forName("com.mysql.jc.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/add_ejemplo", "root",
					"1234");
			Statement sentencia = conexion.createStatement();
			String query = "SELECT * FROM EMPLEADO";
			ResultSet resultado = sentencia.executeQuery(query);
			int numColumns = resultado.get

			while (resultado.next()) {
				int[] Emp_no = resultado.getInt(1);

				int[] director = resultado.getInt(4);
				
				int[] dept_no = resultado.getInt(7);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Empleado obtenerEmpleado(String[] args) {

		int emp_no = Integer.parseInt(args[0]);
		String apellido = args[1];
		String oficio = args[2];
		int director = Integer.parseInt(args[3]);
		float salario = Float.parseFloat(args[4]);
		float comision = Float.parseFloat(args[5]);
		int dept_no = Integer.parseInt(args[6]);
		Empleado empleado = new Empleado(emp_no, apellido, oficio, director, salario, comision, dept_no);

		return empleado;
	}

}
