package Controller;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import Controller.util.Util;
import Controller.util.Validator;
import Dao.DaoDiario;
import Dao.PostgresConnection;
import Models.Diario;

public class DiarioController {

    private final DaoDiario daoDiario;

    public DiarioController() {
        Connection conn = PostgresConnection.getConnection();
        this.daoDiario = new DaoDiario(conn);
    }

    // CREATE
    public Boolean criarDiario(Map<String, String> dados) {
        try {
            if (!validarDados(dados)) {
                return false;
            }
            Diario diario = buildDiario(dados);
            return daoDiario.save(diario);
        } catch (Exception e) {
            System.err.println("Erro ao criar Diario: " + e.getMessage());
            Util.log("Erro ao criar Diario: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // READ - por ID
    public Map<String, String> buscarDiario(String stringId) {
        Integer id = Integer.parseInt(stringId);
        try {
            Optional<Diario> diarioOpt = daoDiario.findById(id);
            if (diarioOpt.isPresent()) {
                Diario diario = diarioOpt.get();
                Map<String, String> mapDiario = buildMap(diario);
                System.out.println(mapDiario.toString());
                return mapDiario;
            }
            return null;
        } catch (Exception e) {
            Util.log("Erro ao buscar Diario: " + e.getMessage());
            System.err.println("Erro ao buscar Diario: " + e.getMessage());
            return null;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // READ - listar todos
    public List<Map<String, String>> listarDiario() {
        try {
            List<Diario> listDiario = daoDiario.findAll();
            if (listDiario != null && !listDiario.isEmpty()) {
                List<Map<String, String>> listaMap = listDiario.stream()
                        .map(this::buildMap)
                        .toList();
                return listaMap;
            }
            return List.of();
        } catch (Exception e) {
            Util.log("Erro ao listar Diario: " + e.getMessage());
            System.err.println("Erro ao listar Diario: " + e.getMessage());
            return List.of();
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // UPDATE
    public Boolean atualizarDiario(Map<String, String> dados) {
        try {
            if (!validarDados(dados)) {
                return false;
            }
            Diario diario = buildDiario(dados);
            return daoDiario.update(diario);
        } catch (Exception e) {
            Util.log("Erro ao atualizar Diario: " + e.getMessage());
            System.err.println("Erro ao atualizar Diario: " + e.getMessage());
            e.printStackTrace(System.err);
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // DELETE
    public Boolean deletarDiario(String idStr) {
        try {
            Optional<Diario> diarioOpt = daoDiario.findById(Integer.parseInt(idStr));
            if (diarioOpt.isPresent()) {
                Diario diario = diarioOpt.get();
                return daoDiario.delete(diario);
            } else {
                System.err.println("Diario não encontrado");
                return false;
            }
        } catch (Exception e) {
            Util.log("Erro ao deletar Diario: " + e.getMessage());
            System.err.println("Erro ao deletar Diario: " + e.getMessage());
            return false;
        } finally {
            PostgresConnection.closeConnection();
        }
    }

    // =========================
    // HELPERS
    // =========================
    private boolean validarDados(Map<String, String> dados) {
        // Aqui você pode adicionar validação específica de campos
        if (!Validator.validarEndereco(dados.get("status"))) {
            System.out.println("validarStatus falhou");
            return false;
        }
        return true;
    }

    private Map<String, String> buildMap(Diario diario) {
        Map<String, String> mapDiario = new HashMap<>();
        mapDiario.put("id", String.format("%d", diario.getId()));
        mapDiario.put("status", diario.getStatus());
        mapDiario.put("idAluno", String.format("%d", diario.getIdAluno()));
        mapDiario.put("idDisciplina", String.format("%d", diario.getIdDisciplina()));
        mapDiario.put("idPeriodo", String.format("%d", diario.getIdPeriodo()));
        mapDiario.put("idTurma", String.format("%d", diario.getIdTurma()));
        return mapDiario;
    }

    private Diario buildDiario(Map<String, String> dados) {
        Diario diario = new Diario(
                dados.containsKey("id") ? Integer.parseInt(dados.get("id")) : null,
                Integer.parseInt(dados.get("idAluno")),
                Integer.parseInt(dados.get("idTurma")),
                Integer.parseInt(dados.get("idDisciplina")),
                Integer.parseInt(dados.get("idPeriodo")),
                dados.get("status")
        );
        return diario;
    }
}
