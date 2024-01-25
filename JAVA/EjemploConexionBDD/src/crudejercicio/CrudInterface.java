package crudejercicio;

import java.util.List;

public interface CrudInterface {
	
	public void create(Departamento dept);
	public Departamento read(int id);
	public List<Departamento> readAll();
	public void update(Departamento dept);
	public void delete(Departamento dept);
	

}
