package Controller.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.regex.Pattern;


public class Util {
	
    public static void log(String message) {
    	String LOG_DIR = "logs";
        try {
            // Cria a pasta se não existir
            File dir = new File(LOG_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Nome do arquivo de log baseado na data
            String fileName = LOG_DIR + "/log_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".txt";

            // Escreve a mensagem no arquivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                writer.write("[" + timestamp + "] " + message);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace(); // caso dê algum erro ao criar/escrever o arquivo
        }
    }
}
    
