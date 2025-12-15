package Controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import Controller.util.Util;
import Controller.util.Validator;
import Dao.DaoTurma;
import Dao.PostgresConnection;
import Models.Turma; 

public class TurmaController {
	
	private final DaoTurma daoTurma;

    public TurmaController() {
        Connection conn = PostgresConnection.getConnection();
        this.daoTurma = new DaoTurma(conn);
    }

    // CREATE
    public Boolean criarTurma(Map<String, String> dados) {
        try {        	
        	if(!validarDados(dados)) {
        		return false;
        		
        	}            
        	Turma Turma = buildTurma(dados);
            return daoTurma.save(Turma);
            
         
        } catch (Exception e) {
        	//inserir logica de log
            System.err.println("Erro ao criar Turma: " + e.getMessage());
            Util.log("Erro ao criar Turma: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // READ
    public Map<String, String> buscarTurma(String stringId) {
    	Integer id = Integer.parseInt(stringId);
        try {
        	
        	if (daoTurma.findById(id).isPresent()) {
        		Turma Turma = daoTurma.findById(id).get();
        		Map<String, String> mapTurma = buildMap(Turma);
        		System.out.println(mapTurma.toString());
        		return mapTurma;
        	
        	}
        	
        	return null;
             
        } catch (Exception e) {
        	Util.log("Erro ao buscar Turma: " + e.getStackTrace());
            System.err.println("Erro ao buscar Turma: " + e.getMessage());
            return null;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    public List<Map<String, String>> listarTurma() {
        try {
        	List<Turma> listTurma =	daoTurma.findAll();
        	if(listTurma != null && !listTurma.isEmpty()) {        		
        		List<Map<String, String>> listaMap = listTurma.stream().map(this::buildMap).toList();
        		return listaMap;
        	}
        	
            return List.of();
        } catch (Exception e) {
        	Util.log("Erro ao listar Turma: " + e.getMessage());
            System.err.println("Erro ao listar Turmas: " + e.getMessage());
            return List.of();
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // UPDATE
    public Boolean atualizarTurma(Map<String, String> dados) {
        try {
        	if(!validarDados(dados)) {
        		return false;
        	}
        	Turma Turma = buildTurma(dados);
            return daoTurma.update(Turma);
        } catch (Exception e) {
        	Util.log("Erro ao atualizar Turma: " + e.getMessage());
            System.err.println("Erro ao atualizar Turma: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // DELETE
    public Boolean deletarTurma(String matricula) {
        try {
            Optional<Turma> turmaOpt = daoTurma.findById(Integer.parseInt(matricula));
            if (turmaOpt.isPresent()) {
            	Turma turma = turmaOpt.get();                
                return daoTurma.delete(turma);
            } else {
                System.err.println("turma não encontrado");
                return false;
            }
        } catch (Exception e) {
        	Util.log("Erro ao deletar turma: " + e.getMessage());
            System.err.println("Erro ao deletar turma: " + e.getMessage());
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

    
    private Map<String, String> buildMap(Turma turma) {
    	Map<String, String> mapTurma =new HashMap<>();
    	mapTurma.put("id", String.format("%d",turma.getId()));
    	mapTurma.put("nome", turma.getNome_turma());
    	
    	return mapTurma;
    }
    
    private Turma buildTurma(Map<String, String> dados) {
    	Turma turma = new Turma();

        if (dados.containsKey("id")) {
        	turma.setId(Integer.parseInt(dados.get("id")));
        }

        turma.setNome_turma(dados.get("nome"));
        
                

        return turma;
    }
}
