// Exemplo de como a inicialização deve ser feita (na sua classe Main.java):

// ... dependencias e imports ...

import javax.swing.SwingUtilities;

import Controller.ControllerAluno;

public class App {
    public static void main(String[] args) {
        // ...
        ControllerAluno controllerAluno = new ControllerAluno(null/* ... services ... */);

        SwingUtilities.invokeLater(() -> {
            // Passa o controller para a tela
            TelaPrincipal frame = new TelaPrincipal(); 
            frame.setVisible(true);
        });
    }
}