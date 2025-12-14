package Services;

public class ServicesDiario {
    public Boolean validarNotas(Double nota) {
        return nota != null && nota >= 0.0 && nota <= 10.0;
    }

}
