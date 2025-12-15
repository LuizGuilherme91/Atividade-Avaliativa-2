package View.Diario;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import Controller.*;
public class MainDiario {

    public static void main(String[] args) {
        // Criar a janela principal
        JFrame frame = new JFrame("Tela Principal Diario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);

        // Botão para abrir formulário
        JButton botaoAbrirFormulario = new JButton("Cadastrar Diario");        
        // Ação do botão
        botaoAbrirFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	DiarioFormulario formulario = new DiarioFormulario();
                Map<String, String> map = formulario.mostrarFormulario();
                DiarioController controller = new DiarioController();
                Boolean response = controller.criarDiario(map);
                
                
                if(response) {
                	JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
                	
                }else {
                	JOptionPane.showMessageDialog(null, "Operação falhou verifique");
                }
            }
        });
        
        
        JButton botaoLerFormulario = new JButton("Buscar Diario");
        
        botaoLerFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	BuscarDiario busca = new BuscarDiario();
            	String matriculaBuscada = busca.MostrarBuscador();
            	DiarioController controller = new DiarioController();
            	Map<String, String> mapDiario = controller.buscarDiario(matriculaBuscada);
            	if (mapDiario != null) { 
            		List<Map<String, String>> listDiario = new ArrayList<>();            		
            		listDiario.add(mapDiario);
            		
            		DiarioTabelaView tabela = new DiarioTabelaView();
            		tabela.mostrarTabela(listDiario);
            		
            	
            		
            	    
            	} else {
            	    System.out.println("Diario não encontrado");
            	}
            }
        });
        
        
        
        JButton botaoLerTudo = new JButton("Buscar Todos");
        
        botaoLerTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            	
        
            	DiarioController controller = new DiarioController();
            	List<Map<String, String>> listDiario = controller.listarDiario();
            	if (listDiario != null ) {         		
            		
            		DiarioTabelaView tabela = new DiarioTabelaView();
            		tabela.mostrarTabela(listDiario); 		
            	
            		
            	    
            	} else {
            	    System.out.println("Diario não encontrado");
            	}
            }
        });
        
        
		JButton atualizar = new JButton("Atualizar");
		        
		        atualizar.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {        	
		        
		            	BuscarDiario busca = new BuscarDiario();
		            	String matriculaBuscada = busca.MostrarBuscador();
		            	DiarioController controller = new DiarioController();
		            	Map<String, String> mapDiario = controller.buscarDiario(matriculaBuscada);
		            	
		            	if (mapDiario != null) { 
		            		mapDiario = UpdateDiarioView.showUpdateForm(mapDiario);
		            		DiarioController controller2 = new DiarioController();
		            		Boolean response = controller2.atualizarDiario(mapDiario);
		            		
		            		
		            		if(response) {
		                    	JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
		                    	
		                    }else {
		                    	JOptionPane.showMessageDialog(null, "Operação falhou verifique");
		                    }
		            	
		            		
		            	    
		            	} else {
		            	    System.out.println("Diario não encontrado");
		            	}
		          
		            }
		        });
		        
		        
		        JButton deletarCadastro = new JButton("Deletar cadastro");
		        
		        deletarCadastro.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {            	
		            	DiarioController controller = new DiarioController();
		            	BuscarDiario busca = new BuscarDiario();		            		            		
		            	String matriculaBuscada = busca.MostrarBuscador();
		            	Boolean response = controller.deletarDiario(matriculaBuscada);
		            	
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