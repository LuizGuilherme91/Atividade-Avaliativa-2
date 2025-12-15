package View.Diario;

import javax.swing.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DiarioFormulario {

    public Map<String, String> mostrarFormulario() {
        // Campos de entrada
        JTextField idAluno = new JTextField(15);
        JTextField idDisciplina = new JTextField(15);
        JTextField idPeriodo = new JTextField(15);
        JTextField idTurma = new JTextField(15);
        JTextField status = new JTextField(15);

        // Painel com layout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("ID do Aluno:"));
        panel.add(idAluno);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("ID da Disciplina:"));
        panel.add(idDisciplina);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("ID do Período:"));
        panel.add(idPeriodo);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("ID da Turma:"));
        panel.add(idTurma);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Status (true/false):"));
        panel.add(status);
        panel.add(Box.createVerticalStrut(10));

        // Mostrar o formulário
        int result = JOptionPane.showConfirmDialog(null, panel, 
                "Preencha os dados do Diário", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Map<String, String> mapEntry = Map.of(
                    "idAluno", idAluno.getText(),
                    "idDisciplina", idDisciplina.getText(),
                    "idPeriodo", idPeriodo.getText(),
                    "idTurma", idTurma.getText(),
                    "status", status.getText()
            );
            return mapEntry;
        } else {
            return null;
        }
    }
}
