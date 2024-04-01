package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Usuario;
import interfaces.UsuarioInterfacesDAO;
import utils.MySQLConexion8;

public class GestionUsuarioDAO implements UsuarioInterfacesDAO {
	// lógica de negocio

	@Override
	public int registrar(Usuario u) {
		// Declaración de variables
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null; // ejecutar la instrucción SQL
		try {
			// paso 1: Establecer la conexión con la BD
			con = MySQLConexion8.getConexion();
			// PASO 2: Establecer la instrucción SQL --Registrar
			// insert into tb_usuarios values
			// (null,"Sofía","Alva","U004","12345","2023-02-14",default,default);
			//
			String sql = "insert into tb_usuarios values (null,?,?,?,?,?,default,default);";
			// paso 3: crear el objeto pstm y enviar la variable sql
			pstm = con.prepareStatement(sql); // obtener comandos SQL
			// paso 4 : obtener los parametros para la instrucción SQL
			pstm.setString(1, u.getNombre());
			pstm.setString(2, u.getApellido());
			pstm.setString(3, u.getUsuario());
			pstm.setString(4, u.getClave());
			pstm.setString(5, u.getFechNacimiento());

			// paso 5: ejecutar la instrucción SQL
			res = pstm.executeUpdate();

		} catch (Exception e) {
			System.out.println(">>>>>>>>>>>>>>>> Error en la instrucción SQL - registrar " + e.getMessage());
		} finally {
			try {

				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();

			} catch (SQLException e2) {
				System.out.println("<<<<<< Error al cerrar la base de datos " + e2.getMessage());
			}
		}

		return res;
	}

	@Override
	public int eliminar(int codigo) {
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			// paso 1 --> Conexión a la base datos
			con = MySQLConexion8.getConexion();
			// paso 2 --> establecer la instrucción SQL - ELIMINAR
			String sql = "delete from tb_usuarios where codigo = ?";
			// paso 3 -- > enviar la instrucción al objeto pstm --> obtener los comandos SQL
			pstm = con.prepareStatement(sql);
			// paso 4 --> obtener el parámetro
			pstm.setInt(1, codigo);
			// paso 5 --> Ejecutar la instrucción SQL
			res = pstm.executeUpdate();

		} catch (Exception e) {
			System.out.println("Error en la instrucción SQL - eliminar" + e.getMessage());
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();

			} catch (SQLException e2) {
				System.out.println("Error al cerrar la base de datos" + e2.getMessage());
			}
		}
		return res;
	}

	@Override
	public int actualizar(Usuario u) {
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null;
		try {
			// paso 1 --> conexion a la base de datos
			con = MySQLConexion8.getConexion();
			
			// paso 2 --->establecer la instruccion SQL
			String sql = "update tb_usuarios set nombre = ?,apellido = ?,usuario = ?,clave = ?,fnacim =? where codigo = ?";

			// paso 3 --> enviar la instruccion al objeto pstm --> para obtener los comandos
			pstm = con.prepareStatement(sql);

			// paso 4 --> obtener los parametro el 
			pstm.setString(1, u.getNombre());
			pstm.setString(2, u.getApellido());
			pstm.setString(3, u.getUsuario());
			pstm.setString(4, u.getClave());
			pstm.setString(5, u.getFechNacimiento());
			pstm.setInt(6,u.getCodigo());

			// Paso 5 --> Ejecutar la instruccion SQL
			res = pstm.executeUpdate();
	
		} catch (Exception e) {
			System.out.println("Error en la instrucción SQL-Actualizar" + e.getMessage());
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (con != null)
					con.close();

			} catch (SQLException e2) {
				System.out.println("Error al cerrar la base de datos " + e2.getMessage());
			}
		}
		return res;
	}

	@Override
	public Usuario buscarUsuario(int codigo) {
		
		Usuario user = null;
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			//paso 1 --> conectar a la BD
			con = MySQLConexion8.getConexion();
			//PASO 2 --> Establecer la instrucción SQL - cONSULTA DE USUARIO POR CODIGO
		    String sql = "select * from tb_usuarios where codigo = ?;";
		    //PASO 3 --> Enviar la instruccion SQL al objeto pstm 
		    pstm = con.prepareStatement(sql);
		    //paso 4 --> paramétros
		    pstm.setInt(1, codigo);
		    //paso 5 --> ejecutar la consulta
		    res = pstm.executeQuery();
		    //paso 6 --> Setear lo valores
		    if(res.next()) {
		    	user = new Usuario(res.getInt(1),
		    			           res.getString(2), 
		    			           res.getString(3), 
		    			           res.getString(4), 
		    			           res.getString(5), 
		    			           res.getString(6), 
		    			           res.getInt(7),
		    			           res.getInt(8));	
		    }    
				
		} catch (Exception e) {
			System.out.println("Error en la consulta de búsqueda"+e.getMessage());
		}finally {
			try {
				if(pstm!= null)pstm.close();
				if(res != null)res.close();
				if(con != null )con.close();
				
			} catch (SQLException e2) {
				System.out.println("Error al cerrar la base de datos");
			}
		}	
		return user;
	}

	@Override
	public ArrayList<Usuario> listarUsuarios() {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();//NULL
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		Usuario user = null;
		try {
			//1. Conectarnos a la base de datos
			con = MySQLConexion8.getConexion(); 
			//2. Establecer la instrucción SQL -- consulta a la tabla tb_usuarios
			String sql = "select * from tb_usuarios;"; 
			//3. Enviar la nstrucción sql al objeto "pstm"
			pstm = con.prepareStatement(sql); 
			//4. parametros -->> no Hay
			
			//5. ejecutar la instrucción SQL
			res = pstm.executeQuery();
			
			//6. bucle para realizar el recorrido al objeto "res" 
			while(res.next()) {
                 //crear un objeto de tipo "Usuario"
				user = new Usuario();
                 // setear(asignar los valores a los atributos privados)
				user.setCodigo(res.getInt(1));
				user.setNombre(res.getString(2));
				user.setApellido(res.getString(3));
				user.setUsuario(res.getString(4));
				user.setClave(res.getString(5));
				user.setFechNacimiento(res.getString(6));
				user.setTipo(res.getInt(7));
				user.setEstado(res.getInt(8));
				
			//paso 7 : añadir el objeto "user" a la lista
				lista.add(user);
				
			}		
			
		} catch (Exception e) {
			System.out.println("Error en la instrucción SQL - LISTAR USUARIOS"+e.getMessage());
		}finally {
			try {
				if(pstm != null) pstm.close();
				if(res != null)res.close();
				if(con != null)con.close();
				
			} catch (SQLException e2) {
				System.out.println("Error al cerrar la base de datos "+e2.getMessage());
			}
		}
				
		return lista;
	}

	@Override
	public ArrayList<Usuario> listarUsuariosXTipo(int tipoUser) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();//NULL
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		Usuario user = null;
		try {
			//1. Conectarnos a la base de datos
			con = MySQLConexion8.getConexion(); 
			//2. Establecer la instrucción SQL -- consulta a la tabla tb_usuarios
			String sql = "select * from tb_usuarios where tipo = ?;"; 
			//3. Enviar la nstrucción sql al objeto "pstm"
			pstm = con.prepareStatement(sql); 
			//4. parametros -->> 
			pstm.setInt(1, tipoUser);
			//5. ejecutar la instrucción SQL
			res = pstm.executeQuery();
			
			//6. bucle para realizar el recorrido al objeto "res" 
			while(res.next()) {
                 //crear un objeto de tipo "Usuario"
				user = new Usuario();
                 // setear(asignar los valores a los atributos privados)
				user.setCodigo(res.getInt(1));
				user.setNombre(res.getString(2));
				user.setApellido(res.getString(3));
				user.setUsuario(res.getString(4));
				user.setClave(res.getString(5));
				user.setFechNacimiento(res.getString(6));
				user.setTipo(res.getInt(7));
				user.setEstado(res.getInt(8));
				
			//paso 7 : añadir el objeto "user" a la lista
				lista.add(user);
				
			}		
			
		} catch (Exception e) {
			System.out.println("Error en la instrucción SQL - LISTAR USUARIOS"+e.getMessage());
		}finally {
			try {
				if(pstm != null) pstm.close();
				if(res != null)res.close();
				if(con != null)con.close();
				
			} catch (SQLException e2) {
				System.out.println("Error al cerrar la base de datos "+e2.getMessage());
			}
		}
				
		return lista;
	}

}
