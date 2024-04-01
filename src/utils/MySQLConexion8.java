package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConexion8 {
	public static Connection getConexion() {
		//Declarando un objeto de tipo "Conection"
		Connection con = null;
		try {
			///establecer la ruta del driver de conexión -- > nombre paquete
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Datos para la conexión a la bd
            // 			 driver:protocoloDriver/ubicación/nombreBD/datos de actualización 
			String url = "jdbc:mysql://localhost:3306/ciberfarma?serverTimezone=UTC";
			String usr = "root";
			String psw = "mysql";//contraseña que se ingresa en el programa MySQL
			con = DriverManager.getConnection(url, usr, psw);
		} catch (ClassNotFoundException e) {
			System.out.println("Error >> Driver no Instalado!!" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error >> de conexi�n con la BD" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error >> general : " + e.getMessage());
		} 
		return con;
	}

}
