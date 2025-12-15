package View.Periodo;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
 

public class PeriodoFormulario {
	
	
    public Map<String, String> mostrarFormulario() {
        // Campos de entrada
        JTextField nomePeriodo = new JTextField(15);
        
        
        
        // Painel com layout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(new JLabel("Nome do periodo:"));
        panel.add(nomePeriodo);
        panel.add(Box.createVerticalStrut(10));
        
        
           
        
        

        // Mostrar o formulário
        int result = JOptionPane.showConfirmDialog(null, panel, 
                "Preencha os dados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            Map<String, String> mapEntry =  Map.of(
            	    "nome", nomePeriodo.getText()           	  	    
            	    	    
            	);
            return mapEntry;
            
        }else {
        	return null;
        }
    }
}
