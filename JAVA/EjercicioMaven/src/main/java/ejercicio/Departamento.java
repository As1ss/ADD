package ejercicio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity(name = "departamento")
public class Departamento {

	@Id
	private int dept_no;
	
	private String dnombre;
	private String loc;

	public Departamento() {

	}

	public Departamento(int dept_no, String dnombre, String loc) {
		this.dept_no = dept_no;
		this.dnombre = dnombre;
		this.loc = loc;
	}
	
	public int getDept_no() {
		return dept_no;
	}
	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}
	public String getDnombre() {
		return dnombre;
	}
	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}

}
