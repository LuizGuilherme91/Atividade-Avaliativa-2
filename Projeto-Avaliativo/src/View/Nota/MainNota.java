package View.Nota;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import Controller.*;
public class MainNota {

    public static void main(String[] args) {
        // Criar a janela principal
        JFrame frame = new JFrame("Tela Principal Nota");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);

        // Botão para abrir formulário
        JButton botaoAbrirFormulario = new JButton("Cadastrar Nota");        
        // Ação do botão
        botaoAbrirFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	NotaFormulario formulario = new NotaFormulario();
                Map<String, String> map = formulario.mostrarFormulario();
                NotaController controller = new NotaController();
                Boolean response = controller.criarNota(map);
                
                
                if(response) {
                	JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
                	
                }else {
                	JOptionPane.showMessageDialog(null, "Operação falhou verifique");
                }
            }
        });
        
        
        JButton botaoLerFormulario = new JButton("Buscar Nota");
        
        botaoLerFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	BuscarNota busca = new BuscarNota();
            	String matriculaBuscada = busca.MostrarBuscador();
            	NotaController controller = new NotaController();
            	Map<String, String> mapNota = controller.buscarNota(matriculaBuscada);
            	if (mapNota != null) { 
            		List<Map<String, String>> listNota = new ArrayList<>();            		
            		listNota.add(mapNota);
            		
            		NotaTabelaView tabela = new NotaTabelaView();
            		tabela.mostrarTabela(listNota);
            		
            	
            		
            	    
            	} else {
            	    System.out.println("Nota não encontrado");
            	}
            }
        });
        
        
        
        JButton botaoLerTudo = new JButton("Buscar Todos");
        
        botaoLerTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            	
        
            	NotaController controller = new NotaController();
            	List<Map<String, String>> listNota = controller.listarNota();
            	if (listNota != null ) {         		
            		
            		NotaTabelaView tabela = new NotaTabelaView();
            		tabela.mostrarTabela(listNota); 		
            	
            		
            	    
            	} else {
            	    System.out.println("Nota não encontrado");
            	}
            }
        });
        
        
		JButton atualizar = new JButton("Atualizar");
		        
		        atualizar.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {        	
		        
		            	BuscarNota busca = new BuscarNota();
		            	String matriculaBuscada = busca.MostrarBuscador();
		            	NotaController controller = new NotaController();
		            	Map<String, String> mapNota = controller.buscarNota(matriculaBuscada);
		            	
		            	if (mapNota != null) { 
		            		mapNota = UpdateNotaView.showUpdateForm(mapNota);
		            		NotaController controller2 = new NotaController();
		            		Boolean response = controller2.atualizarNota(mapNota);
		            		
		            		
		            		if(response) {
		                    	JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
		                    	
		                    }else {
		                    	JOptionPane.showMessageDialog(null, "Operação falhou verifique");
		                    }
		            	
		            		
		            	    
		            	} else {
		            	    System.out.println("Nota não encontrado");
		            	}
		          
		            }
		        });
		        
		        
		        JButton deletarCadastro = new JButton("Deletar cadastro");
		        
		        deletarCadastro.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {            	
		            	NotaController controller = new NotaController();
		            	BuscarNota busca = new BuscarNota();		            		            		
		            	String matriculaBuscada = busca.MostrarBuscador();
		            	Boolean response = controller.deletarNota(matriculaBuscada);
		            	
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