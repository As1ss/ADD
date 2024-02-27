package ejercicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
	
	public static void main(String[] args) {
		
		EntityManagerFactory eFactory = Persistence.createEntityManagerFactory("Persistencia");
		EntityManager eManager = eFactory.createEntityManager();
		EntityTransaction eTrans = eManager.getTransaction();
		
		try {
			
			eTrans.begin();
			Departamento d1 = new Departamento();
			d1.setId(30);
			d1.setNombre("Ventas");
			d1.setLocalizacion("Madrid");
			eManager.persist(d1);
			
			eTrans.commit();
		} catch (Exception e) {
			
		}
		
	}

}
