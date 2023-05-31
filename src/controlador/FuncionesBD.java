package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FuncionesBD {

	public static int buscarIdProducto(String nombreProducto) throws ClassNotFoundException, SQLException {
	
			Connection conexion = null;
			Statement sentenciaSQL;
	
			int id = 0;
	
			try {
				// conectar con la base de datos
	//			Class.forName("com.mysql.jdbc.Driver");
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestorventas", "root", "");// proporcionamos
																											// la dirección,
																											// el
																											// administrador
																											// y la clave
	
				// creamos sentencias ejecutables sobre esa conexión
				sentenciaSQL = conexion.createStatement();
	
				// almaceno el resultado de la sql en un resulset (conjunto de registros)
				ResultSet rs = sentenciaSQL.executeQuery("SELECT id FROM productos WHERE nombre= '" + nombreProducto + "'");
				// chequeo que el result set no sea vacío, moviendo el cursor a la
				// primer fila. (El cursor inicia antes de la primer fila)
				while (rs.next()) {
					// Si hay resultados obtengo el valor.
	
					id = rs.getInt("id");
				}
	
			} catch (SQLException ex) {
				ex.printStackTrace();
				// System.out.println("Error");
			} finally {
				conexion.close();
	
			}
			return id;
		}

	public static int buscarIdCliente(String nombre) throws ClassNotFoundException, SQLException {
	
			Connection conexion = null;
			Statement sentenciaSQL;
	
			int id = 0;
	
			try {
				// conectar con la base de datos
	//			Class.forName("com.mysql.jdbc.Driver");
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestorventas", "root", "");// proporcionamos
																											// la dirección,
																											// el
																											// administrador
																											// y la clave
	
				// creamos sentencias ejecutables sobre esa conexión
				sentenciaSQL = conexion.createStatement();
	
				// almaceno el resultado de la sql en un resulset (conjunto de registros)
				ResultSet rs = sentenciaSQL.executeQuery("SELECT id FROM clientes WHERE nombre= '" + nombre + "'");
				// chequeo que el result set no sea vacío, moviendo el cursor a la
				// primer fila. (El cursor inicia antes de la primer fila)
				while (rs.next()) {
					// Si hay resultados obtengo el valor.
	
					id = rs.getInt("id");
				}
	
			} catch (SQLException ex) {
				ex.printStackTrace();
				// System.out.println("Error");
			} finally {
				conexion.close();
	
			}
			return id;
		}

	public static void consultar(String opcion) throws SQLException, ClassNotFoundException {
			Connection conexion = null;
			Statement sentenciaSQL = null;
			ResultSet rs;
			String nombre;
			String sql = "";
	
			try {
	//			Class.forName("com.mysql.jdbc.Driver");
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestorventas", "root", "");// proporcionamos
																											// la
																											// dirección, el
																											// administrador
																											// y
																											// la clav
				sentenciaSQL = conexion.createStatement();
	
				switch (opcion) {
				case "clientesDeUnProducto":
	
					nombre = Lector.leerString("Introduzca el nombre del producto");
	
					sql = "SELECT clientes.nombre as 'cliente', sum(ventas.cantidad) as 'ventas', productos.nombre as 'producto' "
							+ "FROM clientes " + "INNER JOIN ventas on clientes.id=ventas.idCliente "
							+ "INNER JOIN productos on productos.id=ventas.idProducto " + "WHERE productos.nombre='"
							+ nombre + "'" + "GROUP by clientes.nombre; ";
	
					break;
				case "productosDeUnCliente":
					nombre = Lector.leerString("Introduzca el nombre del cliente");
	
					sql = "select Sum(ventas.cantidad) as 'cantidad', productos.nombre "
							+ "FROM ventas "
							+ "INNER JOIN productos on productos.id=ventas.idProducto "
							+ "INNER JOIN clientes on clientes.id=ventas.idCliente "
							+ "WHERE clientes.nombre='"+ nombre +"'"
							+ "GROUP BY productos.nombre; ";
	
					break;
	
				case "productoMasVendido":
	
					sql = "SELECT productos.nombre ,COUNT(*) as 'Unidades vendidas'\r\n" + "From productos\r\n"
							+ "INNER JOIN ventas on productos.id=ventas.idProducto\r\n"
							+ "GROUP BY idProducto order by count(idProducto) DESC LIMIT 1;";
	
					break;
				case "productosSinVenta":
	
					sql = "SELECT productos.nombre, productos.fecha FROM productos  WHERE productos.id not IN \r\n"
							+ "							(SELECT ventas.idProducto			FROM ventas );";
					break;
				case "totalClientes":
					
					sql= "SELECT COUNT(*) as 'total' from clientes";
					break;
				case "impagos":
	
				sql= "select clientes.nombre from clientes INNER JOIN ventas on ventas.idCliente=clientes.id where pagado=0;" ;
				default:
					break;
				}
				rs = sentenciaSQL.executeQuery(sql);
	
				while (rs.next()) {
	
					switch (opcion) {
					case "clientesDeUnProducto":
	
						System.out.println(rs.getString("cliente") + " ha comprado " + rs.getInt("ventas") + " unidades de "
								+ rs.getString("producto"));
						break;
					case "productosDeUnCliente":
						System.out.println(rs.getInt("cantidad")+" unidades de "+rs.getString("nombre"));
						
						break;
					case "productoMasVendido":
						System.out.println("El producto más vendido es " + rs.getString("nombre") + " con "
								+ rs.getInt("Unidades vendidas") + " unidades vendidas.");
						break;
					case "productosSinVenta":
						System.out.println(rs.getDate("fecha") + "      " + rs.getString("nombre"));
	
						break;
					case "totalClientes":
						System.out.println("Tienes un total de "+rs.getString("total")+" clientes");
						break;
					case "impagos":
						System.out.println(rs.getString("nombre"));
					
						
					default:
						break;
					}
	
				}
	
			} catch (SQLException ex) {
				ex.printStackTrace();
				System.out.println("Error");
			} finally {
				sentenciaSQL.close();
				conexion.close();
	
			}
	
		}

}
