package modelo;

import java.sql.Date;

public class Producto {

	
	int id;
	String nombreProducto;
	String categoria;
	//int stock; necesito triggers
	//int precioCompra; a futuro para sacar beneficios, un producto puede tener mas de un precio de compra
	Date fechaCompra;
	
	
	public Producto(int id, String nombreProducto, String categoria, Date fechaCompra) {
		super();
		this.id = id;
		this.nombreProducto = nombreProducto;
		this.categoria = categoria;
		this.fechaCompra = fechaCompra;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombreProducto=" + nombreProducto + ", categoria=" + categoria
				+ ", fechaCompra=" + fechaCompra + "]";
	}
	
	
}
