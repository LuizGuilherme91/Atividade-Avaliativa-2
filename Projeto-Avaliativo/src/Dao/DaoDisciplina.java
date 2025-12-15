package Dao;

import Models.Disciplina;
import java.sql.*;
import java.util.*;

public class DaoDisciplina implements DAO<Disciplina, Integer> {

    private final Connection connection;

    public DaoDisciplina(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Boolean save(Disciplina disciplina) throws SQLException {
        String sql = "INSERT INTO Disciplina (nome_disciplina, id_professor) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, disciplina.getNome_disciplina());
            ps.setInt(2, disciplina.getProf_responsavel());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    disciplina.setId(rs.getInt(1));
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public Boolean update(Disciplina disciplina) throws SQLException {
        String sql = "UPDATE Disciplina SET nome_disciplina = ?, id_professor = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, disciplina.getNome_disciplina());
            ps.setInt(2, disciplina.getProf_responsavel());
            ps.setInt(3, disciplina.getId());
            ps.executeUpdate();
        }

        return true;
    }

    @Override
    public Optional<Disciplina> findById(Integer id) throws SQLException {
        String sql = "SELECT id, nome_disciplina, id_professor FROM Disciplina WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapDisciplina(rs));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Disciplina> findAll() throws SQLException {
        String sql = "SELECT id, nome_disciplina, id_professor FROM Disciplina ORDER BY nome_disciplina";

        List<Disciplina> disciplinas = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                disciplinas.add(mapDisciplina(rs));
            }
        }

        return disciplinas;
    }

    @Override
    public Boolean delete(Disciplina disciplina) throws SQLException {
        String sql = "DELETE FROM Disciplina WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, disciplina.getId());
            ps.executeUpdate();
        }

        return true;
    }

    // =========================
    // Helper
    // =========================
    private Disciplina mapDisciplina(ResultSet rs) throws SQLException {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(rs.getInt("id"));
        disciplina.setNome_disciplina(rs.getString("nome_disciplina"));
        disciplina.setProf_responsavel(rs.getInt("id_professor"));
        return disciplina;
    }
}
