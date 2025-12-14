package Dao;

import Models.Professor;
import java.sql.*;
import java.util.List;


public class DaoProfessor implements DAO<Professor, Integer>{
    private final Connection connection;

    public DaoProfessor(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Boolean save(Professor professor) throws SQLException {
        String sql = "INSERT INTO pessoa (nome, endereco, telefone, email) VALUES (?, ?, ?, ?)";
        String sqlProfessor = "INSERT INTO professor (matricula, id_pessoa) VALUES (?, ?)";

        connection.setAutoCommit(false);

        // Inserir Pessoa
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
            ps.setString(1, professor.getMatricula());
            ps.setInt(2, professor.getId());
            ps.executeUpdate();
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public Boolean update(Professor professor) throws SQLException {
        String sqlPessoa = "UPDATE pessoa SET nome = ?, endereco = ?, telefone = ?, email = ? WHERE id = ?";
        String sqlProfessor = "UPDATE professor SET matricula = ? WHERE id_pessoa = ?";

        connection.setAutoCommit(false);

        // Atualizar Pessoa
        try (PreparedStatement ps = connection.prepareStatement(sqlPessoa)) {
            ps.setString(1, professor.getNome());
            ps.setString(2, professor.getEndereco());
            ps.setString(3, professor.getTelefone());
            ps.setString(4, professor.getEmail());
            ps.setInt(5, professor.getId());
            ps.executeUpdate();
        }

        // Atualizar Professor
        try (PreparedStatement ps = connection.prepareStatement(sqlProfessor)) {
            ps.setString(1, professor.getMatricula());
            ps.setInt(2, professor.getId());
            ps.executeUpdate();
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public java.util.Optional<Professor> findById(Integer id) throws SQLException {
        String sql = "SELECT p.id, p.nome, p.endereco, p.telefone, p.email, pr.matricula " +
                     "FROM pessoa p JOIN professor pr ON p.id = pr.id_pessoa WHERE p.id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Professor professor = new Professor();
                    professor.setId(rs.getInt("id"));
                    professor.setNome(rs.getString("nome"));
                    professor.setEndereco(rs.getString("endereco"));
                    professor.setTelefone(rs.getString("telefone"));
                    professor.setEmail(rs.getString("email"));
                    professor.setMatricula(rs.getString("matricula"));
                    return java.util.Optional.of(professor);
                } else {
                    return java.util.Optional.empty();
                }
            }
        }
    }

    @Override
    public Boolean delete(Professor professor) throws SQLException {
        String sqlProfessor = "DELETE FROM professor WHERE id_pessoa = ?";
        String sqlPessoa = "DELETE FROM pessoa WHERE id = ?";

        connection.setAutoCommit(false);

        // Deletar Professor
        try (PreparedStatement ps = connection.prepareStatement(sqlProfessor)) {
            ps.setInt(1, professor.getId());
            ps.executeUpdate();
        }

        // Deletar Pessoa
        try (PreparedStatement ps = connection.prepareStatement(sqlPessoa)) {
            ps.setInt(1, professor.getId());
            ps.executeUpdate();
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    private Professor mapProfessor(ResultSet rs) throws SQLException {
        Professor professor = new Professor();
        professor.setId(rs.getInt("id"));
        professor.setNome(rs.getString("nome"));
        professor.setEndereco(rs.getString("endereco"));
        professor.setTelefone(rs.getString("telefone"));
        professor.setEmail(rs.getString("email"));
        professor.setMatricula(rs.getString("matricula"));
        return professor;
    }

    @Override
    public List<Professor> findAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
