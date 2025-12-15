package View.Periodo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

public class PeriodoTabelaView {

    public void mostrarTabela(List<Map<String, String>> periodo) {

        // Colunas da tabela
        String[] colunas = {
            "Id", "Nome",
        };

        // Modelo da tabela (não editável)
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Preenchendo a tabela a partir do Map
        for (Map<String, String> dados : periodo) {
            model.addRow(new Object[]{
                dados.get("id"),
                dados.get("nome"),                           
            });
        }

        JTable tabela = new JTable(model);
        tabela.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tabela);

        // Exibe em um diálogo
        JOptionPane.showMessageDialog(
            null,
            scrollPane,
            "Lista de periodo",
            JOptionPane.PLAIN_MESSAGE
        );
    }
}
