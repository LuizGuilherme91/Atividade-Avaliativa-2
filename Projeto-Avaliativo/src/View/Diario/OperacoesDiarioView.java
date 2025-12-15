package View.Diario;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.*;

import Controller.*;

public class OperacoesDiarioView {

    public static JPanel criarPainel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JButton btnCadastrar = new JButton("Cadastrar Diário");
        JButton btnBuscar = new JButton("Buscar Diário");
        JButton btnBuscarTodos = new JButton("Buscar Todos");
        JButton btnAtualizar = new JButton("Atualizar Diário");
        JButton btnDeletar = new JButton("Deletar Diário");

        // Cadastrar
        btnCadastrar.addActionListener(e -> {
            DiarioFormulario formulario = new DiarioFormulario();
            Map<String, String> map = formulario.mostrarFormulario();

            DiarioController controller = new DiarioController();
            Boolean response = controller.criarDiario(map);

            JOptionPane.showMessageDialog(
                null,
                response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
            );
        });

        // Buscar por identificador
        btnBuscar.addActionListener(e -> {
            BuscarDiario busca = new BuscarDiario();
            String codigo = busca.MostrarBuscador();

            DiarioController controller = new DiarioController();
            Map<String, String> diario = controller.buscarDiario(codigo);

            if (diario != null) {
                List<Map<String, String>> lista = new ArrayList<>();
                lista.add(diario);

                DiarioTabelaView tabela = new DiarioTabelaView();
                tabela.mostrarTabela(lista);
            } else {
                JOptionPane.showMessageDialog(null, "Diário não encontrado");
            }
        });

        // Buscar todos
        btnBuscarTodos.addActionListener(e -> {
            DiarioController controller = new DiarioController();
            List<Map<String, String>> diarios = controller.listarDiario();

            if (diarios != null && !diarios.isEmpty()) {
                DiarioTabelaView tabela = new DiarioTabelaView();
                tabela.mostrarTabela(diarios);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum diário encontrado");
            }
        });

        // Atualizar
        btnAtualizar.addActionListener(e -> {
            BuscarDiario busca = new BuscarDiario();
            String codigo = busca.MostrarBuscador();

            DiarioController controller = new DiarioController();
            Map<String, String> diario = controller.buscarDiario(codigo);

            if (diario != null) {
                diario = UpdateDiarioView.showUpdateForm(diario);
                Boolean response = controller.atualizarDiario(diario);

                JOptionPane.showMessageDialog(
                    null,
                    response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
                );
            } else {
                JOptionPane.showMessageDialog(null, "Diário não encontrado");
            }
        });

        // Deletar
        btnDeletar.addActionListener(e -> {
            BuscarDiario busca = new BuscarDiario();
            String codigo = busca.MostrarBuscador();

            DiarioController controller = new DiarioController();
            Boolean response = controller.deletarDiario(codigo);

            JOptionPane.showMessageDialog(
                null,
                response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
            );
        });

        panel.add(btnCadastrar);
        panel.add(btnBuscar);
        panel.add(btnBuscarTodos);
        panel.add(btnAtualizar);
        panel.add(btnDeletar);

        return panel;
    }
}
