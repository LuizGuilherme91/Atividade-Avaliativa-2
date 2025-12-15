package View.Disciplina;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
 

public class PofessorFormulario {
	
	
    public Map<String, String> mostrarFormulario() {
        // Campos de entrada
        JTextField nomeDiscplina = new JTextField(15);
        JTextField matriculaProfessor = new JTextField(15);
        
        
        // Painel com layout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(new JLabel("Nome:"));
        panel.add(nomeDiscplina);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Matricula do professor:"));
        panel.add(matriculaProfessor);
        panel.add(Box.createVerticalStrut(10));
        
           
        
        

        // Mostrar o formulário
        int result = JOptionPane.showConfirmDialog(null, panel, 
                "Preencha os dados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            Map<String, String> mapEntry =  Map.of(
            	    "nome", nomeDiscplina.getText(),
            	    "professor",matriculaProfessor.getText()   	    
            	    	    
            	);
            return mapEntry;
            
        }else {
        	return null;
        }
    }
}
