package modelo;

public class Cliente {

	int id;
	int idProducto;
	String nombre;
	String dni;
	String nombreEmpresa;

	public Cliente(int id, String nombre, String dni, String nombreEmpresa) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.nombreEmpresa = nombreEmpresa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", dni=" + dni
				+ ", nombreEmpresa=" + nombreEmpresa + "]";
	}





}
