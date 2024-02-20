package crudejercicio;

import java.util.ArrayList;


public class Ejercicio9 {

	private static OperacionesDepartamento depService;
	
public Ejercicio9() {

	
	
}
	public static void main(String[] args) {
	

		depService = new OperacionesDepartamento();
		
		//Consultar todos los departaemntos
		consultarDepartamentos();
		//Insertar departamento
		
		Departamento dept = new Departamento(60,"RRHH","BILBAO");
		//insertarDepartamento(dept);
		
		//Consultamos departamentos para verificar los cambiox
		consultarDepartamentos();
		
		dept.setLoc("BILBAO");
		
		actualizarDepartamento(dept);
		
		consultarDepartamentos();
		
		eliminarDepartamento(dept);
		
		consultarDepartamentos();
		
	
		
		
		
		
		


	}
	private static void actualizarDepartamento(Departamento dept) {
		
		depService.update(dept);
		
	}
	private static void consultarDepartamentos() {
		ArrayList<Departamento> departamentos;

		OperacionesDepartamento depService = new OperacionesDepartamento();
		
//		departamentos = (ArrayList<Departamento>) depService.read();
//		
//
//
//		for(Departamento dep : departamentos) {
//			System.out.println("Departamento: "+dep.getDept_no());
//			System.out.println("Nombre del departamento: "+dep.getDnombre());
//			System.out.println("Localización del departamento: "+dep.getLoc());
//			System.out.println("----------------------------------------------");
//		}
		
	}
	private static void insertarDepartamento(Departamento dept) {
		
		depService.create(dept);
	
	}
	private static void eliminarDepartamento(Departamento dept) {
		depService.delete(dept);
		
	}

}
