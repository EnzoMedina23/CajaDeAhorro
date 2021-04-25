package estructuraTP.modelo;

import java.time.LocalDate;

public class Servicio{
		int id_cuenta;
		int nro_cliente;
		String nombre_servicio;
		LocalDate fecha;
		float importe;
		
		public Servicio(int id_cuenta, int nro_cliente, String nombre_servicio, LocalDate fecha, float importe)
		{
			
			this.id_cuenta= id_cuenta;
			this.nro_cliente= nro_cliente;
			this.nombre_servicio= nombre_servicio;
			this.fecha= fecha;
			this.importe= importe;
			
			
		}

		public int getCliente() {
			// TODO Auto-generated method stub
			return nro_cliente;
		}

		public String getNombre() {
			// TODO Auto-generated method stub
			return nombre_servicio;
		}
		public LocalDate getFecha() {
			// TODO Auto-generated method stub
			return fecha;
		}
		
		public float getImporte() {
			return importe;
		}
		

}