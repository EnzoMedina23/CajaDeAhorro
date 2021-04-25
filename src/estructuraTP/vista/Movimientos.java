package estructuraTP.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.DAO.CuentaDAO;
import estructuraTP.DAO.MovimientoDAO;
import estructuraTP.modelo.Movimiento;

public class Movimientos extends JPanel {

	public Movimientos (JFrame frame, boolean tablaFiltrada, ArrayList<Movimiento> consultaFiltrada, boolean cliente, int dni) {
		setLayout(null);

		String[] nombresColumnas = { "Número movimiento", "Número cuenta", "Tipo movimiento", "Fecha movimiento", "Monto", "Fecha imputar", "Monto imputar", "Comentario" }; 
		
		DefaultTableModel modelo = new DefaultTableModel(nombresColumnas,0);
		
		MovimientoDAO mdao = new MovimientoDAO();
		CuentaDAO cdao = new CuentaDAO();
		ArrayList<Movimiento> resultado = new ArrayList<Movimiento>();
		if(cliente) {
			resultado = mdao.consultarConIdCuenta(cdao.consultarIdCuenta(dni));
		} else {
			if(tablaFiltrada) {
				resultado = consultaFiltrada;
			}
			else {
				resultado = mdao.consultar();						
			}			
		}
		
		
		
		for ( Movimiento m : resultado)
		{
	
		int nroMovimiento = m.getMovimiento();
		int idCuenta = m.getIdCuenta();
		String tipoMovimiento = m.getTipoMovimiento();
		LocalDate fechaMovimiento = m.getFechaMovimiento();
		float monto = m.getMonto();
		LocalDate fechaImputar = m.getFechaImputar();
		float montoImputar = m.getMontoImputar();
		String comentario = m.getComentario();
			
		Object [] registro = {nroMovimiento,idCuenta,tipoMovimiento,fechaMovimiento,monto,fechaImputar,montoImputar,comentario};
		
		modelo.addRow(registro);		
	       
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 28, 630, 230);
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
		
		JRadioButton rdbtnMovimiento = new JRadioButton("Movimiento");
		rdbtnMovimiento.setBounds(243, 343, 167, 23);
		add(rdbtnMovimiento);
		
		JLabel lblMovimientos = new JLabel("Movimientos");
		lblMovimientos.setBounds(276, 3, 109, 14);
		add(lblMovimientos);
		
		ActionListener listenerAtras = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cliente) {
					PanelPrincipal panel = new PanelPrincipal(frame, false);
					frame.setContentPane(panel);					
				} else {
					PanelPrincipal panel = new PanelPrincipal(frame, true);
					frame.setContentPane(panel);
				}
		        frame.validate();
			}
		};
		
		ActionListener listenerAceptar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean xCuenta = rdbtnNmeroDeCuenta.isSelected();
				boolean xFecha = rdbtnEntreFechas.isSelected();
				boolean xMonto = rdbtnEntreMontos.isSelected();
				boolean xMovimiento = rdbtnMovimiento.isSelected();
				if(!xCuenta && !xFecha && !xMonto && !xMovimiento) {
					JOptionPane.showMessageDialog(null,"Debe seleccionar al menos una opción.");

				} else {
					ConsultaMovimiento panel = new ConsultaMovimiento(frame, xCuenta, xFecha, xMonto, xMovimiento, cliente, dni);
			        frame.setContentPane(panel);
			        frame.validate();				
				}
			}
		};
		
		btnAtrs.addActionListener(listenerAtras);
		btnAceptar.addActionListener(listenerAceptar);
		
		if(cliente) {
			rdbtnNmeroDeCuenta.setVisible(false);
		}
	}

}
