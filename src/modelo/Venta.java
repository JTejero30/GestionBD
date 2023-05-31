package modelo;

import java.sql.Date;

public class Venta {

	int id;
	int idProducto;
	int idCliente;
	//int precioVenta;// a futuro se utilizara con precioCompra
	//int cantidadVendida; a futuro 
	Date fechaVenta;
	boolean pagado; // para controlar impagos

	public Venta(int id, int idProducto, int idCliente, int precioVenta, int cantidadVendida, Date fechaVenta,
			boolean pagado) {
		super();
		this.id = id;
		this.idProducto = idProducto;
		this.idCliente = idCliente;
		this.fechaVenta = fechaVenta;
		this.pagado = pagado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}



}
