package estructuraTP.modelo;

import java.time.LocalDate;

public class Movimiento {

	private int nroMovimiento;
	private int idCuenta;
	private String tipoMovimiento;
	private LocalDate fechaMovimiento;
	private float monto;
	private LocalDate fechaImputar;
	private float montoImputar;
	private String comentario;
	
	public Movimiento(int nroMovimiento, int idCuenta, String tipoMovimiento, LocalDate fechaMovimiento, float monto, LocalDate fechaImputar, float montoImputar, String comentario) {
		this.nroMovimiento = nroMovimiento;
		this.idCuenta = idCuenta;
		this.tipoMovimiento = tipoMovimiento;
		this.fechaMovimiento = fechaMovimiento;
		this.monto = monto;
		this.fechaImputar = fechaImputar;
		this.montoImputar = montoImputar;
		this.comentario = comentario;
	}

	public int getMovimiento() {
		return nroMovimiento;
	}

	public int getIdCuenta() {
		return idCuenta;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public LocalDate getFechaMovimiento() {
		return fechaMovimiento;
	}

	public float getMonto() {
		return monto;
	}

	public LocalDate getFechaImputar() {
		return fechaImputar;
	}

	public float getMontoImputar() {
		return montoImputar;
	}

	public String getComentario() {
		return comentario;
	}
	
}
