import time
from core.node import Node
from graphics.window import GraphicsWindow
from OpenGL.GL import glClear, glClearColor, GL_COLOR_BUFFER_BIT, GL_DEPTH_BUFFER_BIT

def main():
    window = GraphicsWindow(800, 600, "KoreEngine Prototype")
    
    # Escena principal (Nodo Raíz)
    root = Node("SceneRoot")
    
    # Ejemplo de nodo hijo
    player_node = Node("Player")
    root.add_child(player_node)

    last_time = time.time()

    # Bucle principal del motor
    while not window.should_close():
        current_time = time.time()
        delta_time = current_time - last_time
        last_time = current_time

        window.poll_events()

        # Inyección OpenGL: Limpieza de Pantalla
        glClearColor(0.1, 0.1, 0.15, 1.0)
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

        # Ciclo de vida del Árbol de Nodos
        root.update(delta_time)
        root.render()

        window.swap_buffers()

    window.terminate()

if __name__ == "__main__":
    main()
