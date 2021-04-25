package estructuraTP.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import estructuraTP.modelo.Usuario;

public class UsuarioDAO extends MySQL_Conn{

	
	public Usuario datosCuenta(int dni) {
		Connection c = conexion();
		Usuario in = new Usuario();
		try {
			PreparedStatement preparedStatement;
			
			preparedStatement = c.prepareStatement("SELECT contraseña, tipo_de_usuario FROM usuario WHERE dni = ?");
			
			preparedStatement.setInt(1, dni);
			
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				int contraseña = rs.getInt("contraseña");
				String tipoUsuario = rs.getString("tipo_de_usuario");
				
				in.setTipoUsuario(tipoUsuario);
				in.setContraseña(contraseña);
				in.setdni(dni);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return in;
	}
	
	public void guardar(Usuario in) {
		Connection c = conexion();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("INSERT INTO usuario (nombre, apellido, dni, contraseña, domicilio, telefono, tipo_de_usuario) VALUES (?,?,?,?,?,?,?)");
		
			preparedStatement.setString(1, in.getNombre());
			preparedStatement.setString(2, in.getApellido());
			preparedStatement.setInt(3, in.getDni());
			preparedStatement.setInt(4, in.getContraseña());
			//Date date = java.sql.Date.valueOf(localDate);  pasa de LocalDate a Date
			preparedStatement.setString(5, in.getDomicilio());
			preparedStatement.setInt(6, in.getTelefono());
			preparedStatement.setString(7, in.getTipoUsuario());

			int row = preparedStatement.executeUpdate();
			
			preparedStatement = c.prepareStatement("INSERT INTO cuenta (id_cuenta, dni, saldo) VALUES (?,?,?)");

			
			preparedStatement.setInt(1, in.getIdCuenta());
			preparedStatement.setInt(2, in.getDni());
			preparedStatement.setFloat(3, in.getSaldo());
			
			 row = preparedStatement.executeUpdate();
			System.out.println(row);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
	}
	
	public ArrayList<Usuario> consultar() {
		Connection c = conexion();
		ArrayList<Usuario> s = new ArrayList<Usuario>();
		try {

			Statement stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT u.dni, u.nombre, u.apellido, u.contraseña, u.domicilio, u.telefono, u.tipo_de_usuario, c.id_cuenta, c.saldo FROM usuario AS u INNER JOIN cuenta AS c ON u.dni = c.dni");
			
			while(rs.next()) {
				
				int dni = rs.getInt("u.dni");
				String nombre = rs.getString("u.nombre");
				String apellido = rs.getString("u.apellido");
				int contraseña = rs.getInt("u.contraseña");
				String domicilio = rs.getString("u.domicilio");
				int telefono = rs.getInt("u.telefono");
				String tipoUsuario = rs.getString("u.tipo_de_usuario");
				int idCuenta = rs.getInt("c.id_cuenta");
				float saldo = rs.getFloat("c.saldo");
				
				Usuario us = new Usuario(nombre, apellido, dni, contraseña, domicilio, telefono, tipoUsuario, idCuenta, saldo);
				s.add(us);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
		return s;
	}

	public void borrar(int valor) {
		Connection c = conexion();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("DELETE FROM usuario WHERE dni = ?");
			
			preparedStatement.setInt(1, valor);

			preparedStatement.executeUpdate();
			
			preparedStatement = c.prepareStatement("DELETE FROM cuenta WHERE dni = ?");
			
			preparedStatement.setInt(1, valor);

			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
	}
	
	public void modificar(Usuario u, Integer valor, Integer valor1) {
		Connection c = conexion();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = c.prepareStatement("UPDATE usuario SET nombre = ?, apellido = ?, contraseña = ?, domicilio = ?, telefono = ?, tipo_de_usuario = ? WHERE dni = ?");
		
			preparedStatement.setString(1, u.getNombre());
			preparedStatement.setString(2, u.getApellido());
			preparedStatement.setInt(3, u.getContraseña());
			preparedStatement.setString(4, u.getDomicilio());
			preparedStatement.setInt(5, u.getTelefono());
			preparedStatement.setString(6, u.getTipoUsuario());
			preparedStatement.setInt(7, valor);
			int row = preparedStatement.executeUpdate();
			preparedStatement = c.prepareStatement("UPDATE cuenta SET saldo = ? WHERE id_cuenta = ?");


			preparedStatement.setFloat(1, u.getSaldo());
			preparedStatement.setInt(2, valor1);
			
			
			int row1 = preparedStatement.executeUpdate();
			System.out.println(row);
			System.out.println(row1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			desconexion();
		}
	}
	
}
