package crudejercicio;

public class Departamento {
	private int dept_no;
	private String dnombre;
	private String loc;

	public Departamento() {

	}

	public Departamento(int newDept_no, String newDnombre, String newLoc) {
		this.dept_no = newDept_no;
		this.dnombre = newDnombre;
		this.loc = newLoc;
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
