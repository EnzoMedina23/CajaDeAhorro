package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import estructuraTP.DAO.CuentaDAO;
import estructuraTP.DAO.UsuarioDAO;
import estructuraTP.modelo.Movimiento;
import estructuraTP.modelo.Usuario;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;

public class PanelInicio extends JPanel {
	private JTextField textFieldUsuario;
	private JPasswordField passwordField;
	private int id;

	public PanelInicio(JFrame frame) {
		setLayout(null);
		
		frame.setJMenuBar(null);

		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(175, 150, 89, 14);
		add(lblUsuario);
		
		JLabel lblContraseña = new JLabel("Contrase\u00F1a:");
		lblContraseña.setBounds(175, 200, 89, 14);
		add(lblContraseña);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(275, 150, 120, 20);
		add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		textFieldUsuario.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if(c<'0'|| c>'9'||textFieldUsuario.getText().length()>=8) {
					evt.consume();
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(null, "Solo se aceptan dígitos.");
				}
			}
		});
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(500, 350, 89, 23);
		add(btnAceptar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(40, 350, 89, 23);
		add(btnSalir);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(274, 197, 121, 20);
		add(passwordField);
		passwordField.addKeyListener(new KeyAdapter(){
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
		
		ActionListener listenerSalir = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		};
		
		
		ActionListener listenerAceptar = new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				boolean[] field = new boolean[2];
				boolean r=false;
				field[0]=textFieldUsuario.getText().isEmpty();
				field[1]=passwordField.getText().isEmpty();
				if(field[0] || field[1]){
					r=true;
				}else{
					r=false;
				}
				if(r) {
					JOptionPane.showMessageDialog(null, "Alguno de los campos esta vacio.\nInténtelo de nuevo.");					
				} else {
					
					id = Integer.parseInt(textFieldUsuario.getText());
					UsuarioDAO idao = new UsuarioDAO();
					Usuario in = idao.datosCuenta(id);
					if(in.getContraseña() == Integer.parseInt(passwordField.getText())) {
						
						if(in.getTipoUsuario().contentEquals("cliente")) {
							PanelPrincipal panel = new PanelPrincipal(frame, false);
							menuCliente(frame);
							frame.setContentPane(panel);

						} else if(in.getTipoUsuario().contentEquals("admin")){
							PanelPrincipal panel = new PanelPrincipal(frame, true);
							menuBanco(frame);
							frame.setContentPane(panel);


						}
						
						frame.validate();
					} else {
						JOptionPane.showMessageDialog(null, "Alguno de los datos es incorrecto.\nInténtelo de nuevo.");
						textFieldUsuario.setText(null);
						passwordField.setText(null);
					}
				}
			}
		};
		
		passwordField.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnAceptar.doClick();
				}
			}
		});
		
		btnSalir.addActionListener(listenerSalir);
		btnAceptar.addActionListener(listenerAceptar);
		
		
	}
	
	
	public void menuCliente(JFrame frame) 
	{
		JMenuBar menu = new JMenuBar();
		JMenu movimientos = new JMenu("Movimientos");
		JMenu consultas= new JMenu("Consultas");
		JMenu pf = new JMenu("Plazo Fijo");
		JMenuItem deposito = new JMenuItem("Depósito");
		JMenuItem extraccion = new JMenuItem("Extracción");
		JMenuItem consultarmovimientos = new JMenuItem("Movimientos");
		JMenuItem consultarsaldo = new JMenuItem("Saldo");
		JMenuItem crearpf = new JMenuItem("Crear Plazo Fijo");
		JMenuItem pagarServic = new JMenuItem("Pagar un servicio");
		menu.add(movimientos);
		menu.add(consultas);
		menu.add(pf);
		movimientos.add(deposito);
		movimientos.add(extraccion);
		movimientos.add(pagarServic);
		consultas.add(consultarmovimientos);
		consultas.add(consultarsaldo);
		pf.add(crearpf);
		frame.setJMenuBar(menu);
		
		ActionListener listenerDepo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepoEstra panel = new DepoEstra(frame, id, true);
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		deposito.addActionListener(listenerDepo);
		
		ActionListener listenerExtra = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepoEstra panel = new DepoEstra(frame, id, false);
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		
		extraccion.addActionListener(listenerExtra);
		
		ActionListener listenerCrearPF = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearPlazoFijo panel = new CrearPlazoFijo(frame, id);
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		
		crearpf.addActionListener(listenerCrearPF);
		
		ActionListener listenerConsultaSaldo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CuentaDAO cDao= new CuentaDAO();
				float dineroCuenta= cDao.consultarSaldo(id);
				JOptionPane.showMessageDialog(null,("Recuerde que su saldo es de $" + Float.toString(dineroCuenta)));			
			}
		};
			
		consultarsaldo.addActionListener(listenerConsultaSaldo);
		
		ActionListener listenerMovimiento = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Movimientos panel = new Movimientos(frame, false, null, true, Integer.parseInt(textFieldUsuario.getText()));
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		consultarmovimientos.addActionListener(listenerMovimiento);
		
		ActionListener pagServi = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PagarServicio panel = new PagarServicio(frame, id);
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		
		pagarServic.addActionListener(pagServi);
		
	}
	
	public void menuBanco(JFrame frame) 
	{
		JMenuBar menu = new JMenuBar();
		JMenu abmclientes = new JMenu("ABM clientes");
		JMenu consultas = new JMenu("Consultas");
		JMenuItem alta = new JMenuItem("Alta");
		JMenuItem baja = new JMenuItem("Baja/Modificación");
		JMenuItem movimientos = new JMenuItem("Movimientos");
		JMenuItem clientes = new JMenuItem("Clientes");
		JMenuItem pf = new JMenuItem("Plazos Fijos");
		menu.add(abmclientes);
		menu.add(consultas);
		abmclientes.add(alta);
		abmclientes.add(baja);
		consultas.add(movimientos);
		consultas.add(clientes);
		consultas.add(pf);
		frame.setJMenuBar(menu);
		
		ActionListener listenerAlta = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearCuenta panel = new CrearCuenta(frame, true, null, null, null);
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		ActionListener listenerBajaModificacion = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios panel = new Usuarios(frame, false, false, null);
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		ActionListener listenerPlazoFijo = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlazoFijoPanel panel = new PlazoFijoPanel(frame, false, null);
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		ActionListener listenerMovimiento = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Movimientos panel = new Movimientos(frame, false, null, false, Integer.parseInt(textFieldUsuario.getText()));
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		ActionListener listenerClientes = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios panel = new Usuarios(frame, true,false, null);
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		alta.addActionListener(listenerAlta);
		baja.addActionListener(listenerBajaModificacion);
		pf.addActionListener(listenerPlazoFijo);
		movimientos.addActionListener(listenerMovimiento);
		clientes.addActionListener(listenerClientes);


	}
}
