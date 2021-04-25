package estructuraTP.vista;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import estructuraTP.DAO.UsuarioDAO;
import estructuraTP.modelo.Usuario;

public class ConsultaUsuario extends JPanel {

	private JTextField textFieldCuenta;
	private JTextField textFieldDni;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private String tipoUsuario = "";
	
	public ConsultaUsuario(JFrame frame, boolean xCuenta, boolean xDni, boolean xNombre, boolean xApellido, boolean xTipoUsuario) {

		setLayout(null);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(502, 375, 89, 23);
		add(btnAceptar);
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.setBounds(40, 375, 89, 23);
		add(btnAtrs);
		
		JLabel lblNmeroDeCuenta = new JLabel("N\u00FAmero de cuenta:");
		lblNmeroDeCuenta.setBounds(40, 64, 107, 14);
		add(lblNmeroDeCuenta);
		
		textFieldCuenta = new JTextField();
		textFieldCuenta.setBounds(157, 61, 129, 20);
		add(textFieldCuenta);
		textFieldCuenta.setColumns(10);
		textFieldCuenta.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if(c<'0'|| c>'9') {
					evt.consume();
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Solo se aceptan dígitos.");

				}
			}
		});
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(40, 89, 89, 14);
		add(lblDni);
		
		textFieldDni = new JTextField();
		textFieldDni.setBounds(157, 86, 129, 20);
		add(textFieldDni);
		textFieldDni.setColumns(10);
		textFieldDni.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if(c<'0'|| c>'9') {
					evt.consume();
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Solo se aceptan dígitos.");

				}
			}
		});
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(40, 114, 89, 14);
		add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(157, 111, 129, 20);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		textFieldNombre.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if(Character.isDigit(c)) {
					evt.consume();
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Solo se aceptan letras.");

				}
			}
		});
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(40, 139, 89, 14);
		add(lblApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(157, 136, 129, 20);
		add(textFieldApellido);
		textFieldApellido.setColumns(10);
		textFieldApellido.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if(Character.isDigit(c)) {
					evt.consume();
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Solo se aceptan letras.");

				}
			}
		});
		
		JLabel lblTipoUsuario = new JLabel("Tipo usuario:");
		lblTipoUsuario.setBounds(40, 164, 89, 14);
		add(lblTipoUsuario);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(157, 163, 96, 17);
		add(comboBox);
			
		comboBox.addItem("admin");
		comboBox.addItem("cliente");
		
		if(!xCuenta) {
			lblNmeroDeCuenta.setVisible(false);
			textFieldCuenta.setVisible(false);			
		}
		if(!xDni) {			
			lblDni.setVisible(false);
			textFieldDni.setVisible(false);
		}
		if(!xNombre) {
			lblNombre.setVisible(false);
			textFieldNombre.setVisible(false);
		}
		if(!xApellido) {
			lblApellido.setVisible(false);
			textFieldApellido.setVisible(false);
		}
		if(!xTipoUsuario) {
			lblTipoUsuario.setVisible(false);
			comboBox.setVisible(false);
		}
		
		ActionListener listenerAtras = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Usuarios panel = new Usuarios(frame, true,false , null);
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		ActionListener listenerAceptar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean[] field = new boolean[4];
				boolean r=false;
				if(!xCuenta) {
					textFieldCuenta.setText("0");
				}
				if(!xDni) {
					textFieldDni.setText("0");
				}
				if(!xNombre) {
					textFieldNombre.setText("0");
				}
				if(!xApellido) {
					textFieldApellido.setText("0");
				}
				field[0]=textFieldCuenta.getText().isEmpty();
				field[1]=textFieldDni.getText().isEmpty();
				field[2]=textFieldNombre.getText().isEmpty();
				field[3]=textFieldApellido.getText().isEmpty();
				if(field[0] || field[1] || field[2] || field[3]){
					r=true;
				}else{
					r=false;
				}
				if(r) {
					JOptionPane.showMessageDialog(null, "Alguno de los campos esta vacio.\nInténtelo de nuevo.");					
				} else {
					Usuarios panel = new Usuarios(frame, true, true,consultaFiltrada(xCuenta, xDni, xNombre, xApellido, xTipoUsuario));
					frame.setContentPane(panel);
					frame.validate();					
				}
			}
		};
		
		btnAtrs.addActionListener(listenerAtras);
		btnAceptar.addActionListener(listenerAceptar);
		
		tipoUsuario = (String) comboBox.getSelectedItem();
	}
	
	public int cantParametros(boolean xCuenta, boolean xDni, boolean xNombre, boolean xApellido, boolean xTipoUsuario) {
		int s = 0;
		if (xCuenta)
			s += 1;
		if (xDni)
			s += 1;
		if (xNombre)
			s += 1;
		if (xApellido)
			s += 1;
		if (xTipoUsuario)
			s += 1;
		return s;
	}
	
	public ArrayList<Usuario> consultaFiltrada(boolean xCuenta, boolean xDni, boolean xNombre, boolean xApellido, boolean xTipoUsuario) {
		ArrayList<Usuario> s = new ArrayList<Usuario>();
		UsuarioDAO pfd = new UsuarioDAO();
		ArrayList<Usuario> consulta = pfd.consultar();
		int cantParametros = cantParametros(xCuenta, xDni, xNombre, xApellido, xTipoUsuario);
		for(Usuario u : consulta) {
			int aux = 0;
			
			if(xCuenta) {
				if(u.getIdCuenta() == Integer.parseInt(textFieldCuenta.getText())) {
					aux += 1;
				}
			}
			
			if(xDni) {
				if(u.getDni() == Integer.parseInt(textFieldDni.getText())) {
					aux += 1;
				}
			}
			
			if(xNombre) {
				if(u.getNombre().contentEquals(textFieldNombre.getText())) {
					aux += 1;
				}
			}
			
			if(xApellido) {
				if(u.getApellido().contentEquals(textFieldApellido.getText())) {
					aux += 1;
				}
			}
			
			if(xTipoUsuario) {
				if(u.getTipoUsuario().equals(tipoUsuario)) {
					aux += 1;
				}
			}
			
			if(aux == cantParametros){
				s.add(u);
			}
			
		}
		
		return s;
	}
}
