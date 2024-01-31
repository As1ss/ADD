package ejercicio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "producto")
public class Producto {

	@Id
	private int id_producto;
	
	private String nombre;
	private int cantidad;
	

	public Producto() {

	}

	public Producto(int id_producto, String nombre, int cantidad) {
		this.id_producto = id_producto;
		this.nombre = nombre;
		this.cantidad = cantidad;
		
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
