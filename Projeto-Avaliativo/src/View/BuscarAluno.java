package View;
import java.util.Map;

import javax.swing.*;


public class BuscarAluno {
	public String MostrarBuscador() {
		JTextField id = new JTextField(15);
		
		JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
			panel.add(new JLabel("Insira o ID a ser buscado"));
	        panel.add(id);
	        panel.add(Box.createVerticalStrut(10));
	        
	        
	        int result = JOptionPane.showConfirmDialog(null, panel, 
	                "Preencha os dados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	        
	        
	        if (result == JOptionPane.OK_OPTION) {	        	
	        	return id.getText();
	        	
	        }
		return null;
	}
}
