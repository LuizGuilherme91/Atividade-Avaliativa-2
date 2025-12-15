package Models;

public class Nota{
    private Integer id;
    private Integer id_diario;
    private Integer matricula_aluno;
    private Double nota;    
    
	public Nota(Integer id, Integer id_diario, Integer matricula_aluno, Double nota) {
		this.id = id;
		this.id_diario = id_diario;
		this.matricula_aluno = matricula_aluno;
		this.nota = nota;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_diario() {
		return id_diario;
	}
	public void setId_diario(Integer id_diario) {
		this.id_diario = id_diario;
	}
	public Integer getMatricula_aluno() {
		return matricula_aluno;
	}
	public void setMatricula_aluno(Integer matricula_aluno) {
		this.matricula_aluno = matricula_aluno;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}

   

}
