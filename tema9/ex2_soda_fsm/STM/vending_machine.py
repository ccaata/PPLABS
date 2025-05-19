from typing import List, Dict
from state_machine import State, StateMachine
from observer import Observer

class TakeMoneyState(State):
    def __init__(self, machine):
        self._machine = machine
        self._observers: List[Observer] = []

    def add_observer(self, observer: Observer) -> None:
        self._observers.append(observer)

    def notify_observers(self, data: float) -> None:
        for observer in self._observers:
            observer.update(data)

    def enter(self) -> None:
        print("Ready to accept money")

    def exit(self) -> None:
        pass

    def handle(self, event: str, data: float = None) -> None:
        if event == "insert_money":
            self._machine.current_money += data
            self.notify_observers(self._machine.current_money)
        elif event == "return_money":
            print(f"Returning ${self._machine.current_money:.2f}")
            self._machine.current_money = 0
            self.notify_observers(0)

class SelectProductState(State):
    def __init__(self, machine):
        self._machine = machine
        self._observers: List[Observer] = []

    def add_observer(self, observer: Observer) -> None:
        self._observers.append(observer)

    def notify_observers(self, data: dict) -> None:
        for observer in self._observers:
            observer.update(data)

    def enter(self) -> None:
        print("Ready to select product")

    def exit(self) -> None:
        pass

    def handle(self, event: str, data: dict = None) -> None:
        if event == "select_product":
            product_id = data.get('product_id')
            price = data.get('price')
            if self._machine.current_money >= price:
                self.notify_observers({'product_id': product_id, 'price': price})
            else:
                print("Not enough money!")

class VendingMachineState(State):
    def __init__(self, machine):
        self._machine = machine

    def enter(self) -> None:
        print("Vending machine ready")

    def exit(self) -> None:
        pass

    def handle(self, event: str, data: dict = None) -> None:
        if event == "product_selected":
            product_id = data.get('product_id')
            price = data.get('price')
            self._machine.current_money -= price
            print(f"Dispensing product {product_id}")
            print(f"Remaining money: ${self._machine.current_money:.2f}")

class TakeMoneySTM(StateMachine):
    def __init__(self):
        super().__init__()
        self.current_money = 0
        self._state = TakeMoneyState(self)
        self.add_state("take_money", self._state)
        self.set_initial_state("take_money")
        self.start()

    def add_observer(self, observer: Observer) -> None:
        self._state.add_observer(observer)

class SelectProductSTM(StateMachine):
    def __init__(self, take_money_stm):
        super().__init__()
        self._take_money_stm = take_money_stm
        self._state = SelectProductState(self)
        self.add_state("select_product", self._state)
        self.set_initial_state("select_product")
        self.start()

    @property
    def current_money(self):
        return self._take_money_stm.current_money

    def add_observer(self, observer: Observer) -> None:
        self._state.add_observer(observer)

class VendingMachineSTM(StateMachine):
    def __init__(self):
        super().__init__()
        self.current_money = 0
        self._state = VendingMachineState(self)
        self.add_state("vending_machine", self._state)
        self.set_initial_state("vending_machine")
        self.start()

    def handle_product_selection(self, product_id: str, price: float) -> None:
        self.handle_event("product_selected", {'product_id': product_id, 'price': price}) 