package ejercicio;



import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity(name = "superheroe")
public class SuperHeroe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "nombre")
	private String name;

	@OneToOne()
	@JoinColumn(name = "persona_id")
	@Cascade(CascadeType.ALL)
	private Persona persona;
	
	
	@ManyToOne
	@JoinColumn(name="equipo_id")
	@Cascade(CascadeType.PERSIST)
	private Equipo team;

	public SuperHeroe() {

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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Equipo getTeam() {
		return team;
	}
	public void setTeam(Equipo team) {
		this.team = team;
	}

}
