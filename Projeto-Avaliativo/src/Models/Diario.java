package Models;

public class Diario {
	
    private Integer id;
    private Integer idAluno;
    private Integer idTurma;
    private Integer idDisciplina;
    private Integer idPeriodo;    
    private String status;
    
	public Diario(Integer id, Integer idAluno, Integer idTurma, Integer idDisciplina, Integer idPeriodo,
			String status) {
		
		this.id = id;
		this.idAluno = idAluno;
		this.idTurma = idTurma;
		this.idDisciplina = idDisciplina;
		this.idPeriodo = idPeriodo;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(Integer idAluno) {
		this.idAluno = idAluno;
	}

	public Integer getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}

	public Integer getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(Integer idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

    

}
