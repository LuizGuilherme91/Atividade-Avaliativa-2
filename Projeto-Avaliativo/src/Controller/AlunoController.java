package Controller;
import Dao.DaoAluno;
import Dao.PostgresConnection;
import Controller.util.*;
import Models.Aluno;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AlunoController {

    private final DaoAluno daoAluno;

    public AlunoController() {
        Connection conn = PostgresConnection.getConnection();
        this.daoAluno = new DaoAluno(conn);
    }

    // CREATE
    public Boolean criarAluno(Map<String, String> dados) {
        try {        	
        	if(!validarDados(dados)) {
        		return false;
        		
        	}            
            Aluno aluno = buildAluno(dados);
            return daoAluno.save(aluno);
            
         
        } catch (Exception e) {
        	//inserir logica de log
            System.err.println("Erro ao criar aluno: " + e.getMessage());
            Util.log("Erro ao criar aluno: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // READ
    public Map<String, String> buscarAluno(String stringId) {
    	Integer id = Integer.parseInt(stringId);
        try {
        	
        	if (daoAluno.findById(id).isPresent()) {
        		Aluno aluno = daoAluno.findById(id).get();
        		Map<String, String> mapAluno = buildMap(aluno);
        		return mapAluno;
        	
        	}
        	
        	return null;
             
        } catch (Exception e) {
        	Util.log("Erro ao buscar aluno: " + e.getStackTrace());
            System.err.println("Erro ao buscar aluno: " + e.getMessage());
            return null;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    public List<Map<String, String>> listarAlunos() {
        try {
        	List<Aluno> listAluno =	daoAluno.findAll();
        	if(listAluno != null && !listAluno.isEmpty()) {        		
        		List<Map<String, String>> listaMap = listAluno.stream().map(this::buildMap).toList();
        		return listaMap;
        	}
        	
            return List.of();
        } catch (Exception e) {
        	Util.log("Erro ao listar aluno: " + e.getMessage());
            System.err.println("Erro ao listar alunos: " + e.getMessage());
            return List.of();
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // UPDATE
    public Boolean atualizarAluno(Map<String, String> dados) {
        try {
        	if(!validarDados(dados)) {
        		return false;
        	}
            Aluno aluno = buildAluno(dados);
            return daoAluno.update(aluno);
        } catch (Exception e) {
        	Util.log("Erro ao atualizar aluno: " + e.getMessage());
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // DELETE
    public Boolean deletarAluno(String matricula) {
        try {
            Optional<Aluno> alunoOpt = daoAluno.findById(Integer.parseInt(matricula));
            if (alunoOpt.isPresent()) {
                Aluno aluno = alunoOpt.get();                
                return daoAluno.delete(aluno);
            } else {
                System.err.println("Aluno não encontrado");
                return false;
            }
        } catch (Exception e) {
        	Util.log("Erro ao deletar aluno: " + e.getMessage());
            System.err.println("Erro ao deletar aluno: " + e.getMessage());
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

        if (!Validator.validarNome(dados.get("nomePai"))) {
        	System.out.println("validarNome falhou");
            return false;
        }

        if (!Validator.validarNome(dados.get("nomeMae"))) {
        	System.out.println("validarNome falhou");
            return false;
        }

        return true;
    }

    
    private Map<String, String> buildMap(Aluno aluno) {
    	Map<String, String> mapAluno =new HashMap<>();
    	mapAluno.put("id", String.format("%d",aluno.getId()));
    	mapAluno.put("nome", aluno.getNome());
    	mapAluno.put("endereco", aluno.getEndereco());
    	mapAluno.put("telefone", aluno.getTelefone());
    	mapAluno.put("email", aluno.getEmail());
    	mapAluno.put("matricula",String.format("%d", aluno.getMatricula()));
    	mapAluno.put("nomePai", aluno.getNomePai());
    	mapAluno.put("nomeMae", aluno.getNomeMae());
    	return mapAluno;
    }
    
    private Aluno buildAluno(Map<String, String> dados) {
        Aluno aluno = new Aluno();

        if (dados.containsKey("id")) {
            aluno.setId(Integer.parseInt(dados.get("id")));
        }

        aluno.setNome(dados.get("nome"));
        aluno.setEndereco(dados.get("endereco"));
        aluno.setTelefone(dados.get("telefone"));
        aluno.setEmail(dados.get("email"));
        aluno.setMatricula(Integer.parseInt(dados.get("matricula")) );
        aluno.setNomePai(dados.get("nomePai"));
        aluno.setNomeMae(dados.get("nomeMae"));

        return aluno;
    }
}
