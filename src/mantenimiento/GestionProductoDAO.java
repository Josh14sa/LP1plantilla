package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.Producto;
import entidad.Usuario;
import interfaces.ProductoInterfacesDAO;
import utils.MySQLConexion8;

public class GestionProductoDAO implements ProductoInterfacesDAO {

	@Override
	public int registrar(Producto prod) {
		// Declaración de variables
		int res = 0;
		Connection con = null;
		PreparedStatement pstm = null; // ejecutar la instrucción SQL
		try {
			// paso 1: Establecer la conexión con la BD
			con = MySQLConexion8.getConexion();
			// PASO 2: Establecer la instrucción SQL --Registrar
			String sql = "insert into tb_productos values (?,?,?,?,?,?);";
			// paso 3: crear el objeto pstm y enviar la variable sql
			pstm = con.prepareStatement(sql); // obtener comandos SQL
			// paso 4 : obtener los parametros para la instrucción SQL
			pstm.setString(1, prod.getIdProd());
			pstm.setString(2, prod.getDescripProducto());
			pstm.setInt(3, prod.getStock());
			pstm.setDouble(4, prod.getPrecio());
			pstm.setInt(5, prod.getIdTipo());
			pstm.setInt(6, prod.getEstado());

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
	public int actualizar(Producto prod) {
		// Declaración de variables
				int res = 0;
				Connection con = null;
				PreparedStatement pstm = null; // ejecutar la instrucción SQL
				try {
					// paso 1: Establecer la conexión con la BD
					con = MySQLConexion8.getConexion();
					// PASO 2: Establecer la instrucción SQL -- aCTUALIZAR 
					String sql = "update tb_productos set descripcion = ?, stock = ?,precio = ?, idtipo = ?,estado = ? where idprod = ?";
					// paso 3: crear el objeto pstm y enviar la variable sql
					pstm = con.prepareStatement(sql); // obtener comandos SQL
					// paso 4 : obtener los parametros para la instrucción SQL
					
					pstm.setString(1, prod.getDescripProducto());
					pstm.setInt(2, prod.getStock());
					pstm.setDouble(3, prod.getPrecio());
					pstm.setInt(4, prod.getIdTipo());
					pstm.setInt(5, prod.getEstado());
					pstm.setString(6, prod.getIdProd());

					// paso 5: ejecutar la instrucción SQL
					res = pstm.executeUpdate();

				} catch (Exception e) {
					System.out.println(">>>>>>>>>>>>>>>> Error en la instrucción SQL - actualizar " + e.getMessage());
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
	public int eliminar(String idProd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Producto> listarProductos() {
		ArrayList<Producto> lista = new ArrayList<Producto>();//NULL
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet res = null;
		Producto prod = null;
		try {
			//1. Conectarnos a la base de datos
			con = MySQLConexion8.getConexion(); 
			//2. Establecer la instrucción SQL -- consulta a la tabla tb_productos
			String sql = "select * from tb_productos;"; 
			//3. Enviar la nstrucción sql al objeto "pstm"
			pstm = con.prepareStatement(sql); 
			//4. parametros -->> no Hay
			
			//5. ejecutar la instrucción SQL
			res = pstm.executeQuery();
			
			//6. bucle para realizar el recorrido al objeto "res" 
			while(res.next()) {
                 //crear un objeto de tipo "Producto"
				prod = new Producto();
                 // setear(asignar los valores a los atributos privados)
				prod.setIdProd(res.getString(1));
				prod.setDescripProducto(res.getString(2));
				prod.setStock(res.getInt(3));
				prod.setPrecio(res.getDouble(4));
				prod.setIdTipo(res.getInt(5));
				prod.setEstado(res.getInt(6));
				
			//paso 7 : añadir el objeto "prod" a la lista
				lista.add(prod);
				
			}		
			
		} catch (Exception e) {
			System.out.println("Error en la instrucción SQL - LISTAR PRODUCTOS"+e.getMessage());
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
	public Producto buscarProducto(String idProd) {
		// TODO Auto-generated method stub
		return null;
	}

}
