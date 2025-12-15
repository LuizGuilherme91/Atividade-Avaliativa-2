package Models;

public class ClasseGenerica {
    private Integer id;
    private Integer matricula;

    public ClasseGenerica() {}

    public ClasseGenerica(Integer id, Integer matricula) {
        this.id = id;
        this.matricula = matricula;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatricula() {
        return matricula;
    }
    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }
}
