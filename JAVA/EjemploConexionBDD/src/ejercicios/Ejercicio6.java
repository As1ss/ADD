package ejercicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

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
		List<Integer> emp_noArray = new ArrayList<Integer>();
		List<Integer> directorArray = new ArrayList<Integer>();
		List<Integer> dept_noArray = new ArrayList<Integer>();
		List<List> datosValidar = new ArrayList<List>();

		try {
			emple = obtenerEmpleado(args);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/add_ejemplo", "root",
					"1234");
			Statement sentencia = conexion.createStatement();
			String query = "SELECT * FROM EMPLEADO";
			ResultSet resultado = sentencia.executeQuery(query);

			while (resultado.next()) {
				emp_noArray.add(resultado.getInt(1));

				directorArray.add(resultado.getInt(4));

				dept_noArray.add(resultado.getInt(8));
			
			}

			datosValidar.add(emp_noArray);
			datosValidar.add(directorArray);
			datosValidar.add(dept_noArray);
		

			conexion.close();
			sentencia.close();
			resultado.close();

			if (comprobarEmpleado(datosValidar, emple)) {
				insertarEmpleado(emple);
				System.out.println("Insercion completada");
			} else {
				System.out.println("Algo ha pachao");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void insertarEmpleado(Empleado emple) {
		Connection conexion;
		int emp_no = emple.getEmp_no();
		String apellido = emple.getApellido();
		String oficio = emple.getOficio();
		int director = emple.getDirector();
		float salario = emple.getSalario();
		float comision = emple.getComision();
		int dept_no = emple.getDept_no();
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/add_ejemplo", "root", "1234");
			Statement sentencia = conexion.createStatement();
			
			 // Utilizar la función NOW() para obtener la fecha y hora actuales
	        String query = "INSERT INTO EMPLEADO VALUES (" + emp_no + ", '" + apellido + "', '" + oficio + "', " +
	                       director + ", NOW(), " + salario + ", " + comision + ", " + dept_no + ")";

			// Ejecutar la sentencia de inserción
			sentencia.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static boolean comprobarEmpleado(List<List> datosValidar, Empleado emple) {
		System.out.println("Ha entrado a comprobar..");

		if (datosValidar.get(0).contains(emple.getEmp_no())) {

			System.out.println("El id del empleado ya existe");
			return false;

		}
		else if(!datosValidar.get(1).contains(emple.getDirector())) {
			System.out.println("El director no existe o es nulo");
			return false;
		}
		else if (!datosValidar.get(2).contains(emple.getDept_no())) {
			System.out.println("El departamento no existe o es nulo");
			return false;
		}
		return true;
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
