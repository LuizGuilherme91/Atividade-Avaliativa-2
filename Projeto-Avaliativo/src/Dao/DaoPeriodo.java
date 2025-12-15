package Dao;

import Models.Periodo; 
import java.sql.*;
import java.util.*;

public class DaoPeriodo implements DAO<Periodo, Integer> {

    private final Connection connection;

    public DaoPeriodo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Boolean save(Periodo periodo) throws SQLException {
        String sql = "INSERT INTO Periodo (nome_periodo) VALUES (?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, periodo.getNome_periodo());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    periodo.setId(rs.getInt(1));
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public Boolean update(Periodo periodo) throws SQLException {
        String sql = "UPDATE Periodo SET nome_periodo = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, periodo.getNome_periodo());
            ps.setInt(2, periodo.getId());
            ps.executeUpdate();
        }

        return true;
    }

    @Override
    public Optional<Periodo> findById(Integer id) throws SQLException {
        String sql = "SELECT id, nome_periodo FROM Periodo WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapPeriodo(rs));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Periodo> findAll() throws SQLException {
        String sql = "SELECT id, nome_periodo FROM Periodo ORDER BY nome_periodo";

        List<Periodo> periodos = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                periodos.add(mapPeriodo(rs));
            }
        }

        return periodos;
    }

    @Override
    public Boolean delete(Periodo periodo) throws SQLException {
        String sql = "DELETE FROM Periodo WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, periodo.getId());
            ps.executeUpdate();
        }

        return true;
    }

    // =========================
    // Helper
    // =========================
    private Periodo mapPeriodo(ResultSet rs) throws SQLException {
        Periodo periodo = new Periodo();
        periodo.setId(rs.getInt("id"));
        periodo.setNome_periodo(rs.getString("nome_periodo"));
        return periodo;
    }
}
