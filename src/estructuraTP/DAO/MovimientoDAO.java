package estructuraTP.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import estructuraTP.modelo.Movimiento;

public class MovimientoDAO extends MySQL_Conn{

	public ArrayList<Movimiento> consultar() {
		Connection c = conexion();
		ArrayList<Movimiento> s = new ArrayList<Movimiento>();
		try {

			Statement stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT nro_movimiento, id_cuenta, tipo_movimiento, fecha_movimiento, monto_movimiento, fecha_imputar, monto_imputar, comentario FROM movimientos");
			
			while(rs.next()) {
				
				int nroMovimiento = rs.getInt("nro_movimiento");
				int idCuenta = rs.getInt("id_cuenta");
				String tipoMovimiento = rs.getString("tipo_movimiento");
				LocalDate fechaMovimiento = rs.getDate("fecha_movimiento").toLocalDate();
				float monto = rs.getFloat("monto_movimiento");
				LocalDate fechaImputar = rs.getDate("fecha_imputar").toLocalDate();
				float montoImputar = rs.getFloat("monto_imputar");
				String comentario = rs.getString("comentario");
				
				Movimiento pf = new Movimiento(nroMovimiento,idCuenta,tipoMovimiento,fechaMovimiento,monto,fechaImputar,montoImputar,comentario);
				s.add(pf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return s;
	}

	public ArrayList<Movimiento> consultarConIdCuenta(int idCuentaa) {
		Connection c = conexion();
		ArrayList<Movimiento> s = new ArrayList<Movimiento>();
		try {

			PreparedStatement ps;
			
			ps = c.prepareStatement("SELECT nro_movimiento, id_cuenta, tipo_movimiento, fecha_movimiento, monto_movimiento, fecha_imputar, monto_imputar, comentario FROM movimientos WHERE id_cuenta = ?");
			
			ps.setInt(1, idCuentaa);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int nroMovimiento = rs.getInt("nro_movimiento");
				int idCuenta = rs.getInt("id_cuenta");
				String tipoMovimiento = rs.getString("tipo_movimiento");
				LocalDate fechaMovimiento = rs.getDate("fecha_movimiento").toLocalDate();
				float monto = rs.getFloat("monto_movimiento");
				LocalDate fechaImputar = rs.getDate("fecha_imputar").toLocalDate();
				float montoImputar = rs.getFloat("monto_imputar");
				String comentario = rs.getString("comentario");
				
				Movimiento pf = new Movimiento(nroMovimiento,idCuenta,tipoMovimiento,fechaMovimiento,monto,fechaImputar,montoImputar,comentario);
				s.add(pf);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return s;
	}
	
	public ArrayList<Movimiento> acreditar(int idCuenta, int plazo, float montoCapital, float montoVto) {
		Connection c = conexion();
		ArrayList<Movimiento> s = new ArrayList<Movimiento>();
		try {

			PreparedStatement preparedStatement;
			
			preparedStatement = c.prepareStatement("INSERT INTO movimientos(id_cuenta, tipo_movimiento,fecha_movimiento,monto_movimiento, fecha_imputar, monto_imputar, sumar, comentario) VALUES (?,?,?,?,?,?,?,?)");
			
			preparedStatement.setInt(1, idCuenta);
			
			preparedStatement.setString(2, "Acreditación plazo fijo");

			preparedStatement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
			preparedStatement.setFloat(4, montoCapital);
			preparedStatement.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
			preparedStatement.setFloat(6, montoVto);
			preparedStatement.setBoolean(7, true);
			preparedStatement.setString(8, "Acreditación de un plazo fijo");

		    
			int row1 = preparedStatement.executeUpdate();
			System.out.print(row1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return s;
	}
	
	public void borrar(int idCuenta, float montoCapital, float montoVto) {
		Connection c = conexion();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("DELETE FROM plazosfijos WHERE id_cuenta = ? AND monto_capital = ? AND monto_vto = ?");
			
			preparedStatement.setInt(1, idCuenta);
			preparedStatement.setFloat(2, montoCapital);
			preparedStatement.setFloat(3, montoVto);


			int row = preparedStatement.executeUpdate();
			System.out.println(row);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
	}
}
