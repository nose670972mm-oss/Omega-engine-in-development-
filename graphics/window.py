import glfw
from OpenGL.GL import *

class GraphicsWindow:
    """
    Wrapper del contexto de ventana OpenGL que expone el pipeline
    para inyecciones directas de código nativo PyOpenGL.
    """
    def __init__(self, width: int = 800, height: int = 600, title: str = "KoreEngine Core"):
        if not glfw.init():
            raise RuntimeError("Error al inicializar GLFW")

        self.window = glfw.create_window(width, height, title, None, None)
        if not self.window:
            glfw.terminate()
            raise RuntimeError("Error al crear la ventana GLFW")

        glfw.make_context_current(self.window)
        glViewport(0, 0, width, height)

    def should_close(self) -> bool:
        return glfw.window_should_close(self.window)

    def poll_events(self) -> None:
        glfw.poll_events()

    def swap_buffers(self) -> None:
        glfw.swap_buffers(self.window)

    def terminate(self) -> None:
        glfw.terminate()
