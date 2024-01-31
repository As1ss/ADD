package ejercicio;

import org.hibernate.Transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Ejercicio2 {

	public static void main(String[] args) {
			EntityManagerFactory eFactory = Persistence.createEntityManagerFactory("m");
			EntityManager eManager = eFactory.createEntityManager();
			
			EntityTransaction eTrans = eManager.getTransaction();
			
			
			try {
				eTrans.begin();
				Producto producto = new Producto(1290,"Juanolas",25);
				
		
				
				eManager.detach(producto);
				
				
				eManager.persist(producto);
				
				
				Producto productoObtenido = eManager.find(Producto.class, 1290);
				
			
				
				
				productoObtenido.setCantidad(22);
				
				

				eTrans.commit();
			} catch (IllegalStateException  e) {
				e.printStackTrace();
			}
	}

}
