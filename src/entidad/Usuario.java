package entidad;

public class Usuario {
	//atributos privados
	private int codigo;
	private String nombre;
	private String apellido;
	private String usuario;
	private String clave;
	private String fechNacimiento;
	private int tipo;
	private int estado;
	
	//constructores --> 2
	public Usuario() {
		
	}

	public Usuario(int codigo, String nombre, String apellido, String usuario, String clave, String fechNacimiento,
			int tipo, int estado) {
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.usuario = usuario;
		this.clave = clave;
		this.fechNacimiento = fechNacimiento;
		this.tipo = tipo;
		this.estado = estado;
	}
	
	//metodos de acceso GET/SET

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getFechNacimiento() {
		return fechNacimiento;
	}

	public void setFechNacimiento(String fechNacimiento) {
		this.fechNacimiento = fechNacimiento;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	


	
	

}
