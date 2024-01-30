package ejercicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        // Crea la fábrica de EntityManager usando la unidad de persistencia definida en persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("m");

        // Crea un EntityManager
        EntityManager em = emf.createEntityManager();

        // Obtiene una transacción
        EntityTransaction transaction = em.getTransaction();

        try {
            // Inicia la transacción
            transaction.begin();
        

            // Crea una entidad y pérsistela
            Departamento departamento =  em.find(Departamento.class, 70);
         
            departamento.setDnombre("TEST");
          
           // em.persist(departamento);
            

            // Confirma la transacción
            transaction.commit();
        } catch (Exception e) {
            // En caso de error, realiza un rollback
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cierra el EntityManager
            em.close();

            // Cierra la fábrica de EntityManager
            emf.close();
        }
    }
}

