class Component:
    """
    Clase base para todos los componentes adjuntables a un Node.
    Inspirado en el patrón GameObject/Component de Unity.
    """
    def __init__(self):
        self.node = None

    def update(self, delta_time: float) -> None:
        pass

    def render(self) -> None:
        pass
