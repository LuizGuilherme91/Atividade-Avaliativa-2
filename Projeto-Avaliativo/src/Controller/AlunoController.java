package Controller;
import Dao.DaoAluno;
import Dao.PostgresConnection;
import Controller.util.*;
import Models.Aluno;

import java.sql.Connection;
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
        	if(validarDados(dados)) {return false;}
            
            Aluno aluno = buildAluno(dados);
            return daoAluno.save(aluno);
        } catch (Exception e) {
        	//inserir logica de log
            System.err.println("Erro ao criar aluno: " + e.getMessage());
            Util.log("Erro ao criar aluno: " + e.getMessage());
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // READ
    public Optional<Aluno> buscarAluno(Integer id) {
        try {
            return daoAluno.findById(id);
        } catch (Exception e) {
        	Util.log("Erro ao buscar aluno: " + e.getMessage());
            System.err.println("Erro ao buscar aluno: " + e.getMessage());
            return Optional.empty();
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    public List<Aluno> listarAlunos() {
        try {
            return daoAluno.findAll();
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
        	if(validarDados(dados)) {return false;}
            Aluno aluno = buildAluno(dados);
            return daoAluno.update(aluno);
        } catch (Exception e) {
        	Util.log("Erro ao atualizar aluno: " + e.getMessage());
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // DELETE
    public Boolean deletarAluno(Integer id, String matricula) {
        try {
            Optional<Aluno> alunoOpt = daoAluno.findById(id);
            if (alunoOpt.isPresent()) {
                Aluno aluno = alunoOpt.get();
                if (!aluno.getMatricula().equals(matricula)) {
                    System.err.println("Matrícula não corresponde ao ID");
                    return false;
                }
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
    private Boolean validarDados(Map<String, String> dados) {
    	if(!Validator.validarNome(dados.get("nome"))){return false;};
    	if(!Validator.validarEndereco(dados.get("endereco"))){return false;};
    	if(!Validator.validarTelefone(dados.get("telefone"))){return false;};
    	if(!Validator.validarEmail(dados.get("email"))){return false;};
    	if(!Validator.validarMatricula(dados.get("matricula"))){return false;};
    	if(!Validator.validarNome(dados.get("nomePai"))){return false;};
    	if(!Validator.validarNome(dados.get("nomeMae"))){return false;};
    	return true;
    	
    	
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
        aluno.setMatricula(dados.get("matricula"));
        aluno.setNomePai(dados.get("nomePai"));
        aluno.setNomeMae(dados.get("nomeMae"));

        return aluno;
    }
}
