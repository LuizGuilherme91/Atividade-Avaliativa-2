package View.Periodo;
import javax.swing.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import Controller.*;
public class MainPeriodo {

    public static void main(String[] args) {
        // Criar a janela principal
        JFrame frame = new JFrame("Tela Principal Periodo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);

        // Botão para abrir formulário
        JButton botaoAbrirFormulario = new JButton("Cadastrar Periodo");        
        // Ação do botão
        botaoAbrirFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PeriodoFormulario formulario = new PeriodoFormulario();
                Map<String, String> map = formulario.mostrarFormulario();
                PeriodoController controller = new PeriodoController();
                Boolean response = controller.criarPeriodo(map);
                
                
                if(response) {
                	JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
                	
                }else {
                	JOptionPane.showMessageDialog(null, "Operação falhou verifique");
                }
            }
        });
        
        
        JButton botaoLerFormulario = new JButton("Buscar Periodo");
        
        botaoLerFormulario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	BuscarPerioddo busca = new BuscarPerioddo();
            	String matriculaBuscada = busca.MostrarBuscador();
            	PeriodoController controller = new PeriodoController();
            	Map<String, String> mapPeriodo = controller.buscarPeriodo(matriculaBuscada);
            	if (mapPeriodo != null) { 
            		List<Map<String, String>> listPeriodo = new ArrayList<>();            		
            		listPeriodo.add(mapPeriodo);
            		
            		PeriodoTabelaView tabela = new PeriodoTabelaView();
            		tabela.mostrarTabela(listPeriodo);
            		
            	
            		
            	    
            	} else {
            	    System.out.println("Periodo não encontrado");
            	}
            }
        });
        
        
        
        JButton botaoLerTudo = new JButton("Buscar Todos");
        
        botaoLerTudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            	
        
            	PeriodoController controller = new PeriodoController();
            	List<Map<String, String>> listPeriodo = controller.listarPeriodo();
            	if (listPeriodo != null ) {         		
            		
            		PeriodoTabelaView tabela = new PeriodoTabelaView();
            		tabela.mostrarTabela(listPeriodo); 		
            	
            		
            	    
            	} else {
            	    System.out.println("Periodo não encontrado");
            	}
            }
        });
        
        
		JButton atualizar = new JButton("Atualizar");
		        
		        atualizar.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {        	
		        
		            	BuscarPerioddo busca = new BuscarPerioddo();
		            	String matriculaBuscada = busca.MostrarBuscador();
		            	PeriodoController controller = new PeriodoController();
		            	Map<String, String> mapPeriodo = controller.buscarPeriodo(matriculaBuscada);
		            	
		            	if (mapPeriodo != null) { 
		            		mapPeriodo = UpdatePeriodoView.showUpdateForm(mapPeriodo);
		            		PeriodoController controller2 = new PeriodoController();
		            		Boolean response = controller2.atualizarPeriodo(mapPeriodo);
		            		
		            		
		            		if(response) {
		                    	JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
		                    	
		                    }else {
		                    	JOptionPane.showMessageDialog(null, "Operação falhou verifique");
		                    }
		            	
		            		
		            	    
		            	} else {
		            	    System.out.println("Periodo não encontrado");
		            	}
		          
		            }
		        });
		        
		        
		        JButton deletarCadastro = new JButton("Deletar cadastro");
		        
		        deletarCadastro.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {            	
		            	PeriodoController controller = new PeriodoController();
		            	BuscarPerioddo busca = new BuscarPerioddo();		            		            		
		            	String matriculaBuscada = busca.MostrarBuscador();
		            	Boolean response = controller.deletarPeriodo(matriculaBuscada);
		            	
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