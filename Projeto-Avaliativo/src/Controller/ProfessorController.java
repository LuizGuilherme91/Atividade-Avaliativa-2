package Controller;
import Dao.DaoProfessor;
import Dao.PostgresConnection;
import Controller.util.*;
import Models.Professor;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProfessorController {

    private final DaoProfessor daoProfessor;

    public ProfessorController() {
        Connection conn = PostgresConnection.getConnection();
        this.daoProfessor = new DaoProfessor(conn);
    }

    // CREATE
    public Boolean criarProfessor(Map<String, String> dados) {
        try {        	
        	if(!validarDados(dados)) {
        		return false;
        		
        	}            
            Professor professor = buildProfessor(dados);
            return daoProfessor.save(professor);
            
         
        } catch (Exception e) {
        	//inserir logica de log
            System.err.println("Erro ao criar Professor: " + e.getMessage());
            Util.log("Erro ao criar Professor: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // READ
    public Map<String, String> buscarProfessor(String stringId) {
    	Integer id = Integer.parseInt(stringId);
        try {
        	
        	if (daoProfessor.findById(id).isPresent()) {
        		Professor Professor = daoProfessor.findById(id).get();
        		Map<String, String> mapProfessor = buildMap(Professor);
        		return mapProfessor;
        	
        	}
        	
        	return null;
             
        } catch (Exception e) {
        	Util.log("Erro ao buscar Professor: " + e.getStackTrace());
            System.err.println("Erro ao buscar Professor: " + e.getMessage());
            return null;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    public List<Map<String, String>> listarProfessor() {
        try {
        	List<Professor> listProfessor =	daoProfessor.findAll();
        	if(listProfessor != null && !listProfessor.isEmpty()) {        		
        		List<Map<String, String>> listaMap = listProfessor.stream().map(this::buildMap).toList();
        		return listaMap;
        	}
        	
            return List.of();
        } catch (Exception e) {
        	Util.log("Erro ao listar Professor: " + e.getMessage());
            System.err.println("Erro ao listar Professors: " + e.getMessage());
            return List.of();
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // UPDATE
    public Boolean atualizarProfessor(Map<String, String> dados) {
        try {
        	if(!validarDados(dados)) {
        		return false;
        	}
        	Professor Professor = buildProfessor(dados);
            return daoProfessor.update(Professor);
        } catch (Exception e) {
        	Util.log("Erro ao atualizar Professor: " + e.getMessage());
            System.err.println("Erro ao atualizar Professor: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // DELETE
    public Boolean deletarProfessor(String matricula) {
        try {
            Optional<Professor> professorOpt = daoProfessor.findById(Integer.parseInt(matricula));
            if (professorOpt.isPresent()) {
            	Professor professor = professorOpt.get();                
                return daoProfessor.delete(professor);
            } else {
                System.err.println("professor não encontrado");
                return false;
            }
        } catch (Exception e) {
        	Util.log("Erro ao deletar professor: " + e.getMessage());
            System.err.println("Erro ao deletar professor: " + e.getMessage());
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

        if (!Validator.validarEndereco(dados.get("endereco"))) {
        	System.out.println("validarEndereco falhou");
            return false;
        }

        if (!Validator.validarTelefone(dados.get("telefone"))) {
        	System.out.println("validarTelefone falhou");
            return false;
        }

        if (!Validator.validarEmail(dados.get("email"))) {
        	System.out.println("validarEmail falhou");
            return false;
        }

        if (!Validator.validarMatricula(dados.get("matricula"))) {
        	System.out.println("validarMatricula falhou");
            return false;
        }

        return true;
    }

    
    private Map<String, String> buildMap(Professor professor) {
    	Map<String, String> mapProfessor =new HashMap<>();
    	mapProfessor.put("id", String.format("%d",professor.getId()));
    	mapProfessor.put("nome", professor.getNome());
    	mapProfessor.put("endereco", professor.getEndereco());
    	mapProfessor.put("telefone", professor.getTelefone());
    	mapProfessor.put("email", professor.getEmail());
    	mapProfessor.put("matricula", String.format("%d", professor.getMatricula()));
    	return mapProfessor;
    }
    
    private Professor buildProfessor(Map<String, String> dados) {
    	Professor professor = new Professor();

        if (dados.containsKey("id")) {
        	professor.setId(Integer.parseInt(dados.get("id")));
        }

        professor.setNome(dados.get("nome"));
        professor.setEndereco(dados.get("endereco"));
        professor.setTelefone(dados.get("telefone"));
        professor.setEmail(dados.get("email"));
        professor.setMatricula(Integer.parseInt(dados.get("matricula")));        

        return professor;
    }
}
