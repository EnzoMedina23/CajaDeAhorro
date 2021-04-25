package estructuraTP.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

import estructuraTP.modelo.PlazoFijo;

public class PlazoFijoDAO extends MySQL_Conn{

	public ArrayList<PlazoFijo> consultar() {
		Connection c = conexion();
		ArrayList<PlazoFijo> s = new ArrayList<PlazoFijo>();
		try {

			Statement stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT nro_pf, id_cuenta, tasa_interes, plazo, fecha_inicio, fecha_vto, monto_capital, monto_interes, monto_vto FROM plazosfijos");
			
			while(rs.next()) {
				
				int nroPf = rs.getInt("nro_pf");
				int idCuenta = rs.getInt("id_cuenta");
				float tasaIntereses = rs.getFloat("tasa_interes");
				int plazo = rs.getInt("plazo");
				Date fechaInicio = rs.getDate("fecha_inicio");
				Date fechaVto = rs.getDate("fecha_vto");
				float montoCapital = rs.getFloat("monto_capital");
				float montoInteres = rs.getFloat("monto_interes");
				float montoVto = rs.getFloat("monto_vto");

				PlazoFijo pf = new PlazoFijo(nroPf,idCuenta,tasaIntereses,plazo,fechaInicio.toLocalDate(), fechaVto.toLocalDate(), montoCapital, montoInteres, montoVto);
				s.add(pf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return s;
	}
	
	public float crearPlazoFijo(float aRetirar, int idCuentaa, int plazo) {
		Connection c = conexion();
		float tasa=(float) 5.75;
		if(plazo==30)
			tasa= (float) 5.75;
		if(plazo==90)
			tasa= (float) 17.25;
		float monto_interes=(aRetirar*tasa)/100;
		float monto_total=aRetirar+monto_interes;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("INSERT INTO plazosfijos(id_cuenta,tasa_interes,plazo, fecha_inicio, fecha_vto, monto_capital, monto_interes, monto_vto) VALUES (?,?,?,?,?,?,?,?)");
		
			preparedStatement.setInt(1, idCuentaa);
			
			preparedStatement.setFloat(2, tasa);

			preparedStatement.setInt(3, plazo);
			preparedStatement.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
			preparedStatement.setDate(5, java.sql.Date.valueOf(sumarDias(plazo)));
			preparedStatement.setFloat(6, aRetirar);
			preparedStatement.setFloat(7, monto_interes);
			preparedStatement.setFloat(8, monto_total);

		    
			int row = preparedStatement.executeUpdate();
			System.out.println(row);
			
			PreparedStatement ps;
			
			ps = c.prepareStatement("UPDATE cuenta SET saldo= saldo-? WHERE id_cuenta = ?");
			ps.setFloat(1, aRetirar);
			ps.setInt(2, idCuentaa);
			
			int rs = ps.executeUpdate();
			System.out.println(rs);
			
			preparedStatement = c.prepareStatement("INSERT INTO movimientos(id_cuenta, tipo_movimiento,fecha_movimiento,monto_movimiento, fecha_imputar, monto_imputar, sumar, comentario) VALUES (?,?,?,?,?,?,?,?)");
			
			preparedStatement.setInt(1, idCuentaa);
			
			preparedStatement.setString(2, "Crear Plazo Fijo");

			preparedStatement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
			preparedStatement.setFloat(4, aRetirar);
			preparedStatement.setDate(5, java.sql.Date.valueOf(sumarDias(plazo)));
			preparedStatement.setFloat(6, monto_total);
			preparedStatement.setBoolean(7, false);
			preparedStatement.setString(8, "Creacion de plazo fijo");

		    
			int row1 = preparedStatement.executeUpdate();
			System.out.println(row1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return monto_total;
		
	}
	
	public LocalDate sumarDias(int dias){
		
		 
		
	      Calendar calendar = Calendar.getInstance();
		
	      java.util.Date fecha= new java.util.Date ();
		calendar.setTime(fecha); // Configuramos la fecha que se recibe
		
	      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0

	 

	      return  calendar.getTime().toInstant()
	    	      .atZone(ZoneId.systemDefault())
	    	      .toLocalDate(); // Devuelve el objeto Date con los nuevos días añadidos
		
	 
		
	 }
	
}
