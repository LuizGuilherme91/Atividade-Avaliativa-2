package View.Nota;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

public class NotaTabelaView {
    public void mostrarTabela(List<Map<String, String>> notas) {
        String[] colunas = {
                "Id", "Nota", "ID Diário", "Matrícula Aluno"
        };
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Map<String, String> dados : notas) {
            model.addRow(new Object[] {
                    dados.get("id"),
                    dados.get("nota"),
                    dados.get("id_diario"),
                    dados.get("matricula_aluno")
            });
        }

        JTable tabela = new JTable(model);
        tabela.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(tabela);

        JOptionPane.showMessageDialog(
                null,
                scrollPane,
                "Lista de Notas",
                JOptionPane.PLAIN_MESSAGE
        );
    }
}