package ejercicio;

import java.util.ArrayList;
import java.util.List;

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

		Persona p1 = new Persona();
		List<SuperHeroe> miembros = new ArrayList<SuperHeroe>();
		p1.setName("Alexis");
		SuperHeroe sh1 = new SuperHeroe();
		sh1.setName("Campeón de las Telas");
		sh1.setPersona(p1);
	
		miembros.add(sh1);
		
		Equipo equipo = new Equipo();
		equipo.setName("Liga de los Tolays");
		equipo.setMiembros(miembros);
		sh1.setTeam(equipo);
		eManager.persist(sh1);
		
		

		eTrans.commit();

	}

}
