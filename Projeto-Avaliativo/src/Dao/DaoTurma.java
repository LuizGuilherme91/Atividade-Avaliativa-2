package Dao;

import Models.Turma; 
import java.sql.*;
import java.util.*;

public class DaoTurma implements DAO<Turma, Integer> {

    private final Connection connection;

    public DaoTurma(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Boolean save(Turma turma) throws SQLException {
        String sql = "INSERT INTO Turma (nome_turma) VALUES (?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, turma.getNome_turma());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    turma.setId(rs.getInt(1));
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public Boolean update(Turma turma) throws SQLException {
        String sql = "UPDATE Turma SET nome_turma = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, turma.getNome_turma());
            ps.setInt(2, turma.getId());
            ps.executeUpdate();
        }

        return true;
    }

    @Override
    public Optional<Turma> findById(Integer id) throws SQLException {
        String sql = "SELECT id, nome_turma FROM Turma WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapTurma(rs));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Turma> findAll() throws SQLException {
        String sql = "SELECT id, nome_turma FROM Turma ORDER BY nome_turma";

        List<Turma> turmas = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                turmas.add(mapTurma(rs));
            }
        }

        return turmas;
    }

    @Override
    public Boolean delete(Turma turma) throws SQLException {
        String sql = "DELETE FROM Turma WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, turma.getId());
            ps.executeUpdate();
        }

        return true;
    }

    // =========================
    // Helper
    // =========================
    private Turma mapTurma(ResultSet rs) throws SQLException {
        Turma turma = new Turma();
        turma.setId(rs.getInt("id"));
        turma.setNome_turma(rs.getString("nome_turma"));
        return turma;
    }
}
