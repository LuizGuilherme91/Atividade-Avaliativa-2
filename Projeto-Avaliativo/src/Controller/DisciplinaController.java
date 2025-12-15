package Controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import Controller.util.Util;
import Controller.util.Validator;
import Dao.DaoDisciplina;
import Dao.PostgresConnection;
import Models.Disciplina; 

public class DisciplinaController {
	
	private final DaoDisciplina daoDisciplina;

    public DisciplinaController() {
        Connection conn = PostgresConnection.getConnection();
        this.daoDisciplina = new DaoDisciplina(conn);
    }

    // CREATE
    public Boolean criarDisciplina(Map<String, String> dados) {
        try {        	
        	if(!validarDados(dados)) {
        		return false;
        		
        	}            
        	Disciplina disciplina = buildDisciplina(dados);
            return daoDisciplina.save(disciplina);
            
         
        } catch (Exception e) {
        	//inserir logica de log
            System.err.println("Erro ao criar Disciplina: " + e.getMessage());
            Util.log("Erro ao criar Disciplina: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // READ
    public Map<String, String> buscarDisciplina(String stringId) {
    	Integer id = Integer.parseInt(stringId);
        try {
        	
        	if (daoDisciplina.findById(id).isPresent()) {
        		Disciplina Disciplina = daoDisciplina.findById(id).get();
        		Map<String, String> mapDisciplina = buildMap(Disciplina);
        		return mapDisciplina;
        	
        	}
        	
        	return null;
             
        } catch (Exception e) {
        	Util.log("Erro ao buscar Disciplina: " + e.getStackTrace());
            System.err.println("Erro ao buscar Disciplina: " + e.getMessage());
            return null;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    public List<Map<String, String>> listarDisciplina() {
        try {
        	List<Disciplina> listDisciplina =	daoDisciplina.findAll();
        	if(listDisciplina != null && !listDisciplina.isEmpty()) {        		
        		List<Map<String, String>> listaMap = listDisciplina.stream().map(this::buildMap).toList();
        		return listaMap;
        	}
        	
            return List.of();
        } catch (Exception e) {
        	Util.log("Erro ao listar Disciplina: " + e.getMessage());
            System.err.println("Erro ao listar Disciplinas: " + e.getMessage());
            return List.of();
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // UPDATE
    public Boolean atualizarDisciplina(Map<String, String> dados) {
        try {
        	if(!validarDados(dados)) {
        		return false;
        	}
        	Disciplina Disciplina = buildDisciplina(dados);
            return daoDisciplina.update(Disciplina);
        } catch (Exception e) {
        	Util.log("Erro ao atualizar Disciplina: " + e.getMessage());
            System.err.println("Erro ao atualizar Disciplina: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // DELETE
    public Boolean deletarDisciplina(String matricula) {
        try {
            Optional<Disciplina> disciplinaOpt = daoDisciplina.findById(Integer.parseInt(matricula));
            if (disciplinaOpt.isPresent()) {
            	Disciplina disciplina = disciplinaOpt.get();                
                return daoDisciplina.delete(disciplina);
            } else {
                System.err.println("disciplina não encontrado");
                return false;
            }
        } catch (Exception e) {
        	Util.log("Erro ao deletar disciplina: " + e.getMessage());
            System.err.println("Erro ao deletar disciplina: " + e.getMessage());
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // =========================
    // HELPERS
    // =========================
    private boolean validarDados(Map<String, String> dados) {

        if (!Validator.validarNome(dados.get("nome"))) {
        	System.out.println("validarNome falhou");
            return false;
        }      

        return true;
    }

    
    private Map<String, String> buildMap(Disciplina disciplina) {
    	Map<String, String> mapDisciplina =new HashMap<>();
    	mapDisciplina.put("id", String.format("%d",disciplina.getId()));
    	mapDisciplina.put("nome", disciplina.getNome_disciplina());
    	mapDisciplina.put("nome", String.format("%d",disciplina.getProf_responsavel()));
    	return mapDisciplina;
    }
    
    private Disciplina buildDisciplina(Map<String, String> dados) {
    	Disciplina disciplina = new Disciplina();

        if (dados.containsKey("id")) {
        	disciplina.setId(Integer.parseInt(dados.get("id")));
        }

        disciplina.setNome_disciplina(dados.get("nome"));
        disciplina.setId(Integer.parseInt(dados.get("Professor")));
                

        return disciplina;
    }
}
