package estructuraTP.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import estructuraTP.modelo.Servicio;


public class CuentaDAO extends MySQL_Conn{

	public int consultarIdCuenta(int idUsuario) {
		Connection c = conexion();
		int idCuenta = 0;
		try {

			PreparedStatement ps;
			
			ps = c.prepareStatement("SELECT id_cuenta FROM cuenta WHERE dni = ?");
			
			ps.setInt(1, idUsuario);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				idCuenta = rs.getInt("id_cuenta");
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return idCuenta;
	}
	
	public int acreditar(int idCuenta, float montoVto) {
		Connection c = conexion();
		try {

				PreparedStatement ps1;
				

			
			
			ps1 = c.prepareStatement("UPDATE cuenta SET saldo = saldo+? WHERE id_cuenta = ?");
			
			ps1.setFloat(1, montoVto);
			ps1.setInt(2, idCuenta);
			
			int row1 = ps1.executeUpdate();
			System.out.print(row1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return idCuenta;
	}
	
	public float consultarSaldo(int idUsuario) {
		Connection c = conexion();
		float idCuenta = 0;
		try {

			PreparedStatement ps;
			
			ps = c.prepareStatement("SELECT saldo FROM cuenta WHERE dni = ?");
			
			ps.setInt(1, idUsuario);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				idCuenta = rs.getFloat("saldo");
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return idCuenta;
	}
	
	public float consultarSaldoCuenta(int idUsuario) {
		Connection c = conexion();
		float idCuenta = 0;
		try {

			PreparedStatement ps;
			
			ps = c.prepareStatement("SELECT saldo FROM cuenta WHERE id_cuenta = ?");
			
			ps.setInt(1, idUsuario);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				idCuenta = rs.getFloat("saldo");
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return idCuenta;
	}
	
	public void retirarDinero(float aRetirar, int id, int idCuentaa) {
		Connection c = conexion();
		try {

			PreparedStatement ps;
			
			ps = c.prepareStatement("UPDATE cuenta SET saldo= saldo-? WHERE dni = ?");
			ps.setFloat(1, aRetirar);
			ps.setInt(2, id);
			
			int rs = ps.executeUpdate();
			System.out.println(rs);
			
			PreparedStatement preparedStatement;
			
			preparedStatement = c.prepareStatement("INSERT INTO movimientos(id_cuenta, tipo_movimiento,fecha_movimiento,monto_movimiento, fecha_imputar, monto_imputar, sumar, comentario) VALUES (?,?,?,?,?,?,?,?)");
			
			preparedStatement.setInt(1, idCuentaa);
			
			preparedStatement.setString(2, "Extracción");

			preparedStatement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
			preparedStatement.setFloat(4, aRetirar);
			preparedStatement.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
			preparedStatement.setFloat(6, aRetirar);
			preparedStatement.setBoolean(7, false);
			preparedStatement.setString(8, "Extracción de dinero de la cuenta");

		    
			int row1 = preparedStatement.executeUpdate();
			System.out.println(row1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		
	}
	
	public void depositarDinero(float aDepositar, int id, int idCuentaa) {
		Connection c = conexion();
		try {

			PreparedStatement ps;
			
			ps = c.prepareStatement("UPDATE cuenta SET saldo= saldo+? WHERE dni = ?");
			ps.setFloat(1, aDepositar);
			ps.setInt(2, id);
			
			int rs = ps.executeUpdate();
			System.out.println(rs);
			
			PreparedStatement preparedStatement;
			
			preparedStatement = c.prepareStatement("INSERT INTO movimientos(id_cuenta, tipo_movimiento,fecha_movimiento,monto_movimiento, fecha_imputar, monto_imputar, sumar, comentario) VALUES (?,?,?,?,?,?,?,?)");
			
			preparedStatement.setInt(1, idCuentaa);
			
			preparedStatement.setString(2, "Depósito");

			preparedStatement.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
			preparedStatement.setFloat(4, aDepositar);
			preparedStatement.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
			preparedStatement.setFloat(6, aDepositar);
			preparedStatement.setBoolean(7, false);
			preparedStatement.setString(8, "Depósito de dinero en cuenta");

		    
			int row1 = preparedStatement.executeUpdate();
			System.out.println(row1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		
	}
	
	public void pagarServices(int idCuentaa, Servicio c2) {
		Connection c = conexion();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("INSERT INTO servicios(id_cuenta,nro_cliente, nombre_servicio, fecha, importe) VALUES (?,?,?,?,?)");
		
			preparedStatement.setInt(1, idCuentaa);
			
			preparedStatement.setInt(2, c2.getCliente());

			preparedStatement.setString(3, c2.getNombre());
			preparedStatement.setDate(4, Date.valueOf(c2.getFecha()));
			preparedStatement.setFloat(5, c2.getImporte());


		    
			int row = preparedStatement.executeUpdate();
			System.out.println(row);
			
			PreparedStatement ps;
			
			ps = c.prepareStatement("UPDATE cuenta SET saldo= saldo-? WHERE id_cuenta = ?");
			ps.setFloat(1, c2.getImporte());
			ps.setInt(2, idCuentaa);
			
			int rs = ps.executeUpdate();
			System.out.println(rs);
			
			preparedStatement = c.prepareStatement("INSERT INTO movimientos(id_cuenta, tipo_movimiento,fecha_movimiento,monto_movimiento, fecha_imputar, monto_imputar, sumar, comentario) VALUES (?,?,?,?,?,?,?,?)");
			
			preparedStatement.setInt(1, idCuentaa);
			
			preparedStatement.setString(2, "Servicios");

			preparedStatement.setDate(3, java.sql.Date.valueOf(c2.getFecha()));
			preparedStatement.setFloat(4, c2.getImporte());
			preparedStatement.setDate(5, java.sql.Date.valueOf(c2.getFecha()));
			preparedStatement.setFloat(6, c2.getImporte());
			preparedStatement.setBoolean(7, false);
			preparedStatement.setString(8, "Pago del servicio" + c2.getNombre());

		    
			int row1 = preparedStatement.executeUpdate();
			System.out.println(row1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		
	}
	
	
}
