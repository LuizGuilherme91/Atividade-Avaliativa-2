package View.Aluno;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;

public class AlunoTabelaView {

    public void mostrarTabela(List<Map<String, String>> alunos) {

        // Colunas da tabela
        String[] colunas = {
            "Id", "Nome", "Endereço", "Telefone", "Email",
            "Matrícula", "Nome do Pai", "Nome da Mãe"
        };

        // Modelo da tabela (não editável)
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Preenchendo a tabela a partir do Map
        for (Map<String, String> dados : alunos) {
            model.addRow(new Object[]{
                dados.get("id"),
                dados.get("nome"),
                dados.get("endereco"),
                dados.get("telefone"),
                dados.get("email"),
                dados.get("matricula"),
                dados.get("nomePai"),
                dados.get("nomeMae")
            });
        }

        JTable tabela = new JTable(model);
        tabela.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(tabela);

        // Exibe em um diálogo
        JOptionPane.showMessageDialog(
            null,
            scrollPane,
            "Lista de Alunos",
            JOptionPane.PLAIN_MESSAGE
        );
    }
}
