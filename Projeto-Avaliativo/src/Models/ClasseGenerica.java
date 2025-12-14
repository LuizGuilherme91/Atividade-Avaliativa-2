package Models;

public class ClasseGenerica {
    private Integer id;
    private String matricula;

    public ClasseGenerica() {}

    public ClasseGenerica(Integer id, String matricula) {
        this.id = id;
        this.matricula = matricula;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
