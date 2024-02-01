package ejercicio;

import java.io.Serializable;

import jakarta.persistence.Embeddable;


@Embeddable
public class Empleado_Id implements Serializable {

	private int empleado_id;
	private String dni;

	public Empleado_Id() {

	}

	@Override
	public int hashCode() {

		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		return super.equals(obj);
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEmpleado_id() {
		return empleado_id;
	}

	public void setEmpleado_id(int empleado_id) {
		this.empleado_id = empleado_id;
	}

}
