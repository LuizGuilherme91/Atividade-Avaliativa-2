package Models;

public class Periodo extends ClasseGenerica {
    private Integer id;
    private String nome_periodo;

    public Periodo() {}

    public Periodo(Integer id, String nome_periodo) {
        this.id = id;
        this.nome_periodo = nome_periodo;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_periodo() {
        return nome_periodo;
    }
    public void setNome_periodo(String nome_periodo) {
        this.nome_periodo = nome_periodo;
    }

}


