package Ejercicio;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nombre","lugar","listaLibros"})
public class Libreria {
	private String nombre;
	private String lugar;
	private ArrayList<Libro> listaLibros;

	public Libreria() {
		listaLibros = new ArrayList<>();

	}

	public Libreria(ArrayList<Libro> listaLibros, String nombre, String lugar) {
		this.listaLibros = listaLibros;
		this.nombre = nombre;
		this.lugar = lugar;
	}

	@XmlElement(name="Nombre")
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

	@XmlElementWrapper(name="ListaLibros")
	@XmlElement(name="Libro")
	public ArrayList<Libro> getListaLibros() {
		return listaLibros;
	}

	public void setListaLibros(ArrayList<Libro> listaLibros) {
		this.listaLibros = listaLibros;
	}
	
}
