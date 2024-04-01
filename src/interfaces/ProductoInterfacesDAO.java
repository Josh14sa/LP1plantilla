package interfaces;

import java.util.ArrayList;

import entidad.Producto;

public interface ProductoInterfacesDAO {
	//registrar productos
	public int registrar(Producto prod);
	//actualizar productos
	public int actualizar(Producto prod);
	//eliminar
	public int eliminar(String idProd);
	
	//listar productos
	public ArrayList<Producto> listarProductos();
	
	//buscar producto por codigo
	public Producto buscarProducto(String idProd);

}
