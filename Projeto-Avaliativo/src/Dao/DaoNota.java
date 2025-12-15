package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Models.Nota;

public class DaoNota implements DAO<Nota, Integer> {

    private final Connection connection;

    public DaoNota(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Boolean save(Nota nota) throws SQLException {
        String sql = "INSERT INTO Nota (nota, id_diario, matricula_aluno) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, nota.getNota());
            ps.setInt(2, nota.getId_diario());
            ps.setInt(3, nota.getMatricula_aluno());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    nota.setId(rs.getInt(1));
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public Boolean update(Nota nota) throws SQLException {
        String sql = "UPDATE Nota SET nota = ?, id_diario = ?, matricula_aluno = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDouble(1, nota.getNota());
            ps.setInt(2, nota.getId_diario());
            ps.setInt(3, nota.getMatricula_aluno());
            ps.setInt(4, nota.getId());
            ps.executeUpdate();
        }

        return true;
    }

    @Override
    public Optional<Nota> findById(Integer id) throws SQLException {
        String sql = "SELECT id, nota, id_diario, matricula_aluno FROM Nota WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapNota(rs));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Nota> findAll() throws SQLException {
        String sql = "SELECT id, nota, id_diario, matricula_aluno FROM Nota ORDER BY id";

        List<Nota> notas = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                notas.add(mapNota(rs));
            }
        }

        return notas;
    }

    @Override
    public Boolean delete(Nota nota) throws SQLException {
        String sql = "DELETE FROM Nota WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, nota.getId());
            ps.executeUpdate();
        }

        return true;
    }

    // =========================
    // Helper
    // =========================
    private Nota mapNota(ResultSet rs) throws SQLException {
        Nota nota = new Nota(
            rs.getInt("id"),
            rs.getInt("id_diario"),
            rs.getInt("matricula_aluno"),
            rs.getDouble("nota")
        );
        return nota;
    }
}
