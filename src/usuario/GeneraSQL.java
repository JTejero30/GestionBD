package usuario;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

import controlador.BaseDatos;
import controlador.FuncionesBD;
import controlador.Lector;

public class GeneraSQL {

	public static String pedirInsertProducto() {

		String sql = "";
		String nombreProducto;
		String categoria;

		try {

			nombreProducto = Lector.leerString("Introduzca el nombre");
			categoria = Lector.leerString("Introduzca la categoria");

			sql = "INSERT INTO `productos` (`id`, `nombre`, `categoria`, `fecha`) VALUES (NULL,'" + nombreProducto
					+ "' , '" + categoria + "', CURRENT_TIMESTAMP)";

		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}

		return sql;
	}

	public static String pedirInsertCliente() {

		String sql = "";
		String nombre;
		String dni;
		String nombreEmpresa;
		Date fecha_nac;

		try {

			nombre = Lector.leerString("Introduzca el nombre");
			dni = Lector.leerString("Introduzca el dni");
			nombreEmpresa = Lector.leerString("Introduzca la empresa si no es particular");
			
			if (nombreEmpresa.isEmpty()) {
				nombreEmpresa = "particular";
			}
			
			sql = "INSERT INTO `clientes` (`id`, `nombre`, `dni`, `nombreEmpresa`) VALUES (NULL," + " '" + nombre
					+ "','" + dni + "', '" + nombreEmpresa + "')";

		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}

		return sql;
	}

	public static String registrarVenta() {

		String sql = "";
		String nombreCliente;
		int idProducto;
		int idCliente;
		byte pagado;// al parecer MySql trata los boolean como tinyint, dando valor
		// 0 a false y 1 a true
		char opcion;
		String nombreProducto;
		int precio;
		int cantidad;

		try {
			nombreProducto = Lector.leerString("Introduzca el nombre del producto");
			idProducto = FuncionesBD.buscarIdProducto(nombreProducto);

			if (idProducto == 0) {

				System.out.println("Ese producto no existe, vamos a crearlo");

				sql = pedirInsertProducto(); // guardo en sql el insert del nuevo producto

				BaseDatos.ejecutar(sql);// lo ejecuto

				nombreProducto = buscarNombre(sql); // saco el nombre de ese String
				idProducto = FuncionesBD.buscarIdProducto(nombreProducto); // saco el id del nombre, sino hiciese esto
																			// el id seria 0
			}

			nombreCliente = Lector.leerString("Introduzca el nombre del cliente");
			idCliente = FuncionesBD.buscarIdCliente(nombreCliente);

			if (idCliente == 0) {

				System.out.println("Ese cliente no existe, vamos a crearlo");

				sql = pedirInsertCliente(); // guardo en sql el insert del nuevo producto

				BaseDatos.ejecutar(sql);// lo ejecuto

				nombreCliente = buscarNombre(sql); // saco el nombre de ese String
				idCliente = FuncionesBD.buscarIdCliente(nombreCliente);
			}

			precio = Lector.leerInt("Precio:");
			cantidad = Lector.leerInt("Cantidad:");

			do {
				opcion = Lector.leerChar("¿Ha pagado el cliente el pedido? \n" + "Y/N");
			} while (opcion != 'Y' && opcion != 'N');

			if (opcion == 'Y') {
				pagado = 1;
			} else {
				pagado = 0;
			}

			sql = "INSERT INTO `ventas` (`id`,`idProducto`, `idCliente`, `pagado`, `precio`, `cantidad`) VALUES (NULL,"
					+ idProducto + " ," + idCliente + "," + pagado + "," + precio + "," + cantidad + ")";
		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}

		return sql;
	}

	public static String registrarVenta(String nombreProducto) {

		char opcion;
		String sql = "";
		String nombreCliente;
		int idProducto;
		int idCliente;
		byte pagado;
		int precio;
		int cantidad;

		try {

			opcion = Lector.leerChar("¿Desea registrar una venta del producto? \n" + "(Y/N)");

			if (opcion == 'Y') {

				nombreCliente = Lector.leerString("Introduzca el nombre del cliente");
				idCliente = FuncionesBD.buscarIdCliente(nombreCliente);

				if (idCliente == 0) {

					System.out.println("Ese cliente no existe, vamos a crearlo");

					sql = pedirInsertCliente(); // guardo en sql el insert del nuevo producto

					BaseDatos.ejecutar(sql);// lo ejecuto

					nombreCliente = buscarNombre(sql); // saco el nombre de ese String
					idCliente = FuncionesBD.buscarIdCliente(nombreCliente);
				}

				do {
					opcion = Lector.leerChar("¿Ha pagado el cliente el pedido? \n" + "Y/N");
				} while (opcion != 'Y' && opcion != 'N');

				if (opcion == 'Y') {
					pagado = 1;
				} else {
					pagado = 0;
				}

				precio = Lector.leerInt("Precio:");
				cantidad = Lector.leerInt("Cantidad:");

				idProducto = FuncionesBD.buscarIdProducto(nombreProducto);

				sql = "INSERT INTO `ventas` (`id`,`idProducto`, `idCliente`, `pagado`, `precio`, `cantidad`) VALUES (NULL,"
						+ idProducto + " ," + idCliente + "," + pagado + "," + precio + "," + cantidad + ")";
			}
		} catch (Exception e) {
			System.out.println("Error al registrar la venta");
		}
		return sql;
	}

	public static String updateProducto() {

		String sql = "";
		int id;
		String nuevoDato;
		char opcion;
		String nombre;

		try {

			do {
				opcion = Lector.leerChar("Buscar producto: \n" + "1.Por nombre \n" + "2.Por id");

				if (opcion == '1') {
					nombre = Lector.leerString("Introduzca el nombre");
					id = FuncionesBD.buscarIdProducto(nombre);
				} else {

					BaseDatos.mostrarTodo("productos");
					id = Lector.leerInt("***************************** \n" + "Seleccione el id del producto:");
				}

			} while (id == 0); // nos aseguramos de que el producto existe

			opcion = Lector.leerChar("¿Qué deseas modificar? \n" + "1.Nombre \n" + "2.Categoria \n");

			switch (opcion) {

			case '1':
				nuevoDato = Lector.leerString("Introduce el nuevo nombre");

				sql = "UPDATE productos SET nombre='" + nuevoDato + "' WHERE id=" + id;

				break;
			case '2':
				nuevoDato = Lector.leerString("Introduce la nueva categoria");

				sql = "UPDATE productos SET categoria='" + nuevoDato + "' WHERE id=" + id;

				break;

			default:
				break;
			}

		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}
		return sql;
	}

	public static String updateCliente() {

		String sql = "";
		int id;
		String nuevoDato;
		char opcion;

		try {

			BaseDatos.mostrarTodo("clientes");
			id = Lector.leerInt("***************************** \n" + "Seleccione el id del cliente:");

			opcion = Lector
					.leerChar("¿Qué deseas modificar? \n" + "1.Nombre \n" + "2.Dni \n" + "3.Nombre de la empresa");

			switch (opcion) {

			case '1':
				nuevoDato = Lector.leerString("Introduce el nuevo nombre");

				sql = "UPDATE clientes SET nombre='" + nuevoDato + "' WHERE id=" + id;

				break;
			case '2':
				nuevoDato = Lector.leerString("Introduce el nuevo dni");

				sql = "UPDATE clientes SET dni='" + nuevoDato + "' WHERE id=" + id;

				break;
			case '3':
				nuevoDato = Lector.leerString("Introduce el nuevo nombre de la empresa");

				sql = "UPDATE clientes SET nombreEmpresa='" + nuevoDato + "' WHERE id=" + id;

			default:
				break;
			}

		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}
		return sql;
	}

	public static String deleteCliente() {

		String sql = "";
		int id;
		String dni;
		char opcion;

		try {

			opcion = Lector.leerChar("¿Cómo lo quiere borrar? \n " + "1.Por dni \n " + "2.Por id");

			if (opcion == '1') {

				dni = Lector.leerString("Introduzca el dni");

				sql = "DELETE FROM clientes WHERE dni='" + dni + "'";

			} else {

				BaseDatos.mostrarTodo("clientes");

				System.out.println("******************");
				id = Lector.leerInt("Introduzca el id");

				sql = "DELETE FROM clientes WHERE id=" + id;
			}
		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}
		return sql;

	}

	public static String deleteProducto() {

		String sql = "";
		int id;
		String nombre;
		char opcion;

		try {

			opcion = Lector.leerChar("¿Como quiere borrar? \n" + "1.Por nombre \n" + "2.Por id");

			if (opcion == '1') {
				nombre = Lector.leerString("Introduzca el nombre");

				sql = "DELETE FROM productos WHERE nombre='" + nombre + "'";

				id = FuncionesBD.buscarIdProducto(nombre); // buscamos el id si es que mete por nombre

			} else {

				id = Lector.leerInt("Introduzca el id");

				sql = "DELETE FROM productos WHERE id=" + id;

			}

			BaseDatos.ejecutar(deleteVenta(id)); // aqui borramos en t1ht2
		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}
		return sql;

	}

	public static String deleteVenta(int idProducto) { // metodo sobrecargado

		String sql = "";

		try {

			sql = "DELETE FROM ventas WHERE idProducto=" + idProducto;

		} catch (Exception e) {
			System.out.println("ERROR");
		}

		return sql;
	}

	public static String deleteVenta() {

		String sql = "";
		int numero;

		try {
			BaseDatos.mostrarTodo("ventas");

			numero = Lector.leerInt("Seleccione el id");

			sql = "DELETE FROM ventas WHERE id=" + numero;

		} catch (Exception e) {
			System.out.println("Error");
		}
		return sql;

	}

	public static void select(String sql) {

	}

	public static String buscarNombre(String cadena) {

		String nombreProducto;
		int primerIndice = cadena.indexOf('\'');
		int segundoIndice = cadena.indexOf('\'', primerIndice + 1);

		nombreProducto = cadena.substring(primerIndice + 1, segundoIndice);

		return nombreProducto;
	}
	
}
