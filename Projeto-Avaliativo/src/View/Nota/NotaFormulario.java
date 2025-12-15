package View.Nota;

import javax.swing.*;
import java.util.Map;

public class NotaFormulario {

    public Map<String, String> mostrarFormulario() {
        // Campos de entrada
        JTextField nota = new JTextField(15);
        JTextField idDiario = new JTextField(15);
        JTextField matriculaAluno = new JTextField(15);

        // Painel com layout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nota:"));
        panel.add(nota);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("ID do Diário:"));
        panel.add(idDiario);
        panel.add(Box.createVerticalStrut(10));

        panel.add(new JLabel("Matrícula do Aluno:"));
        panel.add(matriculaAluno);
        panel.add(Box.createVerticalStrut(10));

        // Mostrar o formulário
        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Preencha os dados da Nota",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            Map<String, String> mapEntry = Map.of(
                    "nota", nota.getText(),
                    "id_diario", idDiario.getText(),
                    "matricula_aluno", matriculaAluno.getText()
            );
            return mapEntry;
        } else {
            return null;
        }
    }
}
