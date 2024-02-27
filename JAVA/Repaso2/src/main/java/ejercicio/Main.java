package ejercicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory eFactory = Persistence.createEntityManagerFactory("Repaso2");
		EntityManager eManager = eFactory.createEntityManager();
		EntityTransaction eTrans = eManager.getTransaction();
		try {
			
			Departamento d1 = new Departamento();
			d1.setId(40);
			d1.setNombre("Producción");
			d1.setLocalizacion("Bilbao");
			
			eTrans.begin();
			eManager.persist(d1);
			eTrans.commit();
		} catch (Exception e) {
			
		}
		
		
		

	}

}
