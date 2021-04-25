package estructuraTP.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;

import estructuraTP.modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.Font;

public class PanelPrincipal extends JPanel {

	public PanelPrincipal(JFrame frame, boolean esAdmin) {
		
		
		
		setLayout(null);
		
		JLabel lblCajaDeAhorro = new JLabel("CAJA DE AHORRO");
		lblCajaDeAhorro.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblCajaDeAhorro.setBounds(206, 77, 266, 46);
		add(lblCajaDeAhorro);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(250, 300, 139, 23);
		add(btnSalir);
		
		JButton btnCerrarSesin = new JButton("Cerrar sesi\u00F3n");
		btnCerrarSesin.setBounds(250, 250, 139, 23);
		add(btnCerrarSesin);
		JButton btnBackup = new JButton("Backup");
		btnBackup.setBounds(250, 200, 139, 23);
		add(btnBackup);
		
		if(!esAdmin) {
			btnBackup.setVisible(false);
		} 
		
		
		ActionListener listenerSalir = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		};
		
		ActionListener listenerCerrarSesion = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelInicio panel = new PanelInicio(frame);
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		btnSalir.addActionListener(listenerSalir);
		btnCerrarSesin.addActionListener(listenerCerrarSesion);

	}
}
