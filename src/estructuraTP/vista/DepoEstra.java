package estructuraTP.vista;

import javax.swing.JPanel;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import estructuraTP.DAO.CuentaDAO;

public class DepoEstra extends JPanel {

	public DepoEstra(JFrame frame, int id, boolean deposi) {
		setLayout(null);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(178, 266, 100, 23);
		add(btnConfirmar);
		
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.setBounds(40, 350, 89, 23);
		add(btnAtrs);
		
		ActionListener listenerAtras = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelPrincipal panel = new PanelPrincipal(frame, false);
				frame.setContentPane(panel);
				frame.validate();
			}
		};
		btnAtrs.addActionListener(listenerAtras);
		
		if(deposi)
		{
			JLabel lblIngreseDinero = new JLabel("Ingrese dinero a depositar ");
			lblIngreseDinero.setBounds(65, 91, 159, 14);
			add(lblIngreseDinero);
			
			JTextField textFieldDeposito = new JTextField();
			textFieldDeposito.setBounds(215, 88, 86, 20);
			add(textFieldDeposito);
			textFieldDeposito.setColumns(10);
			textFieldDeposito.addKeyListener(new KeyAdapter(){
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
			
			JLabel labelDinero = new JLabel("");
			labelDinero.setBounds(178, 177, 350, 14);
			add(labelDinero);
			CuentaDAO cDao= new CuentaDAO();
			float dineroCuenta= cDao.consultarSaldo(id);
			labelDinero.setText("Recuerde que su saldo es de $" + Float.toString(dineroCuenta));
			
			
			ActionListener ConfirmarDeposito = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean[] field = new boolean[1];
					boolean r=false;
					field[0]=textFieldDeposito.getText().isEmpty();
					if(field[0]){
						r=true;
					}else{
						r=false;
					}
					if(r) {					
						JOptionPane.showMessageDialog(null, "Alguno de los campos esta vacio.\nInténtelo de nuevo.");
					} else {
						
						float aDepositar = Float.parseFloat(textFieldDeposito.getText());
						cDao.depositarDinero(aDepositar, id,cDao.consultarIdCuenta(id));
						float dineroCuenta2= cDao.consultarSaldo(id);
						JOptionPane.showMessageDialog(null,"Deposito realizado con exito, su nuevo saldo es de $" + Float.toString(dineroCuenta2));
						PanelPrincipal panel = new PanelPrincipal(frame, false);
						frame.setContentPane(panel);
						frame.validate();
					}
				}
			};
			btnConfirmar.addActionListener(ConfirmarDeposito);
		}
		
		
		if(!deposi)
		{
			JLabel lblIngreseDinero = new JLabel("Ingrese dinero a retirar ");
			lblIngreseDinero.setBounds(65, 91, 159, 14);
			add(lblIngreseDinero);
			
			JTextField textFieldRetirar = new JTextField();
			textFieldRetirar.setBounds(215, 88, 86, 20);
			add(textFieldRetirar);
			textFieldRetirar.setColumns(10);
			textFieldRetirar.addKeyListener(new KeyAdapter(){
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
			
			JLabel labelDinero = new JLabel("");
			labelDinero.setBounds(178, 177, 350, 14);
			add(labelDinero);
			CuentaDAO cDao= new CuentaDAO();
			float dineroCuenta= cDao.consultarSaldo(id);
			labelDinero.setText("Recuerde que su saldo es de $" + Float.toString(dineroCuenta));
			
			ActionListener ConfirmarExtra = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean[] field = new boolean[1];
					boolean r=false;
					field[0]=textFieldRetirar.getText().isEmpty();
					if(field[0]){
						r=true;
					}else{
						r=false;
					}
					if(r) {					
						JOptionPane.showMessageDialog(null, "Alguno de los campos esta vacio.\nInténtelo de nuevo.");
					} else {
						
						float aRetirar = Float.parseFloat(textFieldRetirar.getText());
						if(aRetirar>dineroCuenta)
						{
							JOptionPane.showMessageDialog(null,"Saldo insuficiente, por favor ingrese dinero que disponga");
							textFieldRetirar.setText(null);;
						}
						else
						{
							cDao.retirarDinero(aRetirar, id, cDao.consultarIdCuenta(id));
							float dineroCuenta2= cDao.consultarSaldo(id);
							JOptionPane.showMessageDialog(null,"Extraccion realizado con exito, su nuevo saldo es de $" + Float.toString(dineroCuenta2));
							PanelPrincipal panel = new PanelPrincipal(frame, false);
							frame.setContentPane(panel);
							frame.validate();
						}
					}
				}
			};
			btnConfirmar.addActionListener(ConfirmarExtra);
		}
		

        //JOptionPane.showMessageDialog(null,"Saldo insuficiente, ingrese de nuevo pa");

		
	}
}

