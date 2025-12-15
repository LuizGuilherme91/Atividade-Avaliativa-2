package View.Turma;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
 

public class TurmaFormulario {
	
	
    public Map<String, String> mostrarFormulario() {
        // Campos de entrada
        JTextField nomeTurma = new JTextField(15);
        
        
        
        // Painel com layout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(new JLabel("Nome do turma:"));
        panel.add(nomeTurma);
        panel.add(Box.createVerticalStrut(10));
        
        
           
        
        

        // Mostrar o formulário
        int result = JOptionPane.showConfirmDialog(null, panel, 
                "Preencha os dados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            Map<String, String> mapEntry =  Map.of(
            	    "nome", nomeTurma.getText()           	  	    
            	    	    
            	);
            return mapEntry;
            
        }else {
        	return null;
        }
    }
}
