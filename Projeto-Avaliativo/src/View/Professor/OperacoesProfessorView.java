package View.Professor;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.*;

import Controller.*;

public class OperacoesProfessorView {

    public static JPanel criarPainel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JButton btnCadastrar = new JButton("Cadastrar Professor");
        JButton btnBuscar = new JButton("Buscar Professor");
        JButton btnBuscarTodos = new JButton("Buscar Todos");
        JButton btnAtualizar = new JButton("Atualizar Professor");
        JButton btnDeletar = new JButton("Deletar Professor");

        // Cadastrar
        btnCadastrar.addActionListener(e -> {
            PofessorFormulario formulario = new PofessorFormulario();
            Map<String, String> map = formulario.mostrarFormulario();

            ProfessorController controller = new ProfessorController();
            Boolean response = controller.criarProfessor(map);

            JOptionPane.showMessageDialog(
                null,
                response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
            );
        });

        // Buscar
        btnBuscar.addActionListener(e -> {
            BuscarProfessor busca = new BuscarProfessor();
            String codigo = busca.MostrarBuscador();

            ProfessorController controller = new ProfessorController();
            Map<String, String> professor = controller.buscarProfessor(codigo);

            if (professor != null) {
                List<Map<String, String>> lista = new ArrayList<>();
                lista.add(professor);

                ProfessorTabelaView tabela = new ProfessorTabelaView();
                tabela.mostrarTabela(lista);
            } else {
                JOptionPane.showMessageDialog(null, "Professor não encontrado");
            }
        });

        // Buscar todos
        btnBuscarTodos.addActionListener(e -> {
            ProfessorController controller = new ProfessorController();
            List<Map<String, String>> professores = controller.listarProfessor();

            if (professores != null && !professores.isEmpty()) {
                ProfessorTabelaView tabela = new ProfessorTabelaView();
                tabela.mostrarTabela(professores);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum professor encontrado");
            }
        });

        // Atualizar
        btnAtualizar.addActionListener(e -> {
            BuscarProfessor busca = new BuscarProfessor();
            String codigo = busca.MostrarBuscador();

            ProfessorController controller = new ProfessorController();
            Map<String, String> professor = controller.buscarProfessor(codigo);

            if (professor != null) {
                professor = UpdateProfessorView.showUpdateForm(professor);
                ProfessorController controller2 = new ProfessorController();
                System.out.println(professor);
                Boolean response = controller2.atualizarProfessor(professor);

                JOptionPane.showMessageDialog(
                    null,
                    response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
                );
            } else {
                JOptionPane.showMessageDialog(null, "Professor não encontrado");
            }
        });

        // Deletar
        btnDeletar.addActionListener(e -> {
            BuscarProfessor busca = new BuscarProfessor();
            String codigo = busca.MostrarBuscador();

            ProfessorController controller = new ProfessorController();
            Boolean response = controller.deletarProfessor(codigo);

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
