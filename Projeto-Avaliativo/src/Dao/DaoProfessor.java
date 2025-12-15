package Dao;

import Models.Professor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoProfessor implements DAO<Professor, Integer> {

    private final Connection connection;

    public DaoProfessor(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Boolean save(Professor professor) throws SQLException {
        String sqlPessoa = "INSERT INTO Pessoa (nome, endereco, telefone, email) VALUES (?, ?, ?, ?)";
        String sqlProfessor = "INSERT INTO Professor (matricula, id_pessoa) VALUES (?, ?)";

        connection.setAutoCommit(false);

        // Inserir Pessoa
        try (PreparedStatement ps = connection.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEndereco());
            ps.setString(3, professor.getTelefone());
            ps.setString(4, professor.getEmail());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    professor.setId(rs.getInt(1));
                } else {
                    connection.rollback();
                    return false;
                }
            }
        }

        // Inserir Professor
        try (PreparedStatement ps = connection.prepareStatement(sqlProfessor)) {
            ps.setInt(1, professor.getMatricula());
            ps.setInt(2, professor.getId());
            ps.executeUpdate();
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public Boolean update(Professor professor) throws SQLException {

        String sqlPessoa = """
            UPDATE Pessoa p
            SET
                nome = ?,
                endereco = ?,
                telefone = ?,
                email = ?
            FROM Professor pr
            WHERE pr.id_pessoa = p.id
              AND pr.matricula = ?
            """;

        String sqlProfessor = """
            UPDATE Professor
            SET matricula = ?
            WHERE id_pessoa = ?
            """;

        connection.setAutoCommit(false);

        try {
            // Atualiza pessoa
            try (PreparedStatement ps = connection.prepareStatement(sqlPessoa)) {
                ps.setString(1, professor.getNome());
                ps.setString(2, professor.getEndereco());
                ps.setString(3, professor.getTelefone());
                ps.setString(4, professor.getEmail());
                ps.setInt(5, professor.getMatricula());
                ps.executeUpdate();
            }

            // Atualiza professor
            try (PreparedStatement ps = connection.prepareStatement(sqlProfessor)) {
                ps.setInt(1, professor.getMatricula());
                ps.setInt(2, professor.getId());
                ps.executeUpdate();
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public Optional<Professor> findById(Integer id) throws SQLException {
        String sql = """
            SELECT pr.matricula, p.id, p.nome, p.endereco, p.telefone, p.email
            FROM Professor pr
            JOIN Pessoa p ON pr.id_pessoa = p.id
            WHERE pr.matricula = ?
            """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapProfessor(rs));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Professor> findAll() throws SQLException {
        String sql = """
            SELECT pr.matricula, p.id, p.nome, p.endereco, p.telefone, p.email
            FROM Professor pr
            JOIN Pessoa p ON pr.id_pessoa = p.id
            ORDER BY p.nome
            """;

        List<Professor> professores = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                professores.add(mapProfessor(rs));
            }
        }
        return professores;
    }

    @Override
    public Boolean delete(Professor professor) throws SQLException {
        String sqlProfessor = "DELETE FROM Professor WHERE id_pessoa = ?";
        String sqlPessoa = "DELETE FROM Pessoa WHERE id = ?";

        connection.setAutoCommit(false);

        try (PreparedStatement ps = connection.prepareStatement(sqlProfessor)) {
            ps.setInt(1, professor.getId());
            ps.executeUpdate();
        }

        try (PreparedStatement ps = connection.prepareStatement(sqlPessoa)) {
            ps.setInt(1, professor.getId());
            ps.executeUpdate();
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    // =========================
    // Helper
    // =========================
    private Professor mapProfessor(ResultSet rs) throws SQLException {
        Professor professor = new Professor();
        professor.setId(rs.getInt("id"));
        professor.setNome(rs.getString("nome"));
        professor.setEndereco(rs.getString("endereco"));
        professor.setTelefone(rs.getString("telefone"));
        professor.setEmail(rs.getString("email"));
        professor.setMatricula(rs.getInt("matricula"));
        return professor;
    }
}
