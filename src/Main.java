import com.omega.core.Node;
import com.omega.scripts.RotateComponent;

/**
 * Punto de entrada principal para Omega Engine (Fase de Pruebas de Arquitectura).
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   OMEGA ENGINE - ARCHITECTURE VERIFICATION");
        System.out.println("   Developer: Isaías | Language: Java");
        System.out.println("==========================================\n");

        // 1. Crear Jerarquía de Escena (Árbol de Nodos)
        Node root = new Node("SceneRoot");
        Node player = new Node("PlayerNode");
        Node weapon = new Node("WeaponNode");

        // 2. Conectar Jerarquía (Godot Style)
        root.addChild(player);
        player.addChild(weapon);

        // 3. Inyectar Componentes de Lógica (Unity Style)
        RotateComponent rotateBehavior = new RotateComponent(90.0); // 90 grados/seg
        player.addComponent(rotateBehavior);

        System.out.println("\n[Escena] Estructura creada con éxito:");
        System.out.println(" - " + root.getName());
        System.out.println("   └── " + player.getName());
        System.out.println("       └── " + weapon.getName() + "\n");

        // 4. Simulación del Bucle Principal (Game Loop Simulado)
        double simulatedDeltaTime = 0.016667; // ~60 FPS (16.6 ms por frame)
        System.out.println("[Game Loop] Ejecutando simulación de 5 frames:");

        for (int frame = 1; frame <= 5; frame++) {
            root.update(simulatedDeltaTime);
            root.render();

            System.out.printf(" Frame %d | Rotación acumulada en '%s': %.2f°%n",
                    frame, player.getName(), rotateBehavior.getCurrentRotation());
        }

        System.out.println("\n[Omega Engine] Simulación completada exitosamente.");
    }
}
