package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;		

public class BaseDatos {

	public static void ejecutar(String sql) throws ClassNotFoundException, SQLException {

		Connection conexion = null;
		Statement sentenciaSQL = null;
		int resultado = 0;

		try {
			// conectar con la base de datos
//			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestorventas", "root", "");// proporcionamos
																										// la
																										// dirección, el
																										// administrador
																										// y
																										// la clave

			// creamos sentencias ejecutables sobre esa conexión
			sentenciaSQL = conexion.createStatement();

			// almaceno el resultado de la sql en un resulset (conjunto de registros)

			// sql = "INSERT INTO `propietarios` (`idpropietario`, `nombre`) VALUES (NULL,
			// '" + nombrePropietario + "')";

			resultado = sentenciaSQL.executeUpdate(sql);

			if (resultado >= 1) {
				System.out.println("Ejecución terminada.");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			// System.out.println("Error");
		} finally {
			sentenciaSQL.close();
			conexion.close();

		}

	}

	public static void mostrarTodo(String tabla) throws SQLException   {
			Connection conexion = null;
			Statement sentenciaSQL = null;
			ResultSet rs;
			String sql;
	
			try {
	//			Class.forName("com.mysql.jdbc.Driver");
				conexion = DriverManager.getConnection("jdbc:mysql://localhost/gestorventas", "root", "");// proporcionamos
																											// la
																											// dirección, el
																											// administrador
																											// y
																											// la clave
				sentenciaSQL = conexion.createStatement();
	
				sql = "SELECT * FROM " + tabla;
	
				rs = sentenciaSQL.executeQuery(sql);
	
				while (rs.next()) {
	
					switch (tabla) {
					case "clientes":
	
						System.out.println(rs.getString("id") + ". " + rs.getString("nombre") + ", " + rs.getString("dni")
								+ ", " + rs.getString("nombreEmpresa"));
						break;
	
					case "ventas":
						System.out.println("id:" + rs.getInt("id") + ". " + rs.getInt("idProducto") + rs.getInt("idCliente")
								+ rs.getInt("pagado"));
	
					case "productos":
						System.out.println("id:" + rs.getInt("id") + ". " + rs.getString("nombre") + "  "
								+ rs.getString("categoria") + "  " + rs.getDate("fecha"));
	
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
