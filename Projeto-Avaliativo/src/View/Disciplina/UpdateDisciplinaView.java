package View.Disciplina; 

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class UpdateDisciplinaView {

    public static Map<String, String> showUpdateForm(Map<String, String> disciplina) {

        // Painel principal
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Campos
        JTextField txtId = new JTextField(disciplina.get("id"));
        txtId.setEditable(false);

        JTextField txtNome = new JTextField(disciplina.get("nome"));
        JTextField txtProfessor = new JTextField(disciplina.get("professor"));
        
        // Adicionando rótulos e campos
        panel.add(new JLabel("ID:"));
        panel.add(txtId);

        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);

        panel.add(new JLabel("Professor:"));
        panel.add(txtProfessor);


        // Exibe o formulário
        int opcao = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Editar Disciplina",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        // Se clicar em OK, devolve o Map atualizado
        if (opcao == JOptionPane.OK_OPTION) {
            Map<String, String> atualizado = new HashMap<>();

            atualizado.put("id", disciplina.get("id")); // mantém o id
            atualizado.put("nome", txtNome.getText());
            atualizado.put("Professor", txtProfessor.getText());
                       

            return atualizado;
        }

        return null;
    }
}
