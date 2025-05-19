from abc import ABC, abstractmethod
from typing import Any

class Observer(ABC):
    @abstractmethod
    def update(self, data: Any) -> None:
        pass

class MoneyDisplayObserver(Observer):
    def update(self, data: float) -> None:
        print(f"Current money inserted: ${data:.2f}")

class ProductSelectionObserver(Observer):
    def __init__(self, vending_machine):
        self._vending_machine = vending_machine

    def update(self, data: dict) -> None:
        product_id = data.get('product_id')
        price = data.get('price')
        self._vending_machine.handle_product_selection(product_id, price) 