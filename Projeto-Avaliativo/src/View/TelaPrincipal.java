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

// --- MOCK DE TELA FALTANTE (Para compilar se não estiver no mesmo projeto) ---
// Se você estiver usando o código completo que enviei anteriormente, pode remover esta seção
class TelaAluno extends JFrame {
    public TelaAluno(ControllerAluno controller) {
        setTitle("GERENCIAR ALUNOS (MOCK)");
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(45, 45, 45));
        JLabel lbl = new JLabel("Tela Aluno (MOCK)", SwingConstants.CENTER);
        lbl.setForeground(Color.WHITE);
        getContentPane().add(lbl);
    }
}
// ------------------------------------

public class TelaPrincipal extends JFrame {

    // --- Constantes de Estilo ---
    private static final Color COR_FUNDO = new Color(45, 45, 45); // Cinza escuro
    private static final Color COR_BOTAO = new Color(220, 220, 220); // Cinza BEM claro
    private static final Color COR_TEXTO_BOTAO = new Color(30, 30, 30); // Texto escuro
    private static final Font FONTE_TITULO = new Font("Segoe UI", Font.BOLD, 28);
    private static final Font FONTE_BOTAO = new Font("Segoe UI", Font.BOLD, 16);
    private static final Color COR_TEXTO_TITULO = Color.WHITE; // Branco para o título

    // Dependência do Controller
    private final ControllerAluno controllerAluno;

    // Construtor que recebe a dependência
    public TelaPrincipal(ControllerAluno controllerAluno) {
        
        this.controllerAluno = controllerAluno; // Armazena o Controller

        setTitle("AtividadeAvaliativa - Menu");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- 1. Painel Principal ---
        Container painelPrincipal = getContentPane();
        painelPrincipal.setBackground(COR_FUNDO);
        painelPrincipal.setLayout(new BorderLayout(15, 15));

        // --- 2. Título ---
        JLabel titulo = new JLabel("Menu Principal", SwingConstants.CENTER);
        titulo.setFont(FONTE_TITULO);
        titulo.setForeground(COR_TEXTO_TITULO);
        titulo.setBorder(new EmptyBorder(30, 20, 20, 20));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        // --- 3. Painel de Botões (Centralizado) ---
        JPanel painelCentral = new JPanel(new GridBagLayout());
        painelCentral.setBackground(COR_FUNDO);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        
        // CORREÇÃO 1: Instancia o botão antes de usá-lo
        JButton btnCadastroAluno = new JButton("Gerenciar Alunos (CRUD)"); 
        
        // CORREÇÃO 2: Estiliza o botão instanciado
        styleButton(btnCadastroAluno);

        painelCentral.add(btnCadastroAluno, gbc);

        // Adiciona um separador vertical para centralizar
        gbc.gridy++;
        gbc.weighty = 1.0;
        painelCentral.add(Box.createVerticalGlue(), gbc);

        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // --- 4. Ações dos Botões ---
        // CORREÇÃO 3: Passa o ControllerAluno para a TelaAluno
        btnCadastroAluno.addActionListener(e -> {
            TelaAluno telaAluno = new TelaAluno(this.controllerAluno);
            telaAluno.setVisible(true);
        });

    }

    private void styleButton(JButton button) {
        button.setFont(FONTE_BOTAO);
        button.setBackground(COR_BOTAO);
        button.setForeground(COR_TEXTO_BOTAO); // Define o texto para escuro
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(280, 55));

        // Borda cinza médio para combinar com o botão claro no fundo escuro
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 150, 150), 1), 
            new EmptyBorder(12, 25, 12, 25)
        ));
    }
    
    // É necessário ter uma classe Main ou no mínimo um método main para iniciar a aplicação, 
    // passando o controller.

}