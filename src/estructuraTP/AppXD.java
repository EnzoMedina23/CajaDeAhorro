package estructuraTP;

import javax.swing.JFrame;

import estructuraTP.vista.PanelInicio;

public class AppXD {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 650, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PanelInicio panel = new PanelInicio(frame);
		frame.setContentPane(panel);
		frame.setVisible(true);

	}
	
}
