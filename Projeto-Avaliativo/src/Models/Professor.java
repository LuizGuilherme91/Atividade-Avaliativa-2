package Models;

public class Professor extends Pessoa {
    private String matricula;

    public Professor() {}

    public Professor(Integer id, String nome, String endereco, String telefone, String email, String matricula) {
        super();
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}
