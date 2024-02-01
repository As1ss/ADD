package ejercicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ejercicio4 {

	public static void main(String[] args) {
		EntityManagerFactory eFactory = Persistence.createEntityManagerFactory("m");
		EntityManager eManager = eFactory.createEntityManager();
		EntityTransaction eTransaction = eManager.getTransaction();

		try {
			// ...
			EmpleadoEmbeddedID embebedEmployer = new EmpleadoEmbeddedID();
			Empleado_Id empleado_id = new Empleado_Id();
			empleado_id.setDni("12392839N");
			empleado_id.setEmpleado_id(1);
			embebedEmployer.setName("Alexis");
			embebedEmployer.setEdad(21);
			embebedEmployer.setEmpleado_id(empleado_id);

			EmpleadoEmbeddedID embebedEmployer2 = new EmpleadoEmbeddedID();
			Empleado_Id empleado_id2 = new Empleado_Id();
			empleado_id2.setDni("65697739N");
			empleado_id2.setEmpleado_id(25);
			embebedEmployer2.setName("Joado");
			embebedEmployer2.setEdad(40);
			embebedEmployer2.setEmpleado_id(empleado_id2);

			eManager.persist(embebedEmployer);
			eManager.persist(embebedEmployer2);
			// ...

			eTransaction.commit();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

	}

}
