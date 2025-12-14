package Services;

public class ServicesAluno {
    public Boolean validarMatricula(String matricula) {
        return matricula != null && !matricula.trim().isEmpty();
    }

}
