package com.omega.core;

/**
 * Gestiona la transformación 2D (Posición, Rotación y Escala) para los Nodos.
 */
public class Transform2D {
    public double x;
    public double y;
    public double rotation; // En grados
    public double scaleX;
    public double scaleY;

    public Transform2D() {
        this.x = 0.0;
        this.y = 0.0;
        this.rotation = 0.0;
        this.scaleX = 1.0;
        this.scaleY = 1.0;
    }

    public Transform2D(double x, double y) {
        this();
        this.x = x;
        this.y = y;
    }
}
