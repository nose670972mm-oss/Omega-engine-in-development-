from typing import List, Optional

class Node:
    """
    Nodo base para construir la jerarquía del árbol de la escena.
    Inspirado en la arquitectura de Godot.
    """
    def __init__(self, name: str = "Node"):
        self.name: str = name
        self.parent: Optional['Node'] = None
        self.children: List['Node'] = []
        self.components: List[object] = []

    def add_child(self, child: 'Node') -> None:
        child.parent = self
        self.children.append(child)

    def add_component(self, component: object) -> None:
        self.components.append(component)
        if hasattr(component, 'node'):
            component.node = self

    def update(self, delta_time: float) -> None:
        # Actualiza componentes
        for comp in self.components:
            if hasattr(comp, 'update'):
                comp.update(delta_time)
        
        # Propaga la actualización a los hijos
        for child in self.children:
            child.update(delta_time)

    def render(self) -> None:
        # Ejecuta render de componentes
        for comp in self.components:
            if hasattr(comp, 'render'):
                comp.render()

        # Propaga el renderizado a los hijos
        for child in self.children:
            child.render()
