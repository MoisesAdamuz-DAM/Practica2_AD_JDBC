package hotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientePersistencia {

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
			
			System.out.println("Conexion OK");

		} catch (ClassNotFoundException e) {
			System.out.println("Error cargar el driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error con la BD");
			e.printStackTrace();
		}
		return con;
	}

	
	
	public static int createCliente(String nombre, String apellidos, String email, String dni, String clave)
	{
		/* Devuelve el id del nuevo cliente */
		
		Connection con = getConexion();
		int respuesta = 0;
		try {
			// Creamos un STATEMENT para ejecutar la sentencia SQL.
			Statement sta = con.createStatement();
			String cadenaSQL = "INSERT INTO Clientes" + " VALUES (null,'" 
			+ nombre + "', '" + apellidos + "', '" 
			+ email+ "', '" + dni + "', '" + clave +  "');";
			
			System.out.println(cadenaSQL);
			sta.executeUpdate(cadenaSQL);
			sta.close();
			
			Statement st = con.createStatement();
			String select = "SELECT idCliente FROM Clientes where dni = '" + dni +"'";
			ResultSet rs = st.executeQuery(select);
			while(rs.next())
			{
				respuesta = rs.getInt("idCliente");
			}
			//System.out.println(respuesta);
			System.out.println("Alta realizado con éxito");
			st.close();
			
		} catch (SQLException ex) {
			System.out.println("ERROR:al hacer un Insert");
			ex.printStackTrace();
			respuesta = 1;
		}
		
	   return respuesta;
	}



	public static  String readCliente(int idCliente, String campo) {
		/* Devuelve el valor de la columna "campo" del cliente identificado por "idCliente" */
		Connection con = getConexion();
		String respuest="";
		try
		{
			Statement stm = con.createStatement();
			String sel = "SELECT "+ campo + " FROM Clientes WHERE idCliente ='"+ idCliente + "'";
			ResultSet rs = stm.executeQuery(sel);
			while(rs.next())
			{
				respuest = rs.getString(campo);
			}
			
			//System.out.println(respuest);
			
			
			
		}
		catch (SQLException ex) {
			System.out.println("ERROR:al hacer un SELECT");
			ex.printStackTrace();
			respuest = " ";
			
		}
	
		return respuest;
	}



	public static boolean updateCliente(int idCliente, String campo, String nuevoValor) {
	/* Actualiza el valor de la columna "campo" del cliente identificado por "idCliente". Devuelve true si se ha logrado actualizar. */
	Connection con = getConexion();
		
		try
		{
			Statement stm = con.createStatement();
			String actualizar = "UPDATE Clientes SET " + campo + " = '" + nuevoValor + "' WHERE idCliente = " + idCliente;
			
			stm.executeUpdate(actualizar);
			System.out.println(actualizar);
			stm.close();
			System.out.println("Actualización realizada con éxito");
	
		}
		catch (SQLException ex) {
			System.out.println("ERROR:al hacer un UPDATE");
			ex.printStackTrace();
			
			
		}
		return true;
	}
	public static boolean deleteCliente(int idCliente) {
		/* Elimina el cliente identificado por "idCliente". Devuelve true si se ha logrado eliminar. */
		Connection con = getConexion();
		boolean respuesta = false;
		try
		{
			Statement stm = con.createStatement();
			String actualizar = "DELETE FROM Clientes WHERE idCliente = " + idCliente;
			System.out.println(actualizar);
			stm.executeUpdate(actualizar);
			System.out.println(actualizar);
			stm.close();	
			respuesta = true;
			
	
		}
		catch (SQLException ex) {
			System.out.println("ERROR:al hacer un DELETE");
			ex.printStackTrace();
			respuesta = false;
			
			
		}
		return respuesta;
	}
	
	private void desconectar(Connection con) {
		// TODO Auto-generated method stub
		try
		{
			con.close();
		}
		catch(Exception e) {}
	}
	
	}


