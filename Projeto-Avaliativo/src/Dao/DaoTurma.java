package Dao;

import Models.Turma;
import java.sql.*;

public class DaoTurma implements DAO<Turma, Integer> {
    private final Connection connection;

    public DaoTurma(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Boolean save(Turma turma) throws SQLException {
        String sql = "INSERT INTO turma (nome_turma) VALUES (?)";

        connection.setAutoCommit(false);

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

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public Boolean update(Turma turma) throws SQLException {
        String sql = "UPDATE turma SET nome_turma = ? WHERE id = ?";

        connection.setAutoCommit(false);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, turma.getNome_turma());
            ps.setInt(2, turma.getId());
            ps.executeUpdate();
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public java.util.Optional<Turma> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM turma WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Turma turma = new Turma();
                    turma.setId(rs.getInt("id"));
                    turma.setNome_turma(rs.getString("nome_turma"));
                    return java.util.Optional.of(turma);
                }
            }
        }

        return java.util.Optional.empty();
    }

    @Override
    public java.util.List<Turma> findAll() throws SQLException {
        String sql = "SELECT * FROM turma";
        java.util.List<Turma> turmas = new java.util.ArrayList<>();

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
        String sql = "DELETE FROM turma WHERE id = ?";

        connection.setAutoCommit(false);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, turma.getId());
            ps.executeUpdate();
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    private Turma mapTurma(ResultSet rs) throws SQLException {
        Turma turma = new Turma();
        turma.setId(rs.getInt("id"));
        turma.setNome_turma(rs.getString("nome_turma"));
        return turma;
    }

}
