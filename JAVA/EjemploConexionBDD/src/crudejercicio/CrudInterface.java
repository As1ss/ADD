package crudejercicio;

import java.util.List;

public interface CrudInterface {
	
	public void create(Departamento dept);
	public List<Departamento>read();
	public void update(Departamento dept);
	public void delete(Departamento dept);
	

}
