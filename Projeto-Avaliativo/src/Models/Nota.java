package Models;

public class Nota extends ClasseGenerica {
    private Integer id;
    private Double nota;

    public Nota() {}

    public Nota(Integer id, Double nota) {
        this.id = id;
        this.nota = nota;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }
    public void setNota(Double nota) {
        this.nota = nota;
    }

}
