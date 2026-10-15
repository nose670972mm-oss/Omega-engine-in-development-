package com.omega.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Nodo base para la jerarquía del árbol de escena de Omega Engine.
 */
public class Node {
    private String name;
    private Node parent;
    private final List<Node> children;
    private final List<Component> components;
    public Transform2D transform;

    public Node(String name) {
        this.name = name;
        this.parent = null;
        this.children = new ArrayList<>();
        this.components = new ArrayList<>();
        this.transform = new Transform2D();
    }

    // --- Gestión de Jerarquía ---
    public void addChild(Node child) {
        if (child.parent != null) {
            child.parent.removeChild(child);
        }
        child.parent = this;
        this.children.add(child);
    }

    public void removeChild(Node child) {
        if (this.children.remove(child)) {
            child.parent = null;
        }
    }

    public List<Node> getChildren() {
        return Collections.unmodifiableList(children);
    }

    // --- Gestión de Componentes ---
    public void addComponent(Component component) {
        component.setNode(this);
        this.components.add(component);
        component.start();
    }

    @SuppressWarnings("unchecked")
    public <T extends Component> T getComponent(Class<T> componentClass) {
        for (Component comp : components) {
            if (componentClass.isInstance(comp)) {
                return (T) comp;
            }
        }
        return null;
    }

    // --- Ciclo de Vida ---
    public void update(double deltaTime) {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).update(deltaTime);
        }
        for (int i = 0; i < children.size(); i++) {
            children.get(i).update(deltaTime);
        }
    }

    public void render() {
        for (int i = 0; i < components.size(); i++) {
            components.get(i).render();
        }
        for (int i = 0; i < children.size(); i++) {
            children.get(i).render();
        }
    }

    // --- Getters y Setters ---
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Node getParent() { return parent; }

    @Override
    public String toString() {
        return name; // Utilizado directamente por JTree en la interfaz gráfica
    }
}
