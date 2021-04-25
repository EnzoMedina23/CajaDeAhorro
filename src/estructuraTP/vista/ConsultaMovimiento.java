package estructuraTP.vista;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import estructuraTP.DAO.MovimientoDAO;
import estructuraTP.DAO.PlazoFijoDAO;
import estructuraTP.modelo.Movimiento;
import estructuraTP.modelo.PlazoFijo;

public class ConsultaMovimiento extends JPanel {
	
	private JTextField textFieldCuenta;
	private JTextField textFieldMontoA;
	private JTextField textFieldMontoB;
	private JCalendar calendarioA;
	private JCalendar calendarioB;
	private JComboBox<String> comboBox;
	private String movimiento = "";
	
	public ConsultaMovimiento(JFrame frame, boolean xCuenta, boolean xFecha, boolean xMonto, boolean xMovimiento, boolean cliente, int dni) {

		
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
		
		
		JLabel lblEntreElMonto = new JLabel("Entre el monto:");
		lblEntreElMonto.setBounds(40, 107, 89, 14);
		add(lblEntreElMonto);
		
		textFieldMontoA = new JTextField();
		textFieldMontoA.setBounds(157, 104, 86, 20);
		add(textFieldMontoA);
		textFieldMontoA.setColumns(10);
		textFieldMontoA.addKeyListener(new KeyAdapter(){
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
		
		JLabel lblY = new JLabel("y");
		lblY.setBounds(267, 107, 6, 14);
		add(lblY);
		
		textFieldMontoB = new JTextField();
		textFieldMontoB.setBounds(309, 104, 86, 20);
		add(textFieldMontoB);
		textFieldMontoB.setColumns(10);
		textFieldMontoB.addKeyListener(new KeyAdapter(){
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
		
		JLabel lblEntreLasFechas = new JLabel("Entre las fechas:");
		lblEntreLasFechas.setBounds(40, 193, 107, 14);
		add(lblEntreLasFechas);
		
		calendarioA = new JCalendar();
		 
		calendarioA.setBounds(160, 193, 186, 150);
				 
		add(calendarioA);
		
		calendarioB = new JCalendar();
		 
		calendarioB.setBounds(370, 193, 186, 150);
				 
		add(calendarioB);
		
		JLabel lblY_1 = new JLabel("y");
		lblY_1.setBounds(356, 193, 6, 14);
		add(lblY_1);
		
		JLabel lblMovimiento = new JLabel("Movimiento:");
		lblMovimiento.setBounds(40, 150, 107, 14);
		add(lblMovimiento);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(157, 149, 96, 17);
		add(comboBox);
			
		comboBox.addItem("Depósito");
		comboBox.addItem("Extracción");
		comboBox.addItem("Servicios");
		comboBox.addItem("Creación plazo fijo");
		comboBox.addItem("Acreditación plazo fijo");
		
		if(!xCuenta) {
			lblNmeroDeCuenta.setVisible(false);
			textFieldCuenta.setVisible(false);			
		}
		if(!xFecha) {			
			lblEntreLasFechas.setVisible(false);
			lblY_1.setVisible(false);
		}
		if(!xMonto) {
			lblEntreElMonto.setVisible(false);
			lblY.setVisible(false);
			textFieldMontoA.setVisible(false);
			textFieldMontoB.setVisible(false);	
			calendarioA.setVisible(false);
			calendarioB.setVisible(false);
		}
		if(!xMovimiento) {
			lblMovimiento.setVisible(false);
			comboBox.setVisible(false);
		}
		if(cliente) {
			lblNmeroDeCuenta.setVisible(false);
			textFieldCuenta.setVisible(false);
		}
		
		
		
		ActionListener listenerAtras = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean consultarComoCliente;
				if(cliente) {
					consultarComoCliente = true;
				} else {
					consultarComoCliente = false;
				}
				Movimientos panel = new Movimientos(frame, false, null, consultarComoCliente, dni);
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		ActionListener listenerAceptar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				movimiento = (String) comboBox.getSelectedItem();
				boolean consultarComoCliente;
				boolean[] field = new boolean[3];
				boolean r=false;
				if(!xCuenta) {
					textFieldCuenta.setText("0");
				}
				if(!xMonto) {
					textFieldMontoA.setText("0");
					textFieldMontoB.setText("0");
				}
				field[0]=textFieldCuenta.getText().isEmpty();
				field[1]=textFieldMontoA.getText().isEmpty();
				field[2]=textFieldMontoB.getText().isEmpty();
				if(field[0] || field[1] || field[2]){
					r=true;
				}else{
					r=false;
				}
				if(r) {
					JOptionPane.showMessageDialog(null, "Alguno de los campos esta vacio.\nInténtelo de nuevo.");					
				} else {
					if(cliente) {
						consultarComoCliente = true;
					} else {
						consultarComoCliente = false;
					}
					Movimientos panel = new Movimientos(frame, true, consultaFiltrada(xCuenta, xFecha, xMonto, xMovimiento), consultarComoCliente, dni);
					frame.setContentPane(panel);
					frame.validate();					
				}
			}
		};
		
		btnAtrs.addActionListener(listenerAtras);
		btnAceptar.addActionListener(listenerAceptar);
	}
	
	public int cantParametros(boolean xCuenta, boolean xFecha, boolean xMonto, boolean xMovimiento) {
		int s = 0;
		if (xCuenta)
			s += 1;
		if (xFecha)
			s += 1;
		if (xMonto)
			s += 1;
		if (xMovimiento)
			s += 1;
		return s;
	}
	
	public ArrayList<Movimiento> consultaFiltrada(boolean xCuenta, boolean xFecha, boolean xMonto, boolean xMovimiento) {
		ArrayList<Movimiento> s = new ArrayList<Movimiento>();
		MovimientoDAO mdao = new MovimientoDAO();
		ArrayList<Movimiento> consulta = mdao.consultar();
		int cantParametros = cantParametros(xCuenta, xFecha, xMonto, xMovimiento);
		for(Movimiento m : consulta) {
			int aux = 0;
			
			if(xCuenta) {
				if(m.getIdCuenta() == Integer.parseInt(textFieldCuenta.getText())) {
					aux += 1;
				}
			}
			
			if(xFecha) {
				LocalDate fechaMenor = calendarioA.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate fechaMayor = calendarioB.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();				
				if(fechaMenor.isAfter(fechaMayor)) {
					fechaMenor = calendarioA.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					fechaMayor = calendarioB.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();	
				}
				if(m.getFechaMovimiento().isAfter(fechaMenor) || m.getFechaMovimiento().isEqual(fechaMenor) && m.getFechaMovimiento().isBefore(fechaMayor) || m.getFechaMovimiento().isEqual(fechaMayor) ) {
					aux += 1;
				}
			}
			
			if(xMonto) {
				float montoMenor = Float.parseFloat(textFieldMontoA.getText());
				float montoMayor = Float.parseFloat(textFieldMontoB.getText());
				if(montoMenor > montoMayor) {
					montoMayor = Float.parseFloat(textFieldMontoA.getText());
					montoMenor = Float.parseFloat(textFieldMontoB.getText());
				}
				if(m.getMonto() >= montoMenor && m.getMonto() <= montoMayor) {
					aux += 1;
				}
			}
			
			if(xMovimiento) {
				if(m.getTipoMovimiento().equals(movimiento)) {
					aux += 1;
				}
			}
			
			if(aux == cantParametros){
				s.add(m);
			}
			
		}
		
		return s;
	}

}
