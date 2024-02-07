package ejercicio;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "equipo")
public class Equipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	
	@Column(name = "nombre")
	private String name;

	@OneToMany(mappedBy="team")
	private List<SuperHeroe> miembros;

	public Equipo() {
		
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

	public List<SuperHeroe> getMiembros() {
		return miembros;
	}

	public void setMiembros(List<SuperHeroe> miembros) {
		this.miembros = miembros;
	}

}
