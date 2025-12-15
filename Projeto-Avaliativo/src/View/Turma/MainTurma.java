package View.Turma;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import Controller.*;
public class MainTurma {

    public static void main(String[] args) {
        // Criar a janela principal
        JFrame frame = new JFrame("Tela Principal Turma");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);

        // Botão para abrir formulário
        JButton botaoAbrirFormulario = new JButton("Cadastrar Turma");        
        // Ação do botão
        botaoAbrirFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	TurmaFormulario formulario = new TurmaFormulario();
                Map<String, String> map = formulario.mostrarFormulario();
                TurmaController controller = new TurmaController();
                Boolean response = controller.criarTurma(map);
                
                
                if(response) {
                	JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
                	
                }else {
                	JOptionPane.showMessageDialog(null, "Operação falhou verifique");
                }
            }
        });
        
        
        JButton botaoLerFormulario = new JButton("Buscar Turma");
        
        botaoLerFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	BuscarTurma busca = new BuscarTurma();
            	String matriculaBuscada = busca.MostrarBuscador();
            	TurmaController controller = new TurmaController();
            	Map<String, String> mapTurma = controller.buscarTurma(matriculaBuscada);
            	if (mapTurma != null) { 
            		List<Map<String, String>> listTurma = new ArrayList<>();            		
            		listTurma.add(mapTurma);
            		
            		TurmaTabelaView tabela = new TurmaTabelaView();
            		tabela.mostrarTabela(listTurma);
            		
            	
            		
            	    
            	} else {
            	    System.out.println("Turma não encontrado");
            	}
            }
        });
        
        
        
        JButton botaoLerTudo = new JButton("Buscar Todos");
        
        botaoLerTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            	
        
            	TurmaController controller = new TurmaController();
            	List<Map<String, String>> listTurma = controller.listarTurma();
            	if (listTurma != null ) {         		
            		
            		TurmaTabelaView tabela = new TurmaTabelaView();
            		tabela.mostrarTabela(listTurma); 		
            	
            		
            	    
            	} else {
            	    System.out.println("Turma não encontrado");
            	}
            }
        });
        
        
		JButton atualizar = new JButton("Atualizar");
		        
		        atualizar.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {        	
		        
		            	BuscarTurma busca = new BuscarTurma();
		            	String matriculaBuscada = busca.MostrarBuscador();
		            	TurmaController controller = new TurmaController();
		            	Map<String, String> mapTurma = controller.buscarTurma(matriculaBuscada);
		            	
		            	if (mapTurma != null) { 
		            		mapTurma = UpdateTurmaView.showUpdateForm(mapTurma);
		            		TurmaController controller2 = new TurmaController();
		            		Boolean response = controller2.atualizarTurma(mapTurma);
		            		
		            		
		            		if(response) {
		                    	JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
		                    	
		                    }else {
		                    	JOptionPane.showMessageDialog(null, "Operação falhou verifique");
		                    }
		            	
		            		
		            	    
		            	} else {
		            	    System.out.println("Turma não encontrado");
		            	}
		          
		            }
		        });
		        
		        
		        JButton deletarCadastro = new JButton("Deletar cadastro");
		        
		        deletarCadastro.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {            	
		            	TurmaController controller = new TurmaController();
		            	BuscarTurma busca = new BuscarTurma();		            		            		
		            	String matriculaBuscada = busca.MostrarBuscador();
		            	Boolean response = controller.deletarTurma(matriculaBuscada);
		            	
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