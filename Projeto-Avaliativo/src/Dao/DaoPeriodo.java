package Dao;

import Models.Periodo;
import java.sql.*;


public class DaoPeriodo implements DAO<Periodo, Integer> {
    private final Connection connection;

    public DaoPeriodo(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Boolean save(Periodo entity) throws SQLException {
        String sql = "INSERT INTO periodo (nome_periodo) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getNome_periodo());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public Boolean update(Periodo entity) throws SQLException {
        String sql = "UPDATE periodo SET nome_periodo = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entity.getNome_periodo());
            stmt.setInt(2, entity.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    @Override
    public java.util.Optional<Periodo> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM periodo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Periodo periodo = new Periodo();
                    periodo.setId(rs.getInt("id"));
                    periodo.setNome_periodo(rs.getString("nome_periodo"));
                    return java.util.Optional.of(periodo);
                }
            }
        }
        return java.util.Optional.empty();
    }

    @Override
    public java.util.List<Periodo> findAll() throws SQLException {
        String sql = "SELECT * FROM periodo";
        java.util.List<Periodo> periodos = new java.util.ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Periodo periodo = new Periodo();
                periodo.setId(rs.getInt("id"));
                periodo.setNome_periodo(rs.getString("nome_periodo"));
                periodos.add(periodo);
            }
        }
        return periodos;
    }

    @Override
    public Boolean delete(Periodo entity) throws SQLException {
        String sql = "DELETE FROM periodo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, entity.getId());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    private Periodo mapPeriodo(ResultSet rs) throws SQLException {
        Periodo periodo = new Periodo();
        periodo.setId(rs.getInt("id"));
        periodo.setNome_periodo(rs.getString("nome_periodo"));
        return periodo;
    }

}
