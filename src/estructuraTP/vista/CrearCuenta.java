package estructuraTP.vista;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import estructuraTP.DAO.CuentaDAO;
import estructuraTP.DAO.UsuarioDAO;
import estructuraTP.modelo.Usuario;

public class CrearCuenta extends JPanel {
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDNI;
	private JTextField textFieldContraseña;
	private JTextField textFieldDomicilio;
	private JTextField textFieldTelefono;
	private JTextField textFieldSaldo;

	public CrearCuenta(JFrame frame, boolean crear, Integer valor, Integer valor1, Usuario u) {
		setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(40, 125, 69, 14);
		add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(40, 100, 69, 14);
		add(lblApellido);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(40, 75, 69, 14);
		add(lblDni);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(40, 150, 69, 14);
		add(lblContrasea);
		
		JLabel lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setBounds(40, 175, 69, 14);
		add(lblDomicilio);
		
		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setBounds(40, 200, 69, 14);
		add(lblTelfono);
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de usuario:");
		lblTipoDeUsuario.setBounds(40, 250, 99, 14);
		add(lblTipoDeUsuario);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		lblSaldo.setBounds(40, 225, 99, 14);
		add(lblSaldo);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(144, 122, 125, 20);
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
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(144, 97, 125, 20);
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
		
		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(144, 72, 125, 20);
		add(textFieldDNI);
		textFieldDNI.setColumns(10);
		textFieldDNI.addKeyListener(new KeyAdapter(){
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
		
		textFieldContraseña = new JTextField();
		textFieldContraseña.setBounds(144, 147, 125, 20);
		add(textFieldContraseña);
		textFieldContraseña.setColumns(10);
		textFieldContraseña.addKeyListener(new KeyAdapter(){
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
		
		textFieldDomicilio = new JTextField();
		textFieldDomicilio.setBounds(144, 175, 125, 20);
		add(textFieldDomicilio);
		textFieldDomicilio.setColumns(10);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(144, 200, 125, 20);
		add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		textFieldTelefono.addKeyListener(new KeyAdapter(){
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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(144, 252, 96, 17);
		add(comboBox);
			
		comboBox.addItem("admin");
		comboBox.addItem("cliente");
		
		textFieldSaldo = new JTextField();
		textFieldSaldo.setBounds(144, 225, 125, 20);
		add(textFieldSaldo);
		textFieldSaldo.setColumns(10);
		textFieldSaldo.addKeyListener(new KeyAdapter(){
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

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(500, 350, 89, 23);
		add(btnAceptar);
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.setBounds(40, 350, 89, 23);
		add(btnAtrs);
		
		if(!crear) {
			textFieldDNI.setVisible(false);
			lblDni.setVisible(false);
			lblTipoDeUsuario.setVisible(false);
			comboBox.setVisible(false);
			lblSaldo.setVisible(false);
			textFieldSaldo.setVisible(false);
			textFieldApellido.setText(u.getApellido());
			textFieldNombre.setText(u.getNombre());
			textFieldContraseña.setText(u.getContraseña().toString());
			textFieldDomicilio.setText(u.getDomicilio());
			textFieldTelefono.setText(u.getTelefono().toString());

		}
		
		
		ActionListener listenerAtras = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(crear) {
					PanelPrincipal panel = new PanelPrincipal(frame, true);					
					frame.setContentPane(panel);
				} else {
					Usuarios panel = new Usuarios(frame, false,false, null);
					frame.setContentPane(panel);
				}
		        frame.validate();
			}
		};
		

		ActionListener listenerAceptar = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cont = 0;
				boolean[] field = new boolean[7];
				boolean r=false;
				field[0]=textFieldNombre.getText().isEmpty();
				field[1]=textFieldApellido.getText().isEmpty();
				field[2]=textFieldContraseña.getText().isEmpty();
				field[3]=textFieldDomicilio.getText().isEmpty();
				field[4]=textFieldTelefono.getText().isEmpty();
				field[5]=textFieldSaldo.getText().isEmpty();
				field[6]=textFieldDNI.getText().isEmpty();
				if(field[0] || field[1] || field[2] || field[3]|| field[4] || field[5] || field[6]){
					r=true;
				}else{
					r=false;
				}
				if(r) {
					JOptionPane.showMessageDialog(null, "Alguno de los campos esta vacio.\nInténtelo de nuevo.");

				} else {
					String nombre = textFieldNombre.getText();
					String apellido = textFieldApellido.getText();
					Integer contraseña = Integer.parseInt(textFieldContraseña.getText());
					String domicilio = textFieldDomicilio.getText();
					Integer telefono = Integer.parseInt(textFieldTelefono.getText());
					String tipoUsuario = (String) comboBox.getSelectedItem();
					float saldo = Float.parseFloat(textFieldSaldo.getText());
					UsuarioDAO idao = new UsuarioDAO();
					if(crear) {
						
						int dni = Integer.parseInt(textFieldDNI.getText());
						
						for(Usuario u : idao.consultar()) {
							if(u.getDni() == dni) {
								JOptionPane.showMessageDialog(null, "El DNI ingresado ya está registrado.\nInténtelo de nuevo.");
								cont++;
							}
						}
			
						if(cont == 0) {
							CuentaDAO cdao = new CuentaDAO();
							//valor1 = idcuenta
							int valor1 = cdao.consultarIdCuenta(dni);
							Usuario in = new Usuario(nombre, apellido, dni, contraseña, domicilio, telefono, tipoUsuario, valor1, saldo);
							idao.guardar(in);
							PanelPrincipal panel = new PanelPrincipal(frame, true);					
							frame.setContentPane(panel);						
						}
					} else {
						Usuario in = new Usuario(nombre, apellido, contraseña, domicilio, telefono, tipoUsuario, valor1, saldo);
						idao.modificar(in, valor, valor1);
						Usuarios panel = new Usuarios(frame, false, false, null);
						frame.setContentPane(panel);
					}
			        frame.validate();
				}
				
			}
		};
		
		textFieldContraseña.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnAceptar.doClick();
				}
			}
		});
		
		btnAtrs.addActionListener(listenerAtras);
		btnAceptar.addActionListener(listenerAceptar);
	}
}
