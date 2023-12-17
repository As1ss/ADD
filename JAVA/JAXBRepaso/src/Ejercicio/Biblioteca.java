package Ejercicio;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="Biblioteca")
@XmlType(propOrder = {"nombre","lugar","libreria"})
public class Biblioteca {
	
	
	private String nombre;
	private String lugar;
	private ArrayList<Libreria> libreria;

	public Biblioteca() {
		libreria= new ArrayList<>();
	}

	public Biblioteca(ArrayList<Libreria>libreria, String nombre, String lugar) {
		this.libreria=libreria;
		this.nombre=nombre;
		this.lugar=lugar;
		
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	@XmlElementWrapper(name="Librerias")
	@XmlElement(name="Libreria")
	public ArrayList<Libreria> getLibreria() {
		return libreria;
	}

	public void setLibreria(ArrayList<Libreria> libreria) {
		this.libreria = libreria;
	}
	

}
