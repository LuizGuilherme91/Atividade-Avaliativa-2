package Dao;

import Models.Aluno;
import java.sql.*;
import java.util.*;

public class DaoAluno implements DaoI<Aluno, Integer> {

    private final Connection connection;

    public DaoAluno(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Aluno save(Aluno aluno) {

        String sqlPessoa = """
            INSERT INTO pessoa (nome, ndereeco, telefone, email)
            VALUES (?, ?, ?, ?)
            RETURNING id
        """;

        String sqlAluno = """
            INSERT INTO aluno (matricula, id_pessoa, nome_pai, nome_mae)
            VALUES (?, ?, ?, ?)
        """;

        try {
            connection.setAutoCommit(false);

            // 1️⃣ Pessoa
            try (PreparedStatement ps = connection.prepareStatement(sqlPessoa)) {
                ps.setString(1, aluno.getNome());
                ps.setString(2, aluno.getEndereco());
                ps.setString(3, aluno.getTelefone());
                ps.setString(4, aluno.getEmail());

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    aluno.setId(rs.getInt("id"));
                }
            }

            // 2️⃣ Aluno
            try (PreparedStatement ps = connection.prepareStatement(sqlAluno)) {
                ps.setString(1, aluno.getMatricula());
                ps.setInt(2, aluno.getId()); // id_pessoa
                ps.setString(3, aluno.getNome_pai());
                ps.setString(4, aluno.getNome_mae());
                ps.executeUpdate();
            }

            connection.commit();
            return aluno;

        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Erro ao salvar aluno", e);
        } finally {
            resetAutoCommit();
        }
    }


    @Override
    public Optional<Aluno> findById(Integer idPessoa) {

        String sql = """
            SELECT p.*, a.matricula, a.nome_pai, a.nome_mae
            FROM pessoa p
            JOIN aluno a ON a.id_pessoa = p.id
            WHERE p.id = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPessoa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(mapAluno(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno", e);
        }
    }


    @Override
    public List<Aluno> findAll() {

        String sql = """
            SELECT p.*, a.matricula, a.nome_pai, a.nome_mae
            FROM pessoa p
            JOIN aluno a ON a.id_pessoa = p.id
        """;

        List<Aluno> alunos = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                alunos.add(mapAluno(rs));
            }

            return alunos;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos", e);
        }
    }


    public Aluno update(Aluno aluno) {

        String sqlPessoa = """
            UPDATE pessoa
            SET nome = ?, endereco = ?, telefone = ?, email = ?
            WHERE id = ?
        """;

        String sqlAluno = """
            UPDATE aluno
            SET nome_pai = ?, nome_mae = ?
            WHERE matricula = ?
        """;

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement ps = connection.prepareStatement(sqlPessoa)) {
                ps.setString(1, aluno.getNome());
                ps.setString(2, aluno.getEndereco());
                ps.setString(3, aluno.getTelefone());
                ps.setString(4, aluno.getEmail());
                ps.setInt(5, aluno.getId());
                ps.executeUpdate();
            }

            try (PreparedStatement ps = connection.prepareStatement(sqlAluno)) {
                ps.setString(1, aluno.getNome_pai());
                ps.setString(2, aluno.getNome_mae());
                ps.setString(3, aluno.getMatricula());
                ps.executeUpdate();
            }

            connection.commit();
            return aluno;

        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Erro ao atualizar aluno", e);
        } finally {
            resetAutoCommit();
        }
    }


    @Override
    public void delete(Aluno aluno) {

        String sqlAluno = "DELETE FROM aluno WHERE matricula = ?";
        String sqlPessoa = "DELETE FROM pessoa WHERE id = ?";

        try {
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

        } catch (SQLException e) {
            rollback();
            throw new RuntimeException("Erro ao deletar aluno", e);
        } finally {
            resetAutoCommit();
        }
    }

    /* =========================
       Helpers
    ========================= */
    private Aluno mapAluno(ResultSet rs) throws SQLException {

        Aluno aluno = new Aluno();

        aluno.setId(rs.getInt("id"));
        aluno.setNome(rs.getString("nome"));
        aluno.setEndereco(rs.getString("endereco"));
        aluno.setTelefone(rs.getString("telefone"));
        aluno.setEmail(rs.getString("email"));
        aluno.setMatricula(rs.getString("matricula"));
        aluno.setNome_pai(rs.getString("nome_pai"));
        aluno.setNome_mae(rs.getString("nome_mae"));

        return aluno;
    }

    private void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException("Erro no rollback", e);
        }
    }

    private void resetAutoCommit() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
