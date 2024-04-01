package entidad;

public class Producto {
	//atributos privados
	private String idProd;
	private String descripProducto;
	private int stock;
	private double precio;
	private int idTipo;
	private int estado;
	
	//constructores --> 2
	public Producto() {
		
	}
	public Producto(String idProd, String descripProducto, int stock, double precio, int idTipo, int estado) {
		super();
		this.idProd = idProd;
		this.descripProducto = descripProducto;
		this.stock = stock;
		this.precio = precio;
		this.idTipo = idTipo;
		this.estado = estado;
	}
	
	//métodos de acceso get/set
	public String getIdProd() {
		return idProd;
	}
	public void setIdProd(String idProd) {
		this.idProd = idProd;
	}
	public String getDescripProducto() {
		return descripProducto;
	}
	public void setDescripProducto(String descripProducto) {
		this.descripProducto = descripProducto;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	
	
	
	

}
