package Dao;

import java.sql.*;
import Models.Nota;

public class DaoNota implements DAO<Nota, Integer> {
    private final Connection connection;

    public DaoNota(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Boolean save(Nota nota) throws SQLException {
        String sql = "INSERT INTO nota (id_aluno, id_disciplina, valor) VALUES (?, ?, ?)";

        connection.setAutoCommit(false);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, nota.getId_aluno());
            ps.setInt(2, nota.getId_disciplina());
            ps.setDouble(3, nota.getValor());
            ps.executeUpdate();
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public Boolean update(Nota nota) throws SQLException {
        String sql = "UPDATE nota SET id_aluno = ?, id_disciplina = ?, valor = ? WHERE id = ?";

        connection.setAutoCommit(false);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, nota.getId_aluno());
            ps.setInt(2, nota.getId_disciplina());
            ps.setDouble(3, nota.getValor());
            ps.setInt(4, nota.getId());
            ps.executeUpdate();
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public java.util.Optional<Nota> findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM nota WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Nota nota = new Nota();
                    nota.setId(rs.getInt("id"));
                    nota.setId_aluno(rs.getInt("id_aluno"));
                    nota.setId_disciplina(rs.getInt("id_disciplina"));
                    nota.setValor(rs.getDouble("valor"));
                    return java.util.Optional.of(nota);
                } else {
                    return java.util.Optional.empty();
                }
            }
        }
    }

    @Override
    public java.util.List<Nota> findAll() throws SQLException {
        String sql = "SELECT * FROM nota";
        java.util.List<Nota> notas = new java.util.ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                notas.add(mapNota(rs));
            }
        }

        return notas;
    }

    @Override
    public Boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM nota WHERE id = ?";

        connection.setAutoCommit(false);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    private Nota mapNota(ResultSet rs) throws SQLException {
        Nota nota = new Nota();
        nota.setId(rs.getInt("id"));
        nota.setId_aluno(rs.getInt("id_aluno"));
        nota.setId_disciplina(rs.getInt("id_disciplina"));
        nota.setValor(rs.getDouble("valor"));
        return nota;
    }

}
