package Controller.util;
import java.util.regex.Pattern;

public class Validator {

    
    private static final Pattern NOME_REGEX = Pattern.compile("^[A-Za-zÀ-ú ]+$");
    private static final Pattern ENDERECO_REGEX = Pattern.compile("^[A-Za-z0-9À-ú ,.-]+$");
    private static final Pattern TELEFONE_REGEX = Pattern.compile("^\\+?\\d{10,15}$");
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w+$");
    private static final Pattern MATRICULA_REGEX = Pattern.compile("^\\d{10}$"); // 10 dígitos exatos
    

    
    public static boolean validarNome(String nome) {
        return NOME_REGEX.matcher(nome).matches();
    }

    
    public static boolean validarEndereco(String endereco) {
        return ENDERECO_REGEX.matcher(endereco).matches();
    }

    
    public static boolean validarTelefone(String telefone) {
        return TELEFONE_REGEX.matcher(telefone).matches();
    }

    
    public static boolean validarEmail(String email) {
        return EMAIL_REGEX.matcher(email).matches();
    }

    
    public static boolean validarMatricula(String matricula) {
        return MATRICULA_REGEX.matcher(matricula).matches();
    }

    

}
