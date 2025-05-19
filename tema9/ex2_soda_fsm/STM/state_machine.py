from abc import ABC, abstractmethod
from typing import Dict, Any

class State(ABC):
    @abstractmethod
    def enter(self) -> None:
        pass

    @abstractmethod
    def exit(self) -> None:
        pass

    @abstractmethod
    def handle(self, event: str, data: Any = None) -> None:
        pass

class StateMachine:
    def __init__(self):
        self._states: Dict[str, State] = {}
        self._current_state: State = None
        self._initial_state: str = None

    def add_state(self, name: str, state: State) -> None:
        self._states[name] = state
        if not self._initial_state:
            self._initial_state = name

    def set_initial_state(self, name: str) -> None:
        if name in self._states:
            self._initial_state = name

    def start(self) -> None:
        if self._initial_state:
            self._current_state = self._states[self._initial_state]
            self._current_state.enter()

    def handle_event(self, event: str, data: Any = None) -> None:
        if self._current_state:
            self._current_state.handle(event, data) 