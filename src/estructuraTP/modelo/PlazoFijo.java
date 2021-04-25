package estructuraTP.modelo;

import java.time.LocalDate;

public class PlazoFijo{

	private int nroPf;
	private int idCuenta;
	private float tasaInteres;
	private int plazo;
	private LocalDate fechaInicio;
	private LocalDate fechaVto;
	private float montoCapital;
	private float montoInteres;
	private float montoVto;
	
	public PlazoFijo (int nroPf, int idCuenta, float tasaInteres, int plazo, LocalDate fechaInicio, LocalDate fechaVto, float montoCapital, float montoInteres, float montoVto) {
		this.nroPf = nroPf;
		this.idCuenta = idCuenta;
		this.tasaInteres = tasaInteres;
		this.plazo = plazo;
		this.fechaInicio = fechaInicio;
		this.fechaVto = fechaVto;
		this.montoCapital = montoCapital;
		this.montoInteres = montoInteres;
		this.montoVto = montoVto;
	}

	public int getNropf() {
		return nroPf;
	}

	public int getIdCuenta() {
		return idCuenta;
	}

	public float getTasaInteres() {
		return tasaInteres;
	}

	public int getPlazo() {
		return plazo;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public LocalDate getFechaVto() {
		return fechaVto;
	}

	public float getMontoCapital() {
		return montoCapital;
	}

	public float getMontoVto() {
		return montoVto;
	}

	public float getMontoInteres() {
		return montoInteres;
	}
	
}
