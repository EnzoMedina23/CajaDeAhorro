package estructuraTP.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.DAO.CuentaDAO;
import estructuraTP.DAO.MovimientoDAO;
import estructuraTP.DAO.PlazoFijoDAO;
import estructuraTP.modelo.PlazoFijo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class PlazoFijoPanel extends JPanel {

	public PlazoFijoPanel(JFrame frame, boolean tablaFiltrada, ArrayList<PlazoFijo> consultaFiltrada) {
		setLayout(null);

		String[] nombresColumnas = { "Número plazo fijo", "Número cuenta", "Tasa interés", "Plazo", "Fecha inicio", "Fecha vencimiento", "Monto capital", "Monto interés", "Monto vencimiento"}; 
		
		DefaultTableModel modelo = new DefaultTableModel(nombresColumnas,0);
		
		PlazoFijoDAO pfd = new PlazoFijoDAO();
		ArrayList<PlazoFijo> resultado = new ArrayList<PlazoFijo>();
		if(tablaFiltrada) {
			resultado = consultaFiltrada;
		}
		else {
			resultado = pfd.consultar();						
		}
		
		
		for ( PlazoFijo p : resultado)
		{
	
		int nroPf = p.getNropf();
		int idCuenta = p.getIdCuenta();
		float tasaInteres = p.getTasaInteres();
		int plazo = p.getPlazo();
		LocalDate fechaInicio = p.getFechaInicio();
		LocalDate fechaVto = p.getFechaVto();
		float montoCapital = p.getMontoCapital();
		float montoInteres = p.getMontoInteres();
		float montoVto = p.getMontoVto();
			
		Object [] registro = {nroPf,idCuenta,tasaInteres,plazo,fechaInicio,fechaVto,montoCapital,montoInteres,montoVto};
		
		modelo.addRow(registro);		
	       
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 25, 628, 233);
		add(scrollPane);
		
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);
		
		JButton btnAceptar = new JButton("Consultar");
		btnAceptar.setBounds(502, 375, 89, 23);
		add(btnAceptar);
		
		JButton btnAtrs = new JButton("Atrás");
		btnAtrs.setBounds(40, 375, 89, 23);
		add(btnAtrs);
		
		JLabel lblConsultarPor = new JLabel("Consultar por:");
		lblConsultarPor.setBounds(148, 269, 89, 14);
		add(lblConsultarPor);
		
		JRadioButton rdbtnNmeroDeCuenta = new JRadioButton("N\u00FAmero de cuenta");
		rdbtnNmeroDeCuenta.setBounds(243, 265, 208, 23);
		add(rdbtnNmeroDeCuenta);
		
		JRadioButton rdbtnEntreFechas = new JRadioButton("Entre fechas");
		rdbtnEntreFechas.setBounds(243, 291, 109, 23);
		add(rdbtnEntreFechas);
		
		JRadioButton rdbtnEntreMontos = new JRadioButton("Entre montos");
		rdbtnEntreMontos.setBounds(243, 317, 109, 23);
		add(rdbtnEntreMontos);
		
		JButton btnAcreditar = new JButton("Acreditar");
		btnAcreditar.setBounds(502, 291, 89, 23);
		add(btnAcreditar);
		
		JLabel lblPlazosFijos = new JLabel("Plazos Fijos");
		lblPlazosFijos.setBounds(285, 11, 102, 14);
		add(lblPlazosFijos);
		
		ActionListener listenerAtras = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PanelPrincipal panel = new PanelPrincipal(frame, true);
		        frame.setContentPane(panel);
		        frame.validate();
			}
		};
		
		ActionListener listenerAceptar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean xCuenta = rdbtnNmeroDeCuenta.isSelected();
				boolean xFecha = rdbtnEntreFechas.isSelected();
				boolean xMonto = rdbtnEntreMontos.isSelected();
				if(!xCuenta && !xFecha && !xMonto) {
					JOptionPane.showMessageDialog(null,"Debe seleccionar al menos una opción.");

				} else {
					ConsultaPlazoFijo panel = new ConsultaPlazoFijo(frame, xCuenta, xFecha, xMonto);
					frame.setContentPane(panel);
					frame.validate();					
				}
			}
		};
		
		ActionListener listenerAcreditar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int fila = table.getSelectedRow();
				if(fila >= 0) {
					Integer idCuenta = (int) table.getValueAt(fila, 1);
					int plazo = (int) table.getValueAt(fila, 3);
					LocalDate fechaVto = (LocalDate) table.getValueAt(fila, 5);
					float montoCapital = (float) table.getValueAt(fila, 6);
					float montoVto = (float) table.getValueAt(fila, 8);
					MovimientoDAO mdao = new MovimientoDAO();
					CuentaDAO cdao = new CuentaDAO();
					if(fechaVto.equals(LocalDate.now())) {
						cdao.acreditar(idCuenta, montoVto);
						mdao.acreditar(idCuenta, plazo, montoCapital, montoVto);
						mdao.borrar(idCuenta, montoCapital, montoVto);
						modelo.removeRow(fila);
						float dineroCuenta2 = cdao.consultarSaldoCuenta(idCuenta);
						JOptionPane.showMessageDialog(null,"Acreditacion realizada realizado con exito, el nuevo saldo de la cuenta "+ idCuenta.toString() + " es de $" + Float.toString(dineroCuenta2));

					} else {
						JOptionPane.showMessageDialog(null,"El plazo fijo seleccionado no vence hoy.");
					}

				} else {
					JOptionPane.showMessageDialog(null,"Debe seleccionar el plazo fijo a acreditar.");

				}
			}
		};
		
		btnAtrs.addActionListener(listenerAtras);
		btnAceptar.addActionListener(listenerAceptar);
		btnAcreditar.addActionListener(listenerAcreditar);
	}
}
