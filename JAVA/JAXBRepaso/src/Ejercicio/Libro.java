package Ejercicio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"titulo","autor","editorial","isbn"})
public class Libro {
	private String titulo;
	private String autor;
	private String editorial;
	private String isbn;
	
	public Libro() {
	
	}
	
	public Libro(String titulo, String autor, String editorial,String isbn) {
		this.titulo=titulo;
		this.autor=autor;
		this.editorial=editorial;
		this.isbn=isbn;
	}

	@XmlElement(name="Titulo")
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@XmlElement(name="Autor")
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@XmlElement(name="Editorial")
	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	@XmlElement(name="ISBN")
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	

}
