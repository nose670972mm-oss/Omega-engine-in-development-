package com.omega.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Nodo base para construir la jerarquía del árbol de la escena.
 * Combina la jerarquía Padre-Hijo de Godot con el patrón Entidad-Componente de Unity.
 */
public class Node {
    private String name;
    private Node parent;
    private final List<Node> children;
    private final List<Component> components;

    public Node(String name) {
        this.name = name;
        this.parent = null;
        this.children = new ArrayList<>();
        this.components = new ArrayList<>();
    }

    // --- Gestión de Jerarquía (Godot Style) ---
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

    // --- Gestión de Componentes (Unity Style) ---
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

    // --- Ciclo de Vida del Motor ---
    public void update(double deltaTime) {
        // 1. Actualizar componentes locales
        for (int i = 0; i < components.size(); i++) {
            components.get(i).update(deltaTime);
        }

        // 2. Propagar actualización a nodos hijos
        for (int i = 0; i < children.size(); i++) {
            children.get(i).update(deltaTime);
        }
    }

    public void render() {
        // 1. Renderizar componentes locales
        for (int i = 0; i < components.size(); i++) {
            components.get(i).render();
        }

        // 2. Propagar renderizado a nodos hijos
        for (int i = 0; i < children.size(); i++) {
            children.get(i).render();
        }
    }

    // --- Getters & Setters ---
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getParent() {
        return parent;
    }
}
