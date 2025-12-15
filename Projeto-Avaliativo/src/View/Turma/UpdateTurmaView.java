package View.Turma; 

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class UpdateTurmaView {

    public static Map<String, String> showUpdateForm(Map<String, String> turma) {

        // Painel principal
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Campos
        JTextField txtId = new JTextField(turma.get("id"));
        txtId.setEditable(false);
        JTextField txtNome = new JTextField(turma.get("nome"));
        
        
        // Adicionando rótulos e campos
        panel.add(new JLabel("ID:"));
        panel.add(txtId);

        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);



        // Exibe o formulário
        int opcao = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Editar Turma",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        // Se clicar em OK, devolve o Map atualizado
        if (opcao == JOptionPane.OK_OPTION) {
            Map<String, String> atualizado = new HashMap<>();

            atualizado.put("id", turma.get("id")); // mantém o id
            atualizado.put("nome", txtNome.getText());
            
                       

            return atualizado;
        }

        return null;
    }
}
