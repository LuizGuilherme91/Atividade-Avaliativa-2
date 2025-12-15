package View.Aluno;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import Controller.*;


public class OperacoesAlunoView {

    public static JPanel criarPainel() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1, 10, 10));

        JButton botaoCadastrar = new JButton("Cadastrar Aluno");
        JButton botaoBuscar = new JButton("Buscar Aluno");
        JButton botaoBuscarTodos = new JButton("Buscar Todos");
        JButton botaoAtualizar = new JButton("Atualizar Aluno");
        JButton botaoDeletar = new JButton("Deletar Aluno");

        // Cadastrar
        botaoCadastrar.addActionListener(e -> {
            AlunoFormulario formulario = new AlunoFormulario();
            Map<String, String> map = formulario.mostrarFormulario();

            AlunoController controller = new AlunoController();
            Boolean response = controller.criarAluno(map);

            JOptionPane.showMessageDialog(
                null,
                response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
            );
        });

        // Buscar por matrícula
        botaoBuscar.addActionListener(e -> {
            BuscarAluno busca = new BuscarAluno();
            String matricula = busca.MostrarBuscador();

            AlunoController controller = new AlunoController();
            Map<String, String> aluno = controller.buscarAluno(matricula);

            if (aluno != null) {
                List<Map<String, String>> lista = new ArrayList<>();
                lista.add(aluno);

                AlunoTabelaView tabela = new AlunoTabelaView();
                tabela.mostrarTabela(lista);
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado");
            }
        });

        // Buscar todos
        botaoBuscarTodos.addActionListener(e -> {
            AlunoController controller = new AlunoController();
            List<Map<String, String>> alunos = controller.listarAlunos();

            if (alunos != null && !alunos.isEmpty()) {
                AlunoTabelaView tabela = new AlunoTabelaView();
                tabela.mostrarTabela(alunos);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum aluno encontrado");
            }
        });

        // Atualizar
        botaoAtualizar.addActionListener(e -> {
            BuscarAluno busca = new BuscarAluno();
            String matricula = busca.MostrarBuscador();

            AlunoController controller = new AlunoController();
            Map<String, String> aluno = controller.buscarAluno(matricula);

            if (aluno != null) {
            	AlunoController controller2 = new AlunoController();
                aluno = UpdateAlunoView.showUpdateForm(aluno);
                Boolean response = controller2.atualizarAluno(aluno);

                JOptionPane.showMessageDialog(
                    null,
                    response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
                );
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado");
            }
        });

        // Deletar
        botaoDeletar.addActionListener(e -> {
            BuscarAluno busca = new BuscarAluno();
            String matricula = busca.MostrarBuscador();

            AlunoController controller = new AlunoController();
            Boolean response = controller.deletarAluno(matricula);

            JOptionPane.showMessageDialog(
                null,
                response ? "Operação realizada com sucesso!" : "Operação falhou, verifique"
            );
        });

        panel.add(botaoCadastrar);
        panel.add(botaoBuscar);
        panel.add(botaoBuscarTodos);
        panel.add(botaoAtualizar);
        panel.add(botaoDeletar);

        return panel;
    }
}
