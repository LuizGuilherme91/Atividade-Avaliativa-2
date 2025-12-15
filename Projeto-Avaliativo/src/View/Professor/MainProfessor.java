package View.Professor;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import Controller.*;
public class MainProfessor {

    public static void main(String[] args) {
        // Criar a janela principal
        JFrame frame = new JFrame("Tela Principal Professor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);

        // Botão para abrir formulário
        JButton botaoAbrirFormulario = new JButton("Cadastrar Professor");        
        // Ação do botão
        botaoAbrirFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PofessorFormulario formulario = new PofessorFormulario();
                Map<String, String> map = formulario.mostrarFormulario();
                ProfessorController controller = new ProfessorController();
                Boolean response = controller.criarProfessor(map);
                
                
                if(response) {
                	JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
                	
                }else {
                	JOptionPane.showMessageDialog(null, "Operação falhou verifique");
                }
            }
        });
        
        
        JButton botaoLerFormulario = new JButton("Buscar Professor");
        
        botaoLerFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	BuscarProfessor busca = new BuscarProfessor();
            	String matriculaBuscada = busca.MostrarBuscador();
            	ProfessorController controller = new ProfessorController();
            	Map<String, String> mapProfessor = controller.buscarProfessor(matriculaBuscada);
            	if (mapProfessor != null) { 
            		List<Map<String, String>> listProfessor = new ArrayList<>();            		
            		listProfessor.add(mapProfessor);
            		
            		ProfessorTabelaView tabela = new ProfessorTabelaView();
            		tabela.mostrarTabela(listProfessor);
            		
            	
            		
            	    
            	} else {
            	    System.out.println("Professor não encontrado");
            	}
            }
        });
        
        
        
        JButton botaoLerTudo = new JButton("Buscar Todos");
        
        botaoLerTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            	
        
            	ProfessorController controller = new ProfessorController();
            	List<Map<String, String>> listProfessor = controller.listarProfessor();
            	if (listProfessor != null ) {         		
            		
            		ProfessorTabelaView tabela = new ProfessorTabelaView();
            		tabela.mostrarTabela(listProfessor); 		
            	
            		
            	    
            	} else {
            	    System.out.println("Professor não encontrado");
            	}
            }
        });
        
        
		JButton atualizar = new JButton("Atualizar");
		        
		        atualizar.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {        	
		        
		            	BuscarProfessor busca = new BuscarProfessor();
		            	String matriculaBuscada = busca.MostrarBuscador();
		            	ProfessorController controller = new ProfessorController();
		            	Map<String, String> mapProfessor = controller.buscarProfessor(matriculaBuscada);
		            	
		            	if (mapProfessor != null) { 
		            		mapProfessor = UpdateProfessorView.showUpdateForm(mapProfessor);
		            		ProfessorController controller2 = new ProfessorController();
		            		Boolean response = controller2.atualizarProfessor(mapProfessor);
		            		
		            		
		            		if(response) {
		                    	JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
		                    	
		                    }else {
		                    	JOptionPane.showMessageDialog(null, "Operação falhou verifique");
		                    }
		            	
		            		
		            	    
		            	} else {
		            	    System.out.println("Professor não encontrado");
		            	}
		          
		            }
		        });
		        
		        
		        JButton deletarCadastro = new JButton("Deletar cadastro");
		        
		        deletarCadastro.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {            	
		            	ProfessorController controller = new ProfessorController();
		            	BuscarProfessor busca = new BuscarProfessor();		            		            		
		            	String matriculaBuscada = busca.MostrarBuscador();
		            	Boolean response = controller.deletarProfessor(matriculaBuscada);
		            	
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