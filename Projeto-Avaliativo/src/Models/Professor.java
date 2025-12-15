package Models;

public class Professor extends Pessoa {
    private Integer matricula;

    public Professor() {}

    public Professor(Integer id, String nome, String endereco, String telefone, String email, Integer matricula) {
        super();
        this.matricula = matricula;
    }

    public Integer getMatricula() {
        return matricula;
    }
    public void setMatricula(int i) {
        this.matricula = i;
    }

}
