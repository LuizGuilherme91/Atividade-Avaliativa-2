package Dao;

import Models.Aluno;
import java.sql.*;
import java.util.*;


public class DaoAluno implements DAO<Aluno, Integer> {

    private final Connection connection;

    public DaoAluno(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Boolean save(Aluno aluno) throws SQLException {
    	String sqlPessoa = "INSERT INTO Pessoa (nome, endereco, telefone, email) VALUES (?, ?, ?, ?)";
    	String sqlAluno = "INSERT INTO Aluno (matricula, id, nome_pai, nome_mae) VALUES (?, ?, ?, ?)";


        connection.setAutoCommit(false);

        // Inserir Pessoa
        try (PreparedStatement ps = connection.prepareStatement(sqlPessoa, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getEndereco());
            ps.setString(3, aluno.getTelefone());
            ps.setString(4, aluno.getEmail());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    aluno.setId(rs.getInt(1));
                } else {
                    connection.rollback();
                    return false;
                }
            }
        }

        // Inserir Aluno
        try (PreparedStatement ps = connection.prepareStatement(sqlAluno)) {
            ps.setString(1, aluno.getMatricula());
            ps.setInt(2, aluno.getId());
            ps.setString(3, aluno.getNomePai());
            ps.setString(4, aluno.getNomeMae());
            ps.executeUpdate();
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    @Override
    public Boolean update(Aluno aluno) throws SQLException {

    	String sqlPessoa = """
    		    UPDATE Pessoa p
    		    SET
    		        nome = ?,
    		        endereco = ?,
    		        telefone = ?,
    		        email = ?
    		    FROM Aluno a
    		    WHERE a.id = p.id
    		      AND a.matricula = ?
    		    """;

    		String sqlAluno = """
    		    UPDATE Aluno
    		    SET
    		        nome_pai = ?,
    		        nome_mae = ?
    		    WHERE matricula = ?
    		    """;

        connection.setAutoCommit(false);

        try {
            // Atualiza pessoa
            try (PreparedStatement ps = connection.prepareStatement(sqlPessoa)) {
                ps.setString(1, aluno.getNome());
                ps.setString(2, aluno.getEndereco());
                ps.setString(3, aluno.getTelefone());
                ps.setString(4, aluno.getEmail());
                ps.setString(5, aluno.getMatricula());

                ps.executeUpdate();
            }

            // Atualiza aluno
            try (PreparedStatement ps = connection.prepareStatement(sqlAluno)) {
                ps.setString(1, aluno.getNomePai());
                ps.setString(2, aluno.getNomeMae());
                ps.setString(3, aluno.getMatricula());

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
    public Optional<Aluno> findById(Integer matricula) throws SQLException {
		String sql =
			    "SELECT " +
			    "    a.matricula, a.nome_pai, a.nome_mae, " +
			    "    p.id, p.nome, p.endereco, p.telefone, p.email " +
			    "FROM Aluno a " +
			    "JOIN Pessoa p ON a.id = p.id " +
			    "WHERE a.matricula = ?";


        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, String.format("%d",matricula));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapAluno(rs));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Aluno> findAll() throws SQLException {
    	String sql = "SELECT " +
                "    a.matricula, " +
                "    a.nome_pai, " +
                "    a.nome_mae, " +
                "    p.id, " +
                "    p.nome, " +
                "    p.endereco, " +
                "    p.telefone, " +
                "    p.email " +
                "FROM Aluno a " +
                "JOIN Pessoa p ON a.id = p.id " +
                "ORDER BY p.nome";

        List<Aluno> alunos = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                alunos.add(mapAluno(rs));
            }
        }
        return alunos;
    }

    @Override
    public Boolean delete(Aluno aluno) throws SQLException {
        String sqlAluno = "DELETE FROM aluno WHERE matricula = ?";
        String sqlPessoa = "DELETE FROM pessoa WHERE id = ?";

        connection.setAutoCommit(false);

        try (PreparedStatement ps = connection.prepareStatement(sqlAluno)) {
            ps.setString(1, aluno.getMatricula());
            ps.executeUpdate();
        }

        try (PreparedStatement ps = connection.prepareStatement(sqlPessoa)) {
            ps.setInt(1, aluno.getId());
            ps.executeUpdate();
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }

    // =========================
    // Helpers
    // =========================
    private Aluno mapAluno(ResultSet rs) throws SQLException {
        Aluno aluno = new Aluno();
        aluno.setId(rs.getInt("id"));
        aluno.setNome(rs.getString("nome"));
        aluno.setEndereco(rs.getString("endereco"));
        aluno.setTelefone(rs.getString("telefone"));
        aluno.setEmail(rs.getString("email"));
        aluno.setMatricula(rs.getString("matricula"));
        aluno.setNomePai(rs.getString("nome_pai"));
        aluno.setNomeMae(rs.getString("nome_mae"));
        return aluno;
    }
}
