package Controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import Controller.util.Util;
import Controller.util.Validator;
import Dao.DaoPeriodo;
import Dao.PostgresConnection;
import Models.Periodo; 

public class PeriodoController {
	
	private final DaoPeriodo daoPeriodo;

    public PeriodoController() {
        Connection conn = PostgresConnection.getConnection();
        this.daoPeriodo = new DaoPeriodo(conn);
    }

    // CREATE
    public Boolean criarPeriodo(Map<String, String> dados) {
        try {        	
        	if(!validarDados(dados)) {
        		return false;
        		
        	}            
        	Periodo periodo = buildPeriodo(dados);
            return daoPeriodo.save(periodo);
            
         
        } catch (Exception e) {
        	//inserir logica de log
            System.err.println("Erro ao criar Periodo: " + e.getMessage());
            Util.log("Erro ao criar Periodo: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // READ
    public Map<String, String> buscarPeriodo(String stringId) {
    	Integer id = Integer.parseInt(stringId);
        try {
        	
        	if (daoPeriodo.findById(id).isPresent()) {
        		Periodo Periodo = daoPeriodo.findById(id).get();
        		Map<String, String> mapPeriodo = buildMap(Periodo);
        		System.out.println(mapPeriodo.toString());
        		return mapPeriodo;
        	
        	}
        	
        	return null;
             
        } catch (Exception e) {
        	Util.log("Erro ao buscar Periodo: " + e.getStackTrace());
            System.err.println("Erro ao buscar Periodo: " + e.getMessage());
            return null;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    public List<Map<String, String>> listarPeriodo() {
        try {
        	List<Periodo> listPeriodo =	daoPeriodo.findAll();
        	if(listPeriodo != null && !listPeriodo.isEmpty()) {        		
        		List<Map<String, String>> listaMap = listPeriodo.stream().map(this::buildMap).toList();
        		return listaMap;
        	}
        	
            return List.of();
        } catch (Exception e) {
        	Util.log("Erro ao listar Periodo: " + e.getMessage());
            System.err.println("Erro ao listar Periodos: " + e.getMessage());
            return List.of();
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // UPDATE
    public Boolean atualizarPeriodo(Map<String, String> dados) {
        try {
        	if(!validarDados(dados)) {
        		return false;
        	}
        	Periodo Periodo = buildPeriodo(dados);
            return daoPeriodo.update(Periodo);
        } catch (Exception e) {
        	Util.log("Erro ao atualizar Periodo: " + e.getMessage());
            System.err.println("Erro ao atualizar Periodo: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // DELETE
    public Boolean deletarPeriodo(String matricula) {
        try {
            Optional<Periodo> periodoOpt = daoPeriodo.findById(Integer.parseInt(matricula));
            if (periodoOpt.isPresent()) {
            	Periodo periodo = periodoOpt.get();                
                return daoPeriodo.delete(periodo);
            } else {
                System.err.println("periodo não encontrado");
                return false;
            }
        } catch (Exception e) {
        	Util.log("Erro ao deletar periodo: " + e.getMessage());
            System.err.println("Erro ao deletar periodo: " + e.getMessage());
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // =========================
    // HELPERS
    // =========================
    private boolean validarDados(Map<String, String> dados) {

        if (!Validator.validarEndereco(dados.get("nome"))) {
        	System.out.println("validarNome falhou");
            return false;
        }      

        return true;
    }

    
    private Map<String, String> buildMap(Periodo periodo) {
    	Map<String, String> mapPeriodo =new HashMap<>();
    	mapPeriodo.put("id", String.format("%d",periodo.getId()));
    	mapPeriodo.put("nome", periodo.getNome_periodo());
    	
    	return mapPeriodo;
    }
    
    private Periodo buildPeriodo(Map<String, String> dados) {
    	Periodo periodo = new Periodo();

        if (dados.containsKey("id")) {
        	periodo.setId(Integer.parseInt(dados.get("id")));
        }

        periodo.setNome_periodo(dados.get("nome"));
        
                

        return periodo;
    }
}
