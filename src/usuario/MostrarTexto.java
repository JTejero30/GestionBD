package usuario;

import controlador.Lector;

public class MostrarTexto {

	public static char inicio() {

		char opcion = 0;

		try {
			opcion = Lector.leerChar("************* \n" + "INICIO \n" + "************* \n" + "1.Productos \n" + "2.Clientes \n"
					+ "3.Ventas \n" + "*************");

		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}
		return opcion;

	}

	public static char producto() {

		char opcion = 0;
		try {
			opcion = Lector.leerChar("************* \n" + "PRODUCTOS \n" + "************* \n" + "1.Añadir producto \n"
					+ "2.Modificar producto \n" + "3.Eliminar producto \n" + "4.Consultas \n" + "5.Atras \n"
					+ "*************");

		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}
		return opcion;

	}

	public static char cliente() {

		char opcion = 0;

		try {
			opcion = Lector.leerChar("************* \n" + "CLIENTES \n" + "************* \n" + "1.Añadir cliente \n"
					+ "2.Modificar datos de cliente \n" + "3.Eliminar cliente \n" + "4.Consultas \n" + "5.Atras \n"
					+ "*************");

		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}
		return opcion;

	}

	public static char ventas() {

		char opcion = 0;
		try {
			opcion = Lector.leerChar("************* \n" + "VENTAS \n" + "************* \n" + "1.Registrar una venta \n"
					+ "2.Modificar datos de una venta \n" + "3.Eliminar venta \n" + "4.Consultas \n" + "5.Atras \n"
					+ "*************");

		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}
		return opcion;

	}
}
