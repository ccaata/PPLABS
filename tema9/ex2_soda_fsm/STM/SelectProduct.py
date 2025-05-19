from STM import SelectProductSTM


class SelectProduct:
    state_machine = None
    price = float

    def __init__(self):
        self.current_state = None

    def choose(self):
        product = input("""Select product:
                                    Coca-Cola:  5RON
                                    Pepsi:      5RON
                                    Sprite:     5RON
                """)
        return product


class CocaCola:
    state_machine = SelectProductSTM
    price = 5


class Pepsi:
    state_machine = SelectProductSTM
    price = 5


class Sprite:
    state_machine = SelectProductSTM
    price = 5


class State:
    pass
