package View;

import Controller.ControllerAluno;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;

// --- MOCKS DE TELAS FALTANTES ---
// (Necessário para compilar)
class TelaPrioridade extends JFrame {
    public TelaPrioridade() {
        setTitle("GERENCIAR PRIORIDADES");
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(45, 45, 45));
        JLabel lbl = new JLabel("Tela Prioridade (MOCK)", SwingConstants.CENTER);
        lbl.setForeground(Color.WHITE);
        getContentPane().add(lbl);
    }
}

class TelaResponsavel extends JFrame {
    public TelaResponsavel() {
        setTitle("GERENCIAR RESPONSÁVEIS");
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(45, 45, 45));
        JLabel lbl = new JLabel("Tela Responsável (MOCK)", SwingConstants.CENTER);
        lbl.setForeground(Color.WHITE);
        getContentPane().add(lbl);
    }
}

class TelaListaTarefas extends JFrame {
    public TelaListaTarefas() {
        setTitle("GERENCIAR LISTA DE TAREFAS");
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(45, 45, 45));
        JLabel lbl = new JLabel("Tela Lista de Tarefas (MOCK)", SwingConstants.CENTER);
        lbl.setForeground(Color.WHITE);
        getContentPane().add(lbl);
    }
}
// ------------------------------------

public class TelaPrincipal extends JFrame {

    // --- Constantes de Estilo ---
    private static final Color COR_FUNDO = new Color(45, 45, 45); 
    private static final Color COR_BOTAO = new Color(220, 220, 220);
    private static final Color COR_TEXTO_BOTAO = new Color(30, 30, 30);
    private static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font FONTE_BOTAO = new Font("Segoe UI", Font.BOLD, 16);
    private static final Color COR_TEXTO_TITULO = Color.WHITE;

    private final ControllerAluno controllerAluno;
    
    public TelaPrincipal(ControllerAluno controllerAluno) {
        
        this.controllerAluno = controllerAluno;

        setTitle("AtividadeAvaliativa - Menu");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ... layout e estilo (o mesmo código que você já tinha) ...

        Container painelPrincipal = getContentPane();
        painelPrincipal.setBackground(COR_FUNDO);
        painelPrincipal.setLayout(new BorderLayout(15, 15));

        JLabel titulo = new JLabel("Menu Principal", SwingConstants.CENTER);
        titulo.setFont(FONTE_TITULO);
        titulo.setForeground(COR_TEXTO_TITULO);
        titulo.setBorder(new EmptyBorder(30, 20, 20, 20));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setBackground(COR_FUNDO);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        
        // Instanciação dos botões
        JButton btnCadastroAluno = new JButton("Gerenciar Alunos (CRUD)"); 
        JButton btnPrioridade = new JButton("Gerenciar Prioridades");
        JButton btnResponsavel = new JButton("Gerenciar Responsáveis");
        JButton btnListaTarefas = new JButton("Gerenciar Lista de Tarefas");

        styleButton(btnCadastroAluno);
        styleButton(btnPrioridade);
        styleButton(btnResponsavel);
        styleButton(btnListaTarefas);

        // Adiciona os botões
        painelCentral.add(btnCadastroAluno, gbc); 

        gbc.gridy++;
        painelCentral.add(btnPrioridade, gbc);
        
        gbc.gridy++;
        painelCentral.add(btnResponsavel, gbc);
        
        gbc.gridy++;
        painelCentral.add(btnListaTarefas, gbc);

        gbc.gridy++;
        gbc.weighty = 1.0;
        painelCentral.add(Box.createVerticalGlue(), gbc);

        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // --- Ações dos Botões ---
        
        // Ação principal, passando o controller
        btnCadastroAluno.addActionListener(e -> {
            // Garante que o Controller não é null
            if (this.controllerAluno != null) { 
                TelaAluno telaAluno = new TelaAluno(this.controllerAluno); 
                telaAluno.setVisible(true);
            } else {
                System.err.println("Erro: ControllerAluno não foi inicializado.");
            }
        });
        
        btnPrioridade.addActionListener(e -> new TelaPrioridade().setVisible(true));
        btnResponsavel.addActionListener(e -> new TelaResponsavel().setVisible(true));
        btnListaTarefas.addActionListener(e -> new TelaListaTarefas().setVisible(true));
    }

    private void styleButton(JButton button) {
        button.setFont(FONTE_BOTAO);
        button.setBackground(COR_BOTAO);
        button.setForeground(COR_TEXTO_BOTAO);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(280, 55));

        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 150, 150), 1), 
            new EmptyBorder(12, 25, 12, 25)
        ));
    }
}