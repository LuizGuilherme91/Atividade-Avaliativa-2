package View.Disciplina;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import Controller.*;
public class MainDisciplina {

    public static void main(String[] args) {
        // Criar a janela principal
        JFrame frame = new JFrame("Tela Principal Disciplina");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);

        // Botão para abrir formulário
        JButton botaoAbrirFormulario = new JButton("Cadastrar Disciplina");        
        // Ação do botão
        botaoAbrirFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisciplinaFormulario formulario = new DisciplinaFormulario();
                Map<String, String> map = formulario.mostrarFormulario();
                DisciplinaController controller = new DisciplinaController();
                Boolean response = controller.criarDisciplina(map);
                
                
                if(response) {
                	JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
                	
                }else {
                	JOptionPane.showMessageDialog(null, "Operação falhou verifique");
                }
            }
        });
        
        
        JButton botaoLerFormulario = new JButton("Buscar Disciplina");
        
        botaoLerFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	BuscarDisciplina busca = new BuscarDisciplina();
            	String matriculaBuscada = busca.MostrarBuscador();
            	DisciplinaController controller = new DisciplinaController();
            	Map<String, String> mapDisciplina = controller.buscarDisciplina(matriculaBuscada);
            	if (mapDisciplina != null) { 
            		List<Map<String, String>> listDisciplina = new ArrayList<>();            		
            		listDisciplina.add(mapDisciplina);
            		
            		DisciplinaTabelaView tabela = new DisciplinaTabelaView();
            		tabela.mostrarTabela(listDisciplina);
            		
            	
            		
            	    
            	} else {
            	    System.out.println("Disciplina não encontrado");
            	}
            }
        });
        
        
        
        JButton botaoLerTudo = new JButton("Buscar Todos");
        
        botaoLerTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            	
        
            	DisciplinaController controller = new DisciplinaController();
            	List<Map<String, String>> listDisciplina = controller.listarDisciplina();
            	if (listDisciplina != null ) {         		
            		
            		DisciplinaTabelaView tabela = new DisciplinaTabelaView();
            		tabela.mostrarTabela(listDisciplina); 		
            	
            		
            	    
            	} else {
            	    System.out.println("Disciplina não encontrado");
            	}
            }
        });
        
        
		JButton atualizar = new JButton("Atualizar");
		        
		        atualizar.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {        	
		        
		            	BuscarDisciplina busca = new BuscarDisciplina();
		            	String matriculaBuscada = busca.MostrarBuscador();
		            	DisciplinaController controller = new DisciplinaController();
		            	Map<String, String> mapDisciplina = controller.buscarDisciplina(matriculaBuscada);
		            	
		            	if (mapDisciplina != null) { 
		            		mapDisciplina = UpdateDisciplinaView.showUpdateForm(mapDisciplina);
		            		DisciplinaController controller2 = new DisciplinaController();
		            		Boolean response = controller2.atualizarDisciplina(mapDisciplina);
		            		
		            		
		            		if(response) {
		                    	JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
		                    	
		                    }else {
		                    	JOptionPane.showMessageDialog(null, "Operação falhou verifique");
		                    }
		            	
		            		
		            	    
		            	} else {
		            	    System.out.println("Disciplina não encontrado");
		            	}
		          
		            }
		        });
		        
		        
		        JButton deletarCadastro = new JButton("Deletar cadastro");
		        
		        deletarCadastro.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {            	
		            	DisciplinaController controller = new DisciplinaController();
		            	BuscarDisciplina busca = new BuscarDisciplina();		            		            		
		            	String matriculaBuscada = busca.MostrarBuscador();
		            	Boolean response = controller.deletarDisciplina(matriculaBuscada);
		            	
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