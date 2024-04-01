package mantenimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidad.TipoUsuario;
import interfaces.TipoUsuarioInterfaceDAO;
import utils.MySQLConexion8;

public class GestionTipoUsuarioDAO implements TipoUsuarioInterfaceDAO{

	@Override
	public ArrayList<TipoUsuario> listarTipoUsuario() {
		//Declaracion de variables
		ArrayList<TipoUsuario> lista = new ArrayList<TipoUsuario>();
		Connection con = null;
        PreparedStatement pstm = null;
        ResultSet res = null;
        TipoUsuario tipUser = null;
		//plantilla
		try {
			//1.
			con = MySQLConexion8.getConexion();
			//2.
			String sql ="SELECT * FROM tb_tipos;"; 
			//3
			pstm = con.prepareStatement(sql);
			//4. no
			//5.
			res = pstm.executeQuery();
			//6. 
			while(res.next()) {
				tipUser = new TipoUsuario();
				//setear
				tipUser.setIdTipo(res.getInt(1));
				tipUser.setDescripTipo(res.getString(2));
				// agregar a la lista
				lista.add(tipUser);
			}	
		} catch (Exception e) {
			System.out.println("Error en la instrucción SQL - LISTAR TIPOS"+e.getMessage());
		}finally {
			try {
				if(pstm != null)pstm.close();
				if(res != null)res.close();
				if(con != null)con.close();
			} catch (SQLException e2) {
				System.out.println("Error al cerrar la base de datos "+e2.getMessage());
			}
		}
		
		return lista ;
	}

}
