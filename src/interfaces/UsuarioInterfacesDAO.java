package interfaces;

import java.util.ArrayList;

import entidad.Usuario;

public interface UsuarioInterfacesDAO {
	// Se define los procesos --> métodos -- > compromiso para el programador
	
	//registrar usuario
	public int registrar(Usuario u);
	//eliminar usuario
	public int eliminar(int codigo);
	//actualizar usuario
	public int actualizar(Usuario u);
	//buscar Usuario por codigo 
	public Usuario buscarUsuario(int codigo);
	//listar a los usuarios 
	public ArrayList<Usuario> listarUsuarios();
	//Listar Usuarios por tipo 
	public ArrayList<Usuario> listarUsuariosXTipo(int tipoUser);

}
