package Controller;

import Models.Aluno;
import Services.ServicesCrud;
import java.util.List;
import java.util.Optional;

public class ControllerAluno {

    private final ServicesCrud<Aluno, Integer> servicesAluno;

    public ControllerAluno(ServicesCrud<Aluno, Integer> servicesAluno) {
        this.servicesAluno = servicesAluno;
    }

    public boolean save(List<Object> alunoDados) {

        Integer id = (Integer) alunoDados.get(0);
        String nome = (String) alunoDados.get(1);
        String endereco = (String) alunoDados.get(2);
        String telefone = (String) alunoDados.get(3);
        String email = (String) alunoDados.get(4);
        String matricula = (String) alunoDados.get(5);
        String nomePai = (String) alunoDados.get(6);
        String nomeMae = (String) alunoDados.get(7);

        if (!alunoValido(id, nome, endereco, telefone, email, matricula, nomePai, nomeMae)) {
            return false;
        }

        Aluno aluno = buildAluno(
            id, nome, endereco, telefone, email, matricula, nomePai, nomeMae
        );

        servicesAluno.save(aluno);
        return true;
    }

    public Optional<Aluno> findById(Integer id) {
        return servicesAluno.findById(id);
    }

    public List<Aluno> findAll() {
        return servicesAluno.findAll();
    }

    public boolean delete(Integer id) {
        Optional<Aluno> aluno = findById(id);
        if (aluno.isPresent()) {
            servicesAluno.delete(aluno.get());
            return true;
        }
        else {return false;    }
    }

    private boolean alunoValido(
            Integer id,
            String nome,
            String endereco,
            String telefone,
            String email,
            String matricula,
            String nomePai,
            String nomeMae
    ) {
        if (id == null || id <= 0) return false;
        if (nome == null || nome.trim().isEmpty()) return false;
        if (endereco == null || endereco.trim().isEmpty()) return false;
        if (telefone == null || telefone.trim().isEmpty()) return false;
        if (email == null || email.trim().isEmpty()) return false;
        if (matricula == null || matricula.trim().isEmpty()) return false;
        if (nomePai == null || nomePai.trim().isEmpty()) return false;
        if (nomeMae == null || nomeMae.trim().isEmpty()) return false;
        return true;
    }

    private Aluno buildAluno(
            Integer id,
            String nome,
            String endereco,
            String telefone,
            String email,
            String matricula,
            String nomePai,
            String nomeMae
    ) {
        return new Aluno(id, nome, endereco, telefone, email, matricula, nomePai, nomeMae);
    }
}