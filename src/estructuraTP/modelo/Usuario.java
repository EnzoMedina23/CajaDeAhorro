package estructuraTP.modelo;

public class Usuario {

	private String nombre;
	private String apellido;
	private int dni;
	private Integer contrase�a;
	private String domicilio;
	private Integer telefono;
	private String tipoUsuario;
	private int idCuenta;
	private float saldo;
	
	public Usuario( String nombre, String apellido, int dni, int contrase�a, String domicilio, int telefono, String tipoUsuario) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contrase�a = contrase�a;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.tipoUsuario = tipoUsuario;
	}
	
	public Usuario(String nombre, String apellido, int dni, int contrase�a, String domicilio, int telefono, String tipoUsuario,float saldo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contrase�a = contrase�a;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.tipoUsuario = tipoUsuario;
		this.saldo = saldo;
	}
	
	public Usuario(String nombre, String apellido, int dni, int contrase�a, String domicilio, int telefono, String tipoUsuario, int idCuenta, float saldo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.contrase�a = contrase�a;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.tipoUsuario = tipoUsuario;
		this.idCuenta = idCuenta;
		this.saldo = saldo;
	}

	public Usuario(String nombre, String apellido, int contrase�a, String domicilio, int telefono, String tipoUsuario, int idCuenta, float saldo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrase�a = contrase�a;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.tipoUsuario = tipoUsuario;
		this.idCuenta = idCuenta;
		this.saldo = saldo;
	}
	
	public Usuario() {
	}

	public Usuario(String apellido2, String nombre2, int contrase�a2, String domicilio2, int telefono2,
			String tipoUsuario2) {
		apellido = apellido2;
		nombre = nombre2;
		contrase�a = contrase�a2;
		domicilio = domicilio2;
		telefono = telefono2;
		tipoUsuario = tipoUsuario2;
	}

	public void setContrase�a(int contrase�a) {
		this.contrase�a = contrase�a;
	}

	public Integer getContrase�a() {
		return contrase�a;
	}

	public String getNombre() {
		return nombre;
	}

	public int getDni() {
		return dni;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public int getIdCuenta() {
		return idCuenta;
	}
	
	public float getSaldo() {
		return saldo;
	}

	public void setdni(int dni) {
		this.dni = dni;
	}
}
