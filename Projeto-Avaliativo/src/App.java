import Controller.ControllerAluno;
import Services.InMemoryServices;
import View.TelaPrincipal;
import javax.swing.SwingUtilities;

public class App { // <<< NOME DA CLASSE ALTERADO PARA App
    public static void main(String[] args) {
        
        // 1. Instancia o Serviço (Mock de DB)
        InMemoryServices servicesAluno = new InMemoryServices(); 
        
        // 2. Instancia o Controller, injetando o Serviço
        ControllerAluno controllerAluno = new ControllerAluno(servicesAluno);

        // 3. Inicia a GUI na Thread de Despacho de Eventos (EDT)
        SwingUtilities.invokeLater(() -> {
            // Instancia a Tela Principal, injetando o Controller
            TelaPrincipal frame = new TelaPrincipal(controllerAluno); 
            frame.setVisible(true);
        });
    }
}