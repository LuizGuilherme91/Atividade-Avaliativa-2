package Models;

public class Disciplina extends ClasseGenerica {
    private Integer id;
    private String nome_disciplina;

    public Disciplina() {}

    public Disciplina(Integer id, String nome_disciplina) {
        this.id = id;
        this.nome_disciplina = nome_disciplina;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_disciplina() {
        return nome_disciplina;
    }
    public void setNome_disciplina(String nome_disciplina) {
        this.nome_disciplina = nome_disciplina;
    }

}
