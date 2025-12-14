package View;
import javax.swing.*;
import java.util.*;
 

public class AlunoFormulario {
    public Map<String, String> mostrarFormulario() {
        // Campos de entrada
        JTextField nome = new JTextField(15);
        JTextField endereco = new JTextField(15);
        JTextField telefone = new JTextField(15);
        JTextField email = new JTextField(15);
        JTextField matricula = new JTextField(15);
        JTextField nomePai = new JTextField(15);
        JTextField nomeMae = new JTextField(15);

        // Painel com layout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Adicionando os campos com rótulo
        Map<String, JTextField> map =  Map.of(
        	    "Nome:", nome,
        	    "Endereco:", endereco,
        	    "Telefone:", telefone,
        	    "Email:", email,
        	    "Matricula:", matricula,
        	    "Nome do pai:", nomePai,
        	    "Nome da mae:", nomeMae        	    
        	);
        
        for (Map.Entry<String, JTextField> entry : map.entrySet()) {
        	panel.add(new JLabel(entry.getKey()));
            panel.add(entry.getValue());
            panel.add(Box.createVerticalStrut(10));
        }
        

        // Mostrar o formulário
        int result = JOptionPane.showConfirmDialog(null, panel, 
                "Preencha os dados", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            Map<String, String> mapEntry =  Map.of(
            	    "nome", nome.getText(),
            	    "endereco",endereco.getText(),
            	    "telefone",telefone.getText(),
            	    "email", email.getText(),
            	    "matricula",matricula.getText(),
            	    "nomePai", nomePai.getText(),
            	    "nomeMae", nomeMae.getText() 	    
            	);
            return mapEntry;
            
        }else {
        	return null;
        }
    }
}
