package ejercicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ejercicio1 {

	public static void main(String[] args) {
		EntityManagerFactory eFactory = Persistence.createEntityManagerFactory("m");
		EntityManager eManager = eFactory.createEntityManager();
		EntityTransaction eTrans = eManager.getTransaction();

		eTrans.begin();

		Persona p1 = eManager.find(Persona.class, 1);
		
		
		
		SuperHeroe sh1 = new SuperHeroe();
		sh1.setName("Spoiderman");
		sh1.setPersona(p1);
		

		eManager.persist(sh1);

		eTrans.commit();
		
		

	}

}
