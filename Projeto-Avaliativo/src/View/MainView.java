package View;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import Controller.*;
import Models.Aluno;
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
        
        
        JButton botaoLerFormulario = new JButton("Buscar aluno");
        
        botaoLerFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	BuscarAluno busca = new BuscarAluno();
            	String matriculaBuscada = busca.MostrarBuscador();
            	AlunoController controller = new AlunoController();
            	Map<String, String> mapAluno = controller.buscarAluno(matriculaBuscada);
            	if (mapAluno != null) { 
            		List<Map<String, String>> listAluno = new ArrayList<>();            		
            		listAluno.add(mapAluno);
            		
            		AlunoTabelaView tabela = new AlunoTabelaView();
            		tabela.mostrarTabela(listAluno);
            		
            	
            		
            	    
            	} else {
            	    System.out.println("Aluno não encontrado");
            	}
            }
        });
        
        
        
        JButton botaoLerTudo = new JButton("Buscar Todos");
        
        botaoLerTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            	
        
            	AlunoController controller = new AlunoController();
            	List<Map<String, String>> listAluno = controller.listarAlunos();
            	if (listAluno != null ) {         		
            		
            		AlunoTabelaView tabela = new AlunoTabelaView();
            		tabela.mostrarTabela(listAluno); 		
            	
            		
            	    
            	} else {
            	    System.out.println("Aluno não encontrado");
            	}
            }
        });
        
        
		JButton atualizar = new JButton("Atualizar");
		        
		        atualizar.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {        	
		        
		            	BuscarAluno busca = new BuscarAluno();
		            	String matriculaBuscada = busca.MostrarBuscador();
		            	AlunoController controller = new AlunoController();
		            	Map<String, String> mapAluno = controller.buscarAluno(matriculaBuscada);
		            	
		            	if (mapAluno != null) { 
		            		mapAluno =UpdateAlunoView.showUpdateForm(mapAluno);
		            		AlunoController controller2 = new AlunoController();
		            		Boolean response = controller2.atualizarAluno(mapAluno);
		            		
		            		
		            		if(response) {
		                    	JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
		                    	
		                    }else {
		                    	JOptionPane.showMessageDialog(null, "Operação falhou verifique");
		                    }
		            	
		            		
		            	    
		            	} else {
		            	    System.out.println("Aluno não encontrado");
		            	}
		          
		            }
		        });
		        
		        
		        JButton deletarCadastro = new JButton("Deletar cadastro");
		        
		        deletarCadastro.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {            	
		            	AlunoController controller = new AlunoController();
		            	BuscarAluno busca = new BuscarAluno();		            		            		
		            	String matriculaBuscada = busca.MostrarBuscador();
		            	Boolean response = controller.deletarAluno(matriculaBuscada);
		            	
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
        panel.add(botaoLerFormulario);
        panel.add(deletarCadastro);
        panel.add(botaoLerTudo);
        panel.add(atualizar);
        frame.add(panel);
        frame.setVisible(true);
    }
}