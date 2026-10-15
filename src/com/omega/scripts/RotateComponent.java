package com.omega.scripts;

import com.omega.core.Component;

/**
 * Componente de prueba para verificar la actualización por delta_time.
 */
public class RotateComponent extends Component {
    private double speed;
    private double currentRotation;

    public RotateComponent(double speed) {
        this.speed = speed;
        this.currentRotation = 0.0;
    }

    @Override
    public void start() {
        System.out.println("[RotateComponent] Inicializado en el nodo: " + getNode().getName());
    }

    @Override
    public void update(double deltaTime) {
        currentRotation += speed * deltaTime;
        if (currentRotation >= 360.0) {
            currentRotation -= 360.0;
        }
    }

    public double getCurrentRotation() {
        return currentRotation;
    }
}
