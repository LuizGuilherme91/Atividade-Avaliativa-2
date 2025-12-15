package View.Periodo;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.*;

import Controller.*;

public class OperacoesPeriodoView {

    public static JPanel criarPainel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JButton btnCadastrar = new JButton("Cadastrar Período");
        JButton btnBuscar = new JButton("Buscar Período");
        JButton btnBuscarTodos = new JButton("Buscar Todos");
        JButton btnAtualizar = new JButton("Atualizar Período");
        JButton btnDeletar = new JButton("Deletar Período");

        // Cadastrar
        btnCadastrar.addActionListener(e -> {
            PeriodoFormulario formulario = new PeriodoFormulario();
            Map<String, String> map = formulario.mostrarFormulario();

            PeriodoController controller = new PeriodoController();
            Boolean response = controller.criarPeriodo(map);

            JOptionPane.showMessageDialog(
                null,
                response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
            );
        });

        // Buscar
        btnBuscar.addActionListener(e -> {
            BuscarPerioddo busca = new BuscarPerioddo();
            String codigo = busca.MostrarBuscador();

            PeriodoController controller = new PeriodoController();
            Map<String, String> periodo = controller.buscarPeriodo(codigo);

            if (periodo != null) {
                List<Map<String, String>> lista = new ArrayList<>();
                lista.add(periodo);

                PeriodoTabelaView tabela = new PeriodoTabelaView();
                tabela.mostrarTabela(lista);
            } else {
                JOptionPane.showMessageDialog(null, "Período não encontrado");
            }
        });

        // Buscar todos
        btnBuscarTodos.addActionListener(e -> {
            PeriodoController controller = new PeriodoController();
            List<Map<String, String>> periodos = controller.listarPeriodo();

            if (periodos != null && !periodos.isEmpty()) {
                PeriodoTabelaView tabela = new PeriodoTabelaView();
                tabela.mostrarTabela(periodos);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum período encontrado");
            }
        });

        // Atualizar
        btnAtualizar.addActionListener(e -> {
            BuscarPerioddo busca = new BuscarPerioddo();
            String codigo = busca.MostrarBuscador();

            PeriodoController controller = new PeriodoController();
            Map<String, String> periodo = controller.buscarPeriodo(codigo);

            if (periodo != null) {
                periodo = UpdatePeriodoView.showUpdateForm(periodo);
                Boolean response = controller.atualizarPeriodo(periodo);

                JOptionPane.showMessageDialog(
                    null,
                    response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
                );
            } else {
                JOptionPane.showMessageDialog(null, "Período não encontrado");
            }
        });

        // Deletar
        btnDeletar.addActionListener(e -> {
            BuscarPerioddo busca = new BuscarPerioddo();
            String codigo = busca.MostrarBuscador();

            PeriodoController controller = new PeriodoController();
            Boolean response = controller.deletarPeriodo(codigo);

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
