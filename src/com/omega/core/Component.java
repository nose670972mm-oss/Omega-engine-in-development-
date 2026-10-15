package com.omega.core;

/**
 * Clase base abstracta para todos los componentes adjuntables a un Node.
 * Define el ciclo de vida básico al estilo Unity.
 */
public abstract class Component {
    protected Node node;

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    /**
     * Se ejecuta una sola vez al adjuntar el componente al nodo.
     */
    public void start() {}

    /**
     * Se ejecuta en cada frame para procesar la lógica de juego.
     * @param deltaTime Tiempo transcurrido desde el último frame en segundos.
     */
    public abstract void update(double deltaTime);

    /**
     * Se ejecuta en cada frame para inyectar o emitir llamadas de renderizado.
     */
    public void render() {}
}
