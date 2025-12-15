package View.Nota;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class UpdateNotaView {

    public static Map<String, String> showUpdateForm(Map<String, String> nota) {

        // Painel principal
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Campos
        JTextField txtId = new JTextField(nota.get("id"));
        txtId.setEditable(false);

        JTextField txtNota = new JTextField(nota.get("nota"));
        JTextField txtIdDiario = new JTextField(nota.get("id_diario"));
        JTextField txtMatriculaAluno = new JTextField(nota.get("matricula_aluno"));

        // Adicionando rótulos e campos
        panel.add(new JLabel("ID:"));
        panel.add(txtId);

        panel.add(new JLabel("Nota:"));
        panel.add(txtNota);

        panel.add(new JLabel("ID Diário:"));
        panel.add(txtIdDiario);

        panel.add(new JLabel("Matrícula do Aluno:"));
        panel.add(txtMatriculaAluno);

        // Exibe o formulário
        int opcao = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Editar Nota",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        // Se clicar em OK, devolve o Map atualizado
        if (opcao == JOptionPane.OK_OPTION) {
            Map<String, String> atualizado = new HashMap<>();

            atualizado.put("id", nota.get("id")); // mantém o id
            atualizado.put("nota", txtNota.getText());
            atualizado.put("id_diario", txtIdDiario.getText());
            atualizado.put("matricula_aluno", txtMatriculaAluno.getText());

            return atualizado;
        }

        return null;
    }
}
