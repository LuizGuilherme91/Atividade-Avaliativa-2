package View.Aluno;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class UpdateAlunoView {

    public static Map<String, String> showUpdateForm(Map<String, String> aluno) {

        // Painel principal
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Campos
        JTextField txtId = new JTextField(aluno.get("id"));
        txtId.setEditable(false);

        JTextField txtNome = new JTextField(aluno.get("nome"));
        JTextField txtEndereco = new JTextField(aluno.get("endereco"));
        JTextField txtTelefone = new JTextField(aluno.get("telefone"));
        JTextField txtEmail = new JTextField(aluno.get("email"));
        JTextField txtMatricula = new JTextField(aluno.get("matricula"));
        JTextField txtNomePai = new JTextField(aluno.get("nomePai"));
        JTextField txtNomeMae = new JTextField(aluno.get("nomeMae"));

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

        panel.add(new JLabel("Nome do Pai:"));
        panel.add(txtNomePai);

        panel.add(new JLabel("Nome da Mãe:"));
        panel.add(txtNomeMae);

        // Exibe o formulário
        int opcao = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Editar Aluno",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        // Se clicar em OK, devolve o Map atualizado
        if (opcao == JOptionPane.OK_OPTION) {
            Map<String, String> atualizado = new HashMap<>();

            atualizado.put("id", aluno.get("id")); // mantém o id
            atualizado.put("nome", txtNome.getText());
            atualizado.put("endereco", txtEndereco.getText());
            atualizado.put("telefone", txtTelefone.getText());
            atualizado.put("email", txtEmail.getText());
            atualizado.put("matricula", txtMatricula.getText());
            atualizado.put("nomePai", txtNomePai.getText());
            atualizado.put("nomeMae", txtNomeMae.getText());

            return atualizado;
        }

        return null;
    }
}
