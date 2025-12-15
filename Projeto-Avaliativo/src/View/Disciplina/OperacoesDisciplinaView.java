package View.Disciplina;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.util.*;

import Controller.*;

public class OperacoesDisciplinaView {

    public static JPanel criarPainel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JButton btnCadastrar = new JButton("Cadastrar Disciplina");
        JButton btnBuscar = new JButton("Buscar Disciplina");
        JButton btnBuscarTodos = new JButton("Buscar Todos");
        JButton btnAtualizar = new JButton("Atualizar Disciplina");
        JButton btnDeletar = new JButton("Deletar Disciplina");

        // Cadastrar
        btnCadastrar.addActionListener(e -> {
            DisciplinaFormulario formulario = new DisciplinaFormulario();
            Map<String, String> map = formulario.mostrarFormulario();

            DisciplinaController controller = new DisciplinaController();
            Boolean response = controller.criarDisciplina(map);

            JOptionPane.showMessageDialog(
                null,
                response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
            );
        });

        // Buscar por identificador
        btnBuscar.addActionListener(e -> {
            BuscarDisciplina busca = new BuscarDisciplina();
            String codigo = busca.MostrarBuscador();

            DisciplinaController controller = new DisciplinaController();
            Map<String, String> disciplina = controller.buscarDisciplina(codigo);

            if (disciplina != null) {
                List<Map<String, String>> lista = new ArrayList<>();
                lista.add(disciplina);

                DisciplinaTabelaView tabela = new DisciplinaTabelaView();
                tabela.mostrarTabela(lista);
            } else {
                JOptionPane.showMessageDialog(null, "Disciplina não encontrada");
            }
        });

        // Buscar todos
        btnBuscarTodos.addActionListener(e -> {
            DisciplinaController controller = new DisciplinaController();
            List<Map<String, String>> disciplinas = controller.listarDisciplina();

            if (disciplinas != null && !disciplinas.isEmpty()) {
                DisciplinaTabelaView tabela = new DisciplinaTabelaView();
                tabela.mostrarTabela(disciplinas);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma disciplina encontrada");
            }
        });

        // Atualizar
        btnAtualizar.addActionListener(e -> {
            BuscarDisciplina busca = new BuscarDisciplina();
            String codigo = busca.MostrarBuscador();

            DisciplinaController controller = new DisciplinaController();
            Map<String, String> disciplina = controller.buscarDisciplina(codigo);

            if (disciplina != null) {
                disciplina = UpdateDisciplinaView.showUpdateForm(disciplina);
                Boolean response = controller.atualizarDisciplina(disciplina);

                JOptionPane.showMessageDialog(
                    null,
                    response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
                );
            } else {
                JOptionPane.showMessageDialog(null, "Disciplina não encontrada");
            }
        });

        // Deletar
        btnDeletar.addActionListener(e -> {
            BuscarDisciplina busca = new BuscarDisciplina();
            String codigo = busca.MostrarBuscador();

            DisciplinaController controller = new DisciplinaController();
            Boolean response = controller.deletarDisciplina(codigo);

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
