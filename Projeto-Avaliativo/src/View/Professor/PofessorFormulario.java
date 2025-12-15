package View.Professor;
import javax.swing.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
 

public class PofessorFormulario {
	
	
    public Map<String, String> mostrarFormulario() {
        // Campos de entrada
        JTextField nome = new JTextField(15);
        JTextField endereco = new JTextField(15);
        JTextField telefone = new JTextField(15);
        JTextField email = new JTextField(15);
        JTextField matricula = new JTextField(15);
        
        // Painel com layout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(new JLabel("Nome:"));
        panel.add(nome);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Endereco:"));
        panel.add(endereco);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Telefone:"));
        panel.add(telefone);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Email:"));
        panel.add(email);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JLabel("Matricula:"));
        panel.add(matricula);
        
           
        
        

        // Mostrar o formulário
        int result = JOptionPane.showConfirmDialog(null, panel, 
                "Preencha os dados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            Map<String, String> mapEntry =  Map.of(
            	    "nome", nome.getText(),
            	    "endereco",endereco.getText(),
            	    "telefone",telefone.getText(),
            	    "email", email.getText(),
            	    "matricula",matricula.getText()
            	    	    
            	);
            return mapEntry;
            
        }else {
        	return null;
        }
    }
}
