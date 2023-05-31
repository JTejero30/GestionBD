package controlador;

import usuario.GeneraSQL;

public class Menus {

	public static void opcionProducto(char opcion) {

		String nombreProducto;
		String cadena;
		char opcionSelect;

		try {

			switch (opcion) {
			case '1':// insert producto
				cadena = GeneraSQL.pedirInsertProducto(); // aqui guardo la cadena en un string

				nombreProducto = GeneraSQL.buscarNombre(cadena);// saco el nombre del producto para que no tenga que
																// repetirlo el usuario

				BaseDatos.ejecutar(cadena); // ejecuto el insert
				BaseDatos.ejecutar(GeneraSQL.registrarVenta(nombreProducto)); // le paso por parametro al metodo
																				// sobrecargado el nombreProducto
				// esto son requisitos 1 y 6 "Insert en tabla 1" y"Se puede asociar un cliente a
				// un nuevo producto"

				break;
			case '2':// update producto

				BaseDatos.ejecutar(GeneraSQL.updateProducto());
				break;

			case '3':// delete producto

				BaseDatos.ejecutar(GeneraSQL.deleteProducto());// REQUISITO 4 "borrar en t1 y que se borre de
																// t1_has_t2"

				break;

			case '4':// select

				opcionSelect = Lector.leerChar(
						"Que quiere consultar: \n" + "1.Sacar los clientes que han comprado un determinado producto \n"
								+ "2.Producto más vendido \n" + "3.Productos sin ventas");
				switch (opcionSelect) {
				case '1':// clientes que compran un producto
					FuncionesBD.consultar("clientesDeUnProducto");
					break;
				case '2':// producto mas vendido
					FuncionesBD.consultar("productoMasVendido"); // CONSULTA REQUERIDA producto->clientes

					break;
				case '3':// productos sin ventas
					FuncionesBD.consultar("productosSinVenta"); // CONSULTA REQUERIDA productos sin ventas
					break;

				default:
					break;
				}

			case '5':// atras inicio()

				break;

			default:
				break;
			}

		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}
	}

	public static void opcionCliente(char opcion) {

		char opcionSelect;
		try {

			switch (opcion) {
			case '1':// insert cliente

				BaseDatos.ejecutar(GeneraSQL.pedirInsertCliente()); // REQUISITO 2 "Insert en tabla 2"

				break;
			case '2':// update cliente REQUISITO 4 "update tabla2 excepto id"

				BaseDatos.ejecutar(GeneraSQL.updateCliente());

				break;

			case '3':// delete cliente

				BaseDatos.ejecutar(GeneraSQL.deleteCliente()); // REQUISITO 3 "Borrar en tabla2"

				break;

			case '4':// select

				opcionSelect = Lector.leerChar("Que quiere consultar: \n" + "1.Productos de un cliente \n"
						+ "2.Numero total de clientes \n " + "3.Impagos");

				switch (opcionSelect) {
				case '1':
					FuncionesBD.consultar("productosDeUnCliente");// CONSULTA REQUERIDA cliente->productos

					break;
				case '2':
					FuncionesBD.consultar("totalClientes");// CONSULTA REQUERIDA total clientes

					break;
				default:
					break;
				}
				break;

			case '5':// atras inicio()

				break;

			default:
				break;
			}

		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}
	}

	public static void opcionVenta(char opcion) {

		char opcionSelect;
		try {
			switch (opcion) {
			case '1':
				BaseDatos.ejecutar(GeneraSQL.registrarVenta());

				break;
			case '2':

				break;
			case '3':
				BaseDatos.ejecutar(GeneraSQL.deleteVenta());

				break;
			case '4':
				opcionSelect = Lector.leerChar("Que quiere consultar: 1.Impagos");

				switch (opcionSelect) {

				case '1':
					FuncionesBD.consultar("impagos");

					break;
				}
				break;
			case '5':
				// atras

				break;

			default:
				break;
			}

		} catch (Exception e) {
			System.out.println("Algo ha ido mal");
		}
	}

}
