package View.Diario; 

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class UpdateDiarioView {

    public static Map<String, String> showUpdateForm(Map<String, String> diario) {

        // Painel principal
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Campos
        JTextField txtId = new JTextField(diario.get("id"));
        txtId.setEditable(false);

        JTextField txtStatus = new JTextField(diario.get("status"));
        JTextField txtIdAluno = new JTextField(diario.get("idAluno"));
        JTextField txtIdDisciplina = new JTextField(diario.get("idDisciplina"));
        JTextField txtIdPeriodo = new JTextField(diario.get("idPeriodo"));
        JTextField txtIdTurma = new JTextField(diario.get("idTurma"));

        // Adicionando rótulos e campos
        panel.add(new JLabel("ID:"));
        panel.add(txtId);

        panel.add(new JLabel("Status (true/false):"));
        panel.add(txtStatus);

        panel.add(new JLabel("ID Aluno:"));
        panel.add(txtIdAluno);

        panel.add(new JLabel("ID Disciplina:"));
        panel.add(txtIdDisciplina);

        panel.add(new JLabel("ID Período:"));
        panel.add(txtIdPeriodo);

        panel.add(new JLabel("ID Turma:"));
        panel.add(txtIdTurma);

        // Exibe o formulário
        int opcao = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Editar Diário",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );

        // Se clicar em OK, devolve o Map atualizado
        if (opcao == JOptionPane.OK_OPTION) {
            Map<String, String> atualizado = new HashMap<>();

            atualizado.put("id", diario.get("id")); // mantém o id
            atualizado.put("status", txtStatus.getText());
            atualizado.put("idAluno", txtIdAluno.getText());
            atualizado.put("idDisciplina", txtIdDisciplina.getText());
            atualizado.put("idPeriodo", txtIdPeriodo.getText());
            atualizado.put("idTurma", txtIdTurma.getText());

            return atualizado;
        }

        return null;
    }
}
