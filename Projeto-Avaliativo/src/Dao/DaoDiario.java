package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Models.Diario;

public class DaoDiario implements DAO<Diario, Integer>{
    private final Connection connection;

    public DaoDiario(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Boolean save(Diario diario) throws SQLException {
        String sql = "INSERT INTO Diario (status, matricula_aluno, id_disciplina, id_periodo, id_turma) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setBoolean(1, diario.getStatus().equalsIgnoreCase("true"));
            ps.setInt(2, diario.getIdAluno());
            ps.setInt(3, diario.getIdDisciplina());
            ps.setInt(4, diario.getIdPeriodo());
            ps.setInt(5, diario.getIdTurma());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    diario.setId(rs.getInt(1));
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public Boolean update(Diario diario) throws SQLException {
        String sql = "UPDATE Diario SET status = ?, matricula_aluno = ?, id_disciplina = ?, id_periodo = ?, id_turma = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setBoolean(1, diario.getStatus().equalsIgnoreCase("true"));
            ps.setInt(2, diario.getIdAluno());
            ps.setInt(3, diario.getIdDisciplina());
            ps.setInt(4, diario.getIdPeriodo());
            ps.setInt(5, diario.getIdTurma());
            ps.setInt(6, diario.getId());
            ps.executeUpdate();
        }

        return true;
    }

    @Override
    public Optional<Diario> findById(Integer id) throws SQLException {
        String sql = "SELECT id, status, matricula_aluno, id_disciplina, id_periodo, id_turma FROM Diario WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapDiario(rs));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Diario> findAll() throws SQLException {
        String sql = "SELECT id, status, matricula_aluno, id_disciplina, id_periodo, id_turma FROM Diario ORDER BY id";

        List<Diario> diarios = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                diarios.add(mapDiario(rs));
            }
        }

        return diarios;
    }

    @Override
    public Boolean delete(Diario diario) throws SQLException {
        String sql = "DELETE FROM Diario WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, diario.getId());
            ps.executeUpdate();
        }

        return true;
    }

    // =========================
    // Helper
    // =========================
    private Diario mapDiario(ResultSet rs) throws SQLException {
        Diario diario = new Diario(
            rs.getInt("id"),
            rs.getInt("matricula_aluno"),
            rs.getInt("id_turma"),
            rs.getInt("id_disciplina"),
            rs.getInt("id_periodo"),
            rs.getBoolean("status") ? "true" : "false"
        );
        return diario;
    }
}
