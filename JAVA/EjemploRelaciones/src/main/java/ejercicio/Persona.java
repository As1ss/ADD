package ejercicio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity(name = "persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@OneToOne(mappedBy ="id")
	private int id;
	
	

	
	@Column(name = "nombre")
	private String name;

	public Persona() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
