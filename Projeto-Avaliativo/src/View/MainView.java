package View;

import javax.swing.*;
import java.awt.*;

import View.Aluno.OperacoesAlunoView;
import View.Professor.OperacoesProfessorView;
import View.Turma.OperacoesTurmaView;
import View.Disciplina.OperacoesDisciplinaView;
import View.Diario.OperacoesDiarioView;
import View.Nota.OperacoesNotaView;
import View.Periodo.OperacoesPeriodoView;

public class MainView {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Sistema Acadêmico");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(0, 1, 12, 12));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

            JLabel titulo = new JLabel("Menu Principal", SwingConstants.CENTER);
            titulo.setFont(new Font("Arial", Font.BOLD, 20));

            JButton btnAluno = new JButton("Operações Aluno");
            JButton btnProfessor = new JButton("Operações Professor");
            JButton btnTurma = new JButton("Operações Turma");
            JButton btnDisciplina = new JButton("Operações Disciplina");
            JButton btnDiario = new JButton("Operações Diário");
            JButton btnNota = new JButton("Operações Nota");
            JButton btnPeriodo = new JButton("Operações Período");

            btnAluno.addActionListener(e -> abrirJanela("Aluno", OperacoesAlunoView.criarPainel()));
            btnProfessor.addActionListener(e -> abrirJanela("Professor", OperacoesProfessorView.criarPainel()));
            btnTurma.addActionListener(e -> abrirJanela("Turma", OperacoesTurmaView.criarPainel()));
            btnDisciplina.addActionListener(e -> abrirJanela("Disciplina", OperacoesDisciplinaView.criarPainel()));
            btnDiario.addActionListener(e -> abrirJanela("Diário", OperacoesDiarioView.criarPainel()));
            btnNota.addActionListener(e -> abrirJanela("Nota", OperacoesNotaView.criarPainel()));
            btnPeriodo.addActionListener(e -> abrirJanela("Período", OperacoesPeriodoView.criarPainel()));

            panel.add(titulo);
            panel.add(btnAluno);
            panel.add(btnProfessor);
            panel.add(btnTurma);
            panel.add(btnDisciplina);
            panel.add(btnDiario);
            panel.add(btnNota);
            panel.add(btnPeriodo);

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    private static void abrirJanela(String titulo, JPanel painel) {
        JFrame frame = new JFrame("Operações de " + titulo);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 450);
        frame.setLocationRelativeTo(null);
        frame.add(painel);
        frame.setVisible(true);
    }
}
