package View;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import Controller.*;
public class MainView {

    public static void main(String[] args) {
        // Criar a janela principal
        JFrame frame = new JFrame("Tela Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);

        // Botão para abrir formulário
        JButton botaoAbrirFormulario = new JButton("Cadastrar Aluno");

        // Ação do botão
        botaoAbrirFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlunoFormulario formulario = new AlunoFormulario();
                Map<String, String> map = formulario.mostrarFormulario();
                AlunoController controller = new AlunoController();
                Boolean response = controller.criarAluno(map);
                
                
                if(response) {
                	JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
                	
                }else {
                	JOptionPane.showMessageDialog(null, "Operação falhou verifique");
                }
            }
        });

        // Adicionar botão ao painel
        JPanel panel = new JPanel();
        panel.add(botaoAbrirFormulario);
        frame.add(panel);
        frame.setVisible(true);
    }
}