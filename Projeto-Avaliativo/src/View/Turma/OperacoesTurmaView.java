package View.Turma;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.*;

import Controller.*;

public class OperacoesTurmaView {

    public static JPanel criarPainel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JButton btnCadastrar = new JButton("Cadastrar Turma");
        JButton btnBuscar = new JButton("Buscar Turma");
        JButton btnBuscarTodos = new JButton("Buscar Todos");
        JButton btnAtualizar = new JButton("Atualizar Turma");
        JButton btnDeletar = new JButton("Deletar Turma");

        // Cadastrar
        btnCadastrar.addActionListener(e -> {
            TurmaFormulario formulario = new TurmaFormulario();
            Map<String, String> map = formulario.mostrarFormulario();

            TurmaController controller = new TurmaController();
            Boolean response = controller.criarTurma(map);

            JOptionPane.showMessageDialog(
                null,
                response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
            );
        });

        // Buscar
        btnBuscar.addActionListener(e -> {
            BuscarTurma busca = new BuscarTurma();
            String codigo = busca.MostrarBuscador();

            TurmaController controller = new TurmaController();
            Map<String, String> turma = controller.buscarTurma(codigo);

            if (turma != null) {
                List<Map<String, String>> lista = new ArrayList<>();
                lista.add(turma);

                TurmaTabelaView tabela = new TurmaTabelaView();
                tabela.mostrarTabela(lista);
            } else {
                JOptionPane.showMessageDialog(null, "Turma não encontrada");
            }
        });

        // Buscar todos
        btnBuscarTodos.addActionListener(e -> {
            TurmaController controller = new TurmaController();
            List<Map<String, String>> turmas = controller.listarTurma();

            if (turmas != null && !turmas.isEmpty()) {
                TurmaTabelaView tabela = new TurmaTabelaView();
                tabela.mostrarTabela(turmas);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma turma encontrada");
            }
        });

        // Atualizar
        btnAtualizar.addActionListener(e -> {
            BuscarTurma busca = new BuscarTurma();
            String codigo = busca.MostrarBuscador();

            TurmaController controller = new TurmaController();
            Map<String, String> turma = controller.buscarTurma(codigo);

            if (turma != null) {
                turma = UpdateTurmaView.showUpdateForm(turma);
                TurmaController controller2 = new TurmaController();
                Boolean response = controller2.atualizarTurma(turma);

                JOptionPane.showMessageDialog(
                    null,
                    response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
                );
            } else {
                JOptionPane.showMessageDialog(null, "Turma não encontrada");
            }
        });

        // Deletar
        btnDeletar.addActionListener(e -> {
            BuscarTurma busca = new BuscarTurma();
            String codigo = busca.MostrarBuscador();

            TurmaController controller = new TurmaController();
            Boolean response = controller.deletarTurma(codigo);

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
