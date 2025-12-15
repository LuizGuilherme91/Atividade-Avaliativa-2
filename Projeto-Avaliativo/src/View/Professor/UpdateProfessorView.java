package View.Professor;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class UpdateProfessorView {

    public static Map<String, String> showUpdateForm(Map<String, String> professor) {

        // Painel principal
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Campos
        JTextField txtId = new JTextField(professor.get("id"));
        txtId.setEditable(false);

        JTextField txtNome = new JTextField(professor.get("nome"));
        JTextField txtEndereco = new JTextField(professor.get("endereco"));
        JTextField txtTelefone = new JTextField(professor.get("telefone"));
        JTextField txtEmail = new JTextField(professor.get("email"));
        JTextField txtMatricula = new JTextField(professor.get("matricula"));

        // Adicionando rótulos e campos
        panel.add(new JLabel("ID:"));
        panel.add(txtId);

        panel.add(new JLabel("Nome:"));
        panel.add(txtNome);

        panel.add(new JLabel("Endereço:"));
        panel.add(txtEndereco);

        panel.add(new JLabel("Telefone:"));
        panel.add(txtTelefone);

        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);

        panel.add(new JLabel("Matrícula:"));
        panel.add(txtMatricula);

        // Exibe o formulário
        int opcao = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Editar Professor",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        // Se clicar em OK, devolve o Map atualizado
        if (opcao == JOptionPane.OK_OPTION) {
            Map<String, String> atualizado = new HashMap<>();

            atualizado.put("id", professor.get("id")); // mantém o id
            atualizado.put("nome", txtNome.getText());
            atualizado.put("endereco", txtEndereco.getText());
            atualizado.put("telefone", txtTelefone.getText());
            atualizado.put("email", txtEmail.getText());
            atualizado.put("matricula", txtMatricula.getText());            

            return atualizado;
        }

        return null;
    }
}
