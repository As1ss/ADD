package ejercicio;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity(name = "empleado_dos")
public class EmpleadoEmbeddedID {

	@EmbeddedId
	private Empleado_Id empleado_id;

	private String name;
	
	private int edad;

	public EmpleadoEmbeddedID() {
		
	}

	public void setEmpleado_id(Empleado_Id empleado_id) {
		this.empleado_id = empleado_id;
	}

	public Empleado_Id getEmpleado_id() {
		return empleado_id;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getEdad() {
		return edad;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
