package estructuraTP.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import estructuraTP.DAO.CuentaDAO;
import estructuraTP.modelo.Servicio;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;

import javax.swing.JButton;

public class PagarServicio extends JPanel {
	private JTextField textFieldCliente;
	private JTextField textFieldPagar;

	public PagarServicio(JFrame frame, int id) {
		setLayout(null);
		
		JLabel lblPagoDeServicio = new JLabel("Pago de servicio");
		lblPagoDeServicio.setBounds(224, 24, 112, 14);
		add(lblPagoDeServicio);
		
		JLabel lblServicioAPagar = new JLabel("Servicio a pagar");
		lblServicioAPagar.setBounds(28, 77, 126, 14);
		add(lblServicioAPagar);
		
		JLabel lblNroDeCliente = new JLabel("Nro de cliente");
		lblNroDeCliente.setBounds(28, 118, 112, 14);
		add(lblNroDeCliente);
		
		JLabel lblNewLabel = new JLabel("Importe a pagar");
		lblNewLabel.setBounds(28, 165, 126, 14);
		add(lblNewLabel);
		
		
		
		textFieldCliente = new JTextField();
		textFieldCliente.setBounds(131, 115, 86, 20);
		add(textFieldCliente);
		textFieldCliente.setColumns(10);
		textFieldCliente.addKeyListener(new KeyAdapter(){
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
		
		textFieldPagar = new JTextField();
		textFieldPagar.setBounds(131, 162, 86, 20);
		add(textFieldPagar);
		textFieldPagar.setColumns(10);
		textFieldPagar.addKeyListener(new KeyAdapter(){
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
		comboBox.setBounds(131, 73, 133, 18);
		add(comboBox);
		
		comboBox.addItem("MetroGas");
		comboBox.addItem("AySA");
		comboBox.addItem("Telecentro");
		comboBox.addItem("EDESUR");
		comboBox.addItem("EDENOR");
		comboBox.addItem("Telefonica");
		
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(372, 300, 89, 23);
		add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(51, 300, 89, 23);
		add(btnCancelar);
		
		JLabel labelSaldo = new JLabel("");
		labelSaldo.setBounds(129, 254, 362, 14);
		add(labelSaldo);
		CuentaDAO cDao= new CuentaDAO();
		float dineroCuenta= cDao.consultarSaldo(id);
		labelSaldo.setText("Recuerde que su saldo es de $" + Float.toString(dineroCuenta));
		ActionListener ConfirmarPF = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean[] field = new boolean[2];
				boolean r=false;
				field[0]=textFieldPagar.getText().isEmpty();
				field[1]=textFieldCliente.getText().isEmpty();
				if(field[0] || field[1]){
					r=true;
				}else{
					r=false;
				}
				if(r) {
					JOptionPane.showMessageDialog(null, "Alguno de los campos esta vacio.\nInténtelo de nuevo.");					
				} else {
				
				float pagar = Float.parseFloat(textFieldPagar.getText());
				int nro_cliente = Integer.parseInt(textFieldCliente.getText());
				String servicio =  (String) comboBox.getSelectedItem();
				JLabel labelDinero = new JLabel("");
				labelDinero.setBounds(178, 177, 350, 14);
				add(labelDinero);
				int idCuentaa=cDao.consultarIdCuenta(id);
				labelDinero.setText("Recuerde que su saldo es de $" + Float.toString(dineroCuenta));
				Servicio c= new Servicio(idCuentaa, nro_cliente, servicio, LocalDate.now(), pagar);
				if(pagar>dineroCuenta)
				{
					JOptionPane.showMessageDialog(null,"Saldo insuficiente, por favor ingrese dinero que disponga");
					textFieldPagar.setText(null);;
				}
				else
				{
					
					cDao.pagarServices(idCuentaa, c);
					JOptionPane.showMessageDialog(null,"Servicio pagado, el dinero actual de su cuenta es $" + cDao.consultarSaldo(id));
					PanelPrincipal panel = new PanelPrincipal(frame, false);
					frame.setContentPane(panel);
			        frame.validate();
				}
				}
			}
		};
		btnConfirmar.addActionListener(ConfirmarPF);
		ActionListener salir = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					PanelPrincipal panel = new PanelPrincipal(frame, false);
					frame.setContentPane(panel);
			        frame.validate();
				
			}
		};
		btnCancelar.addActionListener(salir);
		
	}

}