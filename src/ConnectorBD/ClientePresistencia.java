package ConnectorBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClientePresistencia {

	public static Connection getConexion() {
		Connection con = null;
		try {
			
			//Para MySQL esta cadena es siemrpe asi
			Class.forName("com.mysql.jdbc.Driver");
			//Definimos la ruta de la BD Instituto
			String url = "jdbc:mysql://localhost:3306/Hotel?useSSL=false";
			//Definimos el usuario y la contraseña
			String user = "root";
			String password = "Studium2020;";
			                                 //Cadena de Conexion
			con = DriverManager.getConnection(url, user, password);
			
			//System.out.println("Conexion OK");

		} catch (ClassNotFoundException e) {
			System.out.println("Error cargar el driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error con la BD");
			e.printStackTrace();
		}
		return con;
	}
	
	public static int createCliente(String nombre, String apellidos, String email, String dni, String clave) {
		/* Devuelve el id del nuevo cliente */
		
	}
	
	public static String readCliente(int idCliente, String campo) {
		/* Devuelve el valor de la columna "campo" del cliente identificado por "idCliente" */
	}
 
	public static boolean updateCliente(int idCliente, String campo, String nuevoValor) {
		/* Actualiza el valor de la columna "campo" del cliente identificado por "idCliente". Devuelve true si se ha logrado actualizar. */
	}
 
	public static boolean deleteCliente(int idCliente) {
		/* Elimina el cliente identificado por "idCliente". Devuelve true si se ha logrado eliminar. */
	}
}

