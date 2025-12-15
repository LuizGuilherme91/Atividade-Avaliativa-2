package View.Diario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

public class DiarioTabelaView {

    public void mostrarTabela(List<Map<String, String>> diarios) {

        // Colunas da tabela
        String[] colunas = {
            "Id", "Status", "ID Aluno", "ID Disciplina", "ID Período", "ID Turma"
        };

        // Modelo da tabela (não editável)
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Preenchendo a tabela a partir do Map
        for (Map<String, String> dados : diarios) {
            model.addRow(new Object[]{
                dados.get("id"),
                dados.get("status"),
                dados.get("idAluno"),
                dados.get("idDisciplina"),
                dados.get("idPeriodo"),
                dados.get("idTurma")
            });
        }

        JTable tabela = new JTable(model);
        tabela.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tabela);

        // Exibe em um diálogo
        JOptionPane.showMessageDialog(
            null,
            scrollPane,
            "Lista de Diários",
            JOptionPane.PLAIN_MESSAGE
        );
    }
}
