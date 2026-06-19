import java.awt.EventQueue;
import controller.POSController;
import view.MainSCream;

public class Main {
    public static void main(String[] args) {
        // Força o Java a procurar os recursos (imagens) a partir do diretório do classpath
        System.setProperty("user.dir", System.getProperty("user.dir"));

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // 1. Inicializa o controlador (ele carrega o estoque/arquivos)
                    POSController controller = new POSController();
                    
                    // 2. Inicializa a tela passando o controlador criado
                    MainSCream frame = new MainSCream(controller);
                    
                    // 3. Torna a tela visível
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
