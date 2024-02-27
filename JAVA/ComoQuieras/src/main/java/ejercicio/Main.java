package ejercicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[]args){

        EntityManagerFactory eFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager eManager = eFactory.createEntityManager();
        EntityTransaction eTrans = eManager.getTransaction();

        try {
            eTrans.begin();
            Departamento d = new Departamento();
            d.setId(80);
            d.setNombre("Repaso");
            d.setLocalizacion("Cuenca");
            eManager.persist(d);
            eTrans.commit();
        }
            catch(Exception e){
                eTrans.begin();
                Departamento d = new Departamento();
                d.setId(1);
                d.setNombre("Ventas");
                d.setLocalizacion("Madrid");
                eManager.persist(d);
                eTrans.commit();
            }

        


    }
    public EntityTransaction getTransaction(){
        EntityManagerFactory eFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager eManager = eFactory.createEntityManager();
        EntityTransaction eTrans = eManager.getTransaction();
        return eTrans;
    }
}
