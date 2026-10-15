import com.omega.core.Node;
import com.omega.graphics.EditorWindow;
import com.omega.graphics.LauncherDialog;
import com.omega.scripts.RotateComponent;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        // 1. Launcher inicial de Proyecto
        LauncherDialog launcher = new LauncherDialog(null);
        launcher.setVisible(true);

        if (!launcher.isConfirmed()) {
            System.out.println("[Omega Engine] Inicialización cancelada por el usuario.");
            System.exit(0);
        }

        String projectName = launcher.getProjectName();
        System.out.println("[Omega Engine] Proyecto cargado: " + projectName);

        // 2. Escena inicial
        Node root = new Node("SceneRoot");
        
        Node player = new Node("PlayerNode");
        player.transform.x = -80.0;
        player.transform.y = -40.0;

        Node sprite = new Node("Sprite2D");
        sprite.transform.x = 160.0;
        sprite.transform.y = 80.0;

        root.addChild(player);
        root.addChild(sprite);

        // Inyección de componente de prueba
        RotateComponent rotateComp = new RotateComponent(45.0);
        player.addComponent(rotateComp);

        // 3. Ventana del Editor
        SwingUtilities.invokeLater(() -> {
            EditorWindow editor = new EditorWindow(projectName, root);
            editor.setVisible(true);

            Timer loopTimer = new Timer(16, e -> {
                root.update(0.016);
                editor.refreshViewport();
            });
            loopTimer.start();
        });
    }
}
