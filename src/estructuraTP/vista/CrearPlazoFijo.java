package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import estructuraTP.DAO.CuentaDAO;
import estructuraTP.DAO.PlazoFijoDAO;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

public class CrearPlazoFijo extends JPanel {
	private JTextField textFieldCapital;

	public CrearPlazoFijo(JFrame frame, int id) {
		setLayout(null);
		
		JLabel lblCreacionDePlazo = new JLabel("Creacion de plazo fijo");
		lblCreacionDePlazo.setBounds(185, 11, 130, 14);
		add(lblCreacionDePlazo);
		
		JLabel lblDineroAUtilizar = new JLabel("Dinero a utilizar");
		lblDineroAUtilizar.setBounds(10, 58, 110, 14);
		add(lblDineroAUtilizar);
		
		textFieldCapital = new JTextField();
		textFieldCapital.setBounds(104, 55, 86, 20);
		add(textFieldCapital);
		textFieldCapital.setColumns(10);
		textFieldCapital.addKeyListener(new KeyAdapter(){
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
		
		JLabel lblPlazo = new JLabel("Duracion del plazo");
		lblPlazo.setBounds(10, 118, 110, 14);
		add(lblPlazo);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(130, 116, 130, 18);
		add(comboBox);
		comboBox.addItem("30");
		comboBox.addItem("90");
		
		JLabel label1 = new JLabel("");
		label1.setBounds(157, 207, 216, 14);
		add(label1);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(502, 375, 89, 23);
		add(btnAceptar);
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.setBounds(40, 375, 89, 23);
		add(btnAtrs);
		
		
		PlazoFijoDAO pfd = new PlazoFijoDAO();
		CuentaDAO cDao= new CuentaDAO();
		float dineroCuenta= cDao.consultarSaldo(id);
		label1.setText("Recuerde que su saldo es de $" + Float.toString(dineroCuenta));
		int idCuentaa = cDao.consultarIdCuenta(id);
		ActionListener ConfirmarPF = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int plazo =  Integer.parseInt((String) comboBox.getSelectedItem());
				
				boolean[] field = new boolean[1];
				boolean r=false;
				field[0]=textFieldCapital.getText().isEmpty();
				if(field[0]){
					r=true;
				}else{
					r=false;
				}
				if(r) {					
					JOptionPane.showMessageDialog(null, "Alguno de los campos esta vacio.\nInténtelo de nuevo.");
				} else {
					float aRetirar = Float.parseFloat(textFieldCapital.getText());
					if(aRetirar>dineroCuenta)
					{
						JOptionPane.showMessageDialog(null,"Saldo insuficiente, por favor ingrese dinero que disponga");
						textFieldCapital.setText(null);;
					}
					else
					{
						float total= pfd.crearPlazoFijo(aRetirar, idCuentaa, plazo);
						JOptionPane.showMessageDialog(null,"Plazo fijo realizado con exito, el dinero que recibira cuando termine sera $" + Float.toString(total));
						PanelPrincipal panel = new PanelPrincipal(frame, false);
						frame.setContentPane(panel);
						frame.validate();
					
				}
				}
			}
		};
		
		ActionListener listenerAtras = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelPrincipal panel = new PanelPrincipal(frame, false);
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		btnAceptar.addActionListener(ConfirmarPF);
		btnAtrs.addActionListener(listenerAtras);
	}
}

