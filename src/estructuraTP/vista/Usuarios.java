package estructuraTP.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.DAO.PlazoFijoDAO;
import estructuraTP.DAO.UsuarioDAO;
import estructuraTP.modelo.PlazoFijo;
import estructuraTP.modelo.Usuario;
import javax.swing.JButton;

public class Usuarios extends JPanel {

	public Usuarios(JFrame frame, boolean consultar, boolean tablaFiltrada,ArrayList<Usuario> consulta) {
		setLayout(null);
		
		String[] nombresColumnas = { "DNI", "Nombre", "Apellido", "Contraseña", "Domicilio", "Teléfono", "Tipo de usuario", "Número cuenta", "Saldo" }; 
		
		DefaultTableModel modelo = new DefaultTableModel(nombresColumnas,0);
		
		UsuarioDAO udao = new UsuarioDAO();
		ArrayList<Usuario> resultado = new ArrayList<Usuario>();
		if(tablaFiltrada) {
			resultado = consulta;
		}
		else {
			resultado = udao.consultar();						
		}
		
		for ( Usuario u : resultado)
		{
	
		int dni = u.getDni();
		String nombre = u.getNombre();
		String apellido = u.getApellido();
		int contraseña = u.getContraseña();
		String domicilio = u.getDomicilio();
		int telefono = u.getTelefono();
		String tipoUsuario = u.getTipoUsuario();
		int idCuenta = u.getIdCuenta();
		float saldo = u.getSaldo();
			
		Object [] registro = {dni,nombre,apellido,contraseña,domicilio,telefono,tipoUsuario,idCuenta,saldo};
		
		modelo.addRow(registro);		
	       
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 23, 625, 235);
		add(scrollPane);
		
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(540, 365, 89, 23);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(270, 365, 89, 23);
		add(btnEliminar);
		
		JButton btnAtrs = new JButton("Atr\u00E1s");
		btnAtrs.setBounds(5, 365, 89, 23);
		add(btnAtrs);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(540, 365, 89, 23);
		add(btnAceptar);
		
		JLabel lblConsultarPor = new JLabel("Consultar por:");
		lblConsultarPor.setBounds(148, 269, 89, 14);
		add(lblConsultarPor);
		
		JRadioButton rdbtnNmeroDeCuenta = new JRadioButton("N\u00FAmero de cuenta");
		rdbtnNmeroDeCuenta.setBounds(243, 265, 173, 23);
		add(rdbtnNmeroDeCuenta);
		
		JRadioButton rdbtnDni = new JRadioButton("DNI");
		rdbtnDni.setBounds(243, 291, 109, 23);
		add(rdbtnDni);
		
		JRadioButton rdbtnApellido = new JRadioButton("Apellido");
		rdbtnApellido.setBounds(243, 369, 109, 23);
		add(rdbtnApellido);
		
		JRadioButton rdbtnNombre = new JRadioButton("Nombre");
		rdbtnNombre.setBounds(243, 343, 109, 23);
		add(rdbtnNombre);
		
		JRadioButton rdbtnTipoUsuario = new JRadioButton("Tipo de usuario");
		rdbtnTipoUsuario.setBounds(243, 317, 159, 23);
		add(rdbtnTipoUsuario);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(288, 11, 46, 14);
		add(lblUsuarios);
		
		if(consultar) {
			btnModificar.setVisible(false);
			btnEliminar.setVisible(false);
		} else {
			btnAceptar.setVisible(false);
			lblConsultarPor.setVisible(false);
			rdbtnNmeroDeCuenta.setVisible(false);
			rdbtnDni.setVisible(false);
			rdbtnApellido.setVisible(false);
			rdbtnNombre.setVisible(false);
			rdbtnTipoUsuario.setVisible(false);
		}
		
		ActionListener listenerEliminar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila >= 0) {
					int valor = (int) table.getValueAt(fila, 0);
					UsuarioDAO udao = new UsuarioDAO();
					udao.borrar(valor);
					modelo.removeRow(fila);
				}else {
					JOptionPane.showMessageDialog(null,"Debes seleccionar un usuario.");

				}
			}
		};
		
		ActionListener listenerAtras = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelPrincipal panel = new PanelPrincipal(frame, true);
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		ActionListener listenerModificar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila >= 0) {
					String apellido = (String) table.getValueAt(fila, 2);
					String nombre = (String) table.getValueAt(fila, 1);
					int contraseña = (int) table.getValueAt(fila, 3);
					String domicilio = (String) table.getValueAt(fila, 4);
					int telefono = (int) table.getValueAt(fila, 5);
					String tipoUsuario = (String) table.getValueAt(fila, 6);
					Usuario u = new Usuario(apellido, nombre, contraseña, domicilio, telefono, tipoUsuario);
					Integer valor = (Integer) table.getValueAt(fila, 0);
					Integer valor1 = (Integer) table.getValueAt(fila, 7);
					CrearCuenta panel = new CrearCuenta (frame, false, valor, valor1, u);
					frame.setContentPane(panel);
					frame.validate();
					}else {
						JOptionPane.showMessageDialog(null,"Debes seleccionar un usuario.");

					}
			}
		};
		
		ActionListener listenerAceptar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean xCuenta = rdbtnNmeroDeCuenta.isSelected();
				boolean xDni = rdbtnDni.isSelected();
				boolean xNombre = rdbtnNombre.isSelected();
				boolean xApellido = rdbtnApellido.isSelected();
				boolean xTipoUsuario = rdbtnTipoUsuario.isSelected();
				if(!xCuenta && !xDni && !xNombre && !xApellido && !xTipoUsuario) {
					JOptionPane.showMessageDialog(null,"Debe seleccionar al menos una opción.");

				} else {
					ConsultaUsuario panel = new ConsultaUsuario(frame, xCuenta, xDni, xNombre, xApellido, xTipoUsuario);
				    frame.setContentPane(panel);
				    frame.validate();				
				}
				
			}
		};
		
		btnEliminar.addActionListener(listenerEliminar);
		btnAtrs.addActionListener(listenerAtras);
		btnModificar.addActionListener(listenerModificar);
		btnAceptar.addActionListener(listenerAceptar);
	}
}
