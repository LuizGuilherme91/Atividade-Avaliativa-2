package Models;

public class Aluno extends Pessoa {
    private String matricula;
    private String nomePai;
    private String nomeMae;

    public Aluno() {}

    public Aluno(Integer id, String nome, String endereco, String telefone, String email, String matricula, String nomePai, String nomeMae) {
        super();
        this.matricula = matricula;
        this.nomePai = nomePai;
        this.nomeMae = nomeMae;
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }
    public String getNomePai() {
        return nomePai;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }
    public String getNomeMae() {
        return nomeMae;
    }

}
