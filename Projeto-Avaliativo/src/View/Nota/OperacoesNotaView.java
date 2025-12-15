package View.Nota;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.*;

import Controller.*;

public class OperacoesNotaView {

    public static JPanel criarPainel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JButton btnCadastrar = new JButton("Cadastrar Nota");
        JButton btnBuscar = new JButton("Buscar Nota");
        JButton btnBuscarTodos = new JButton("Buscar Todos");
        JButton btnAtualizar = new JButton("Atualizar Nota");
        JButton btnDeletar = new JButton("Deletar Nota");

        // Cadastrar
        btnCadastrar.addActionListener(e -> {
            NotaFormulario formulario = new NotaFormulario();
            Map<String, String> map = formulario.mostrarFormulario();

            NotaController controller = new NotaController();
            Boolean response = controller.criarNota(map);

            JOptionPane.showMessageDialog(
                null,
                response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
            );
        });

        // Buscar por identificador
        btnBuscar.addActionListener(e -> {
            BuscarNota busca = new BuscarNota();
            String codigo = busca.MostrarBuscador();

            NotaController controller = new NotaController();
            Map<String, String> nota = controller.buscarNota(codigo);

            if (nota != null) {
                List<Map<String, String>> lista = new ArrayList<>();
                lista.add(nota);

                NotaTabelaView tabela = new NotaTabelaView();
                tabela.mostrarTabela(lista);
            } else {
                JOptionPane.showMessageDialog(null, "Nota não encontrada");
            }
        });

        // Buscar todos
        btnBuscarTodos.addActionListener(e -> {
            NotaController controller = new NotaController();
            List<Map<String, String>> notas = controller.listarNota();

            if (notas != null && !notas.isEmpty()) {
                NotaTabelaView tabela = new NotaTabelaView();
                tabela.mostrarTabela(notas);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma nota encontrada");
            }
        });

        // Atualizar
        btnAtualizar.addActionListener(e -> {
            BuscarNota busca = new BuscarNota();
            String codigo = busca.MostrarBuscador();

            NotaController controller = new NotaController();
            Map<String, String> nota = controller.buscarNota(codigo);

            if (nota != null) {
                nota = UpdateNotaView.showUpdateForm(nota);
                Boolean response = controller.atualizarNota(nota);

                JOptionPane.showMessageDialog(
                    null,
                    response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
                );
            } else {
                JOptionPane.showMessageDialog(null, "Nota não encontrada");
            }
        });

        // Deletar
        btnDeletar.addActionListener(e -> {
            BuscarNota busca = new BuscarNota();
            String codigo = busca.MostrarBuscador();

            NotaController controller = new NotaController();
            Boolean response = controller.deletarNota(codigo);

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
