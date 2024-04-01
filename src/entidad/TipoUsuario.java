package entidad;

public class TipoUsuario {
	//atributos privados
	private int idTipo;
	private String descripTipo;
	
	//constructores
	public TipoUsuario() {
		
	}

	public TipoUsuario(int idTipo, String descripTipo) {
		super();
		this.idTipo = idTipo;
		this.descripTipo = descripTipo;
	}
	
	//métodos de acceso GET/SET

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getDescripTipo() {
		return descripTipo;
	}

	public void setDescripTipo(String descripTipo) {
		this.descripTipo = descripTipo;
	}
	
	
	
	
	
	
	

}
