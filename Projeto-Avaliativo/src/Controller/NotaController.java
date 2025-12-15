package Controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import Controller.util.Util;
import Controller.util.Validator;
import Dao.DaoNota;
import Dao.PostgresConnection;
import Models.Nota;

public class NotaController {

    private final DaoNota daoNota;

    public NotaController() {
        Connection conn = PostgresConnection.getConnection();
        this.daoNota = new DaoNota(conn);
    }

    // CREATE
    public Boolean criarNota(Map<String, String> dados) {
        try {
            if (!validarDados(dados)) {
                return false;
            }
            Nota nota = buildNota(dados);
            return daoNota.save(nota);
        } catch (Exception e) {
            System.err.println("Erro ao criar Nota: " + e.getMessage());
            Util.log("Erro ao criar Nota: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // READ - por ID
    public Map<String, String> buscarNota(String stringId) {
        Integer id = Integer.parseInt(stringId);
        try {
            Optional<Nota> notaOpt = daoNota.findById(id);
            if (notaOpt.isPresent()) {
                Nota nota = notaOpt.get();
                Map<String, String> mapNota = buildMap(nota);
                System.out.println(mapNota.toString());
                return mapNota;
            }
            return null;
        } catch (Exception e) {
            Util.log("Erro ao buscar Nota: " + e.getMessage());
            System.err.println("Erro ao buscar Nota: " + e.getMessage());
            return null;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // READ - listar todos
    public List<Map<String, String>> listarNota() {
        try {
            List<Nota> listNota = daoNota.findAll();
            if (listNota != null && !listNota.isEmpty()) {
                List<Map<String, String>> listaMap = listNota.stream()
                        .map(this::buildMap)
                        .toList();
                return listaMap;
            }
            return List.of();
        } catch (Exception e) {
            Util.log("Erro ao listar Nota: " + e.getMessage());
            System.err.println("Erro ao listar Nota: " + e.getMessage());
            return List.of();
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // UPDATE
    public Boolean atualizarNota(Map<String, String> dados) {
        try {
            if (!validarDados(dados)) {
                return false;
            }
            Nota nota = buildNota(dados);
            return daoNota.update(nota);
        } catch (Exception e) {
            Util.log("Erro ao atualizar Nota: " + e.getMessage());
            System.err.println("Erro ao atualizar Nota: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // DELETE
    public Boolean deletarNota(String idStr) {
        try {
            Optional<Nota> notaOpt = daoNota.findById(Integer.parseInt(idStr));
            if (notaOpt.isPresent()) {
                Nota nota = notaOpt.get();
                return daoNota.delete(nota);
            } else {
                System.err.println("Nota não encontrada");
                return false;
            }
        } catch (Exception e) {
            Util.log("Erro ao deletar Nota: " + e.getMessage());
            System.err.println("Erro ao deletar Nota: " + e.getMessage());
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // =========================
    // HELPERS
    // =========================
    private boolean validarDados(Map<String, String> dados) {
        // validação simples seguindo o padrão do DiarioController
        if (!Validator.validarNota(dados.get("nota"))) {
            System.out.println("validarNota falhou");
            return false;
        }
        return true;
    }

    private Map<String, String> buildMap(Nota nota) {
        Map<String, String> mapNota = new HashMap<>();
        mapNota.put("id", String.format("%d", nota.getId()));
        mapNota.put("nota", String.format("%f", nota.getNota()));
        mapNota.put("id_diario", String.format("%d", nota.getId_diario()));
        mapNota.put("matricula_aluno", String.format("%d", nota.getMatricula_aluno()));
        return mapNota;
    }

    private Nota buildNota(Map<String, String> dados) {
        Nota nota = new Nota(
                dados.containsKey("id") ? Integer.parseInt(dados.get("id")) : null,
                Integer.parseInt(dados.get("id_diario")),
                Integer.parseInt(dados.get("matricula_aluno")),
                Double.parseDouble(dados.get("nota"))
        );
        return nota;
    }
}
