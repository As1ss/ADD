package ejercicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ejercicio3 {
	
	/*
	 * ESTE ES PRA GENERADTEDVALUE SOLO DESDE PERSISTENCE,XML
	 */

	public static void main(String[] args) {
		EntityManagerFactory eFactory = Persistence.createEntityManagerFactory("m");
		EntityManager eManager = eFactory.createEntityManager();
		EntityTransaction eTransaction = eManager.getTransaction();
		
		eTransaction.begin();
		
		Empleado e1 = new Empleado();
		e1.setName("Pablito");
		e1.setEdad(25);
		eManager.persist(e1);
		
		Empleado e2 = new Empleado();
		e2.setName("Joado");
		e2.setEdad(35);
		eManager.persist(e2);
		
		Empleado e3 = new Empleado();
		e3.setName("Alexis");
		e3.setEdad(21);
		eManager.persist(e3);

		eTransaction.commit();
		
	}

}
