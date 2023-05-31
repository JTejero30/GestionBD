package controlador;

import usuario.MostrarTexto;

/*Objetivo 1: INSERTAR EN LA TABLA 1 hecho
 * Objetivo 2: INSERTAR EN LA TABLA 2 asociado a tabla 1 :
 * 			hecho al registrar una venta, 
 * 			si el cliente no existe lo crea
 * 			si el producto no existe lo crea
 * Objetivo3: BORRAR T2(CLIENTES) hecho
 * Objetivo4: BORRAR T1(PRODUCTOS) Y QUE SE BORREN REGISTROS DE T1T2(VENTAS) hecho
 * Objetivo5: MODIFICAR EN T1 Y T2 EXCEPTO EL ID hecho
 * Objetivo6: ASOCIAR UN CLIENTE A UN NUEVO PRODUCTO 
 * 			hecho en el insert de producto, al crear pregunta si desea registrar la venta  
 * 			si el cliente seleccionado no existe lo crea
 * Objetivo7: CONSULTAS:
 * 			-Productos:
 *				-Clientes de un producto    	(obligatorio)
 *				-Productos por categoria		(extra)	no hecho
 * 			-Clientes:
 * 				-Productos de un cliente    	(obligatorio)
 * 				-Total Clientes					(obligatorio)
 * 				-Cliente con mas compras		(extra)
 * 			-Ventas:
 * 				-Producto mas vendido			(obligatorio)
 * 				-Producto sin ventas			(obligatorio)
 * 				-Impagos 						(extra)
 * 				-Los mas vendidos por categoria	(extra) no hecho
 * 				
 * 	*/

public class Controlador {

	public static void main(String[] args) {

		char opcion;

		try {
			do {

				opcion = MostrarTexto.inicio();

				switch (opcion) {
				case '1':

					opcion = MostrarTexto.producto();// los metodos mostrar texto muestran el menu por consola y
														// devuelven un char opcion

					Menus.opcionProducto(opcion); // los metodos opcionX cogen ese char opcion para ejecutar el switch
													// interno

					break;
				case '2':

					opcion = MostrarTexto.cliente();

					Menus.opcionCliente(opcion);

					break;
				case '3':

					opcion = MostrarTexto.ventas();

					Menus.opcionVenta(opcion);

				}
			} while (opcion != '1' && opcion != '2' && opcion != '3' && opcion != '4');
		} catch (Exception e) {

			System.out.println("Algo ha ido mal");
		}

	}

}
