package Ejericio1;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Libreria {
	private ArrayList<Libro> listaLibros;
	private String nombre;
	private String lugar;
	
	public Libreria(ArrayList<Libro> listaLibros,String nombre,String lugar) {
		this.listaLibros=listaLibros;
		this.nombre=nombre;
		this.lugar=lugar;
	}
	public Libreria() {
		
	}

	@XmlElementWrapper
	@XmlElement (name="Libro")
	public ArrayList<Libro> getListaLibros() {
		return listaLibros;
	}

	public void setListaLibros(ArrayList<Libro> listaLibros) {
		this.listaLibros = listaLibros;
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
	
}
