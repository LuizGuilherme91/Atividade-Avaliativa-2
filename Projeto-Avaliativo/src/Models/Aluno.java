package Models;

public class Aluno extends Pessoa {
    private String matricula;
    private String nome_pai;
    private String nome_mae;

    public Aluno() {}

    public Aluno(Integer id, String nome, String endereco, String telefone, String email, String matricula, String nome_pai, String nome_mae) {
        super();
        this.matricula = matricula;
        this.nome_pai = nome_pai;
        this.nome_mae = nome_mae;
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNome_pai(String nome_pai) {
        this.nome_pai = nome_pai;
    }
    public String getNome_pai() {
        return nome_pai;
    }

    public void setNome_mae(String nome_mae) {
        this.nome_mae = nome_mae;
    }
    public String getNome_mae() {
        return nome_mae;
    }

}
