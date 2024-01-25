package crudejercicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OperacionesDepartamento implements CrudInterface {

	private static Connection conn;

	public OperacionesDepartamento() {
		conn = Conexion.abrirConexion();

	}

	@Override
	public void create(Departamento dept) {

		conn = Conexion.abrirConexion();
		String query = "INSERT INTO DEPARTAMENTO VALUES (?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, dept.getDept_no());
			ps.setString(2, dept.getDnombre());
			ps.setString(3, dept.getLoc());
			ps.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
	}
	@Override
	public Departamento read(int id) {
		conn = Conexion.abrirConexion();
		String query = "SELECT * FROM DEPARTAMENTO WHERE dept_no = "+id;
		Departamento dep = new Departamento();
		try {
			Statement sentencia = (Statement) conn.createStatement();
			ResultSet rs = sentencia.executeQuery("SELECT * FROM DEPARTAMENTO");
			while (rs.next()) {
				
				dep.setDept_no(rs.getInt(1));
				dep.setDnombre(rs.getString(2));
				dep.setLoc(rs.getString(3));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
		return dep;
	}

	@Override
	public List<Departamento> readAll() {
		conn = Conexion.abrirConexion();
		String query = "SELECT * FROM DEPARTAMENTO";
		List<Departamento> departamentos = new ArrayList<Departamento>();
		try {
			Statement sentencia = (Statement) conn.createStatement();
			ResultSet rs = sentencia.executeQuery("SELECT * FROM DEPARTAMENTO");
			while (rs.next()) {
				Departamento dep = new Departamento();
				dep.setDept_no(rs.getInt(1));
				dep.setDnombre(rs.getString(2));
				dep.setLoc(rs.getString(3));
				departamentos.add(dep);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}

		return departamentos;

	}

	@Override
	public void update(Departamento dept) {
		conn = Conexion.abrirConexion();
		String query = "UPDATE DEPARTAMENTO SET loc = ? WHERE dept_no= ?   ";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, dept.getLoc());
			ps.setInt(2, dept.getDept_no());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}

	}

	@Override
	public void delete(Departamento dept) {
		conn = Conexion.abrirConexion();
		String query = "DELETE FROM DEPARTAMENTO WHERE dept_no = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, dept.getDept_no());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion();
		}
	}

}
