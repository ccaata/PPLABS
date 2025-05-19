from STM import SelectProduct
from STM.SelectProduct import CocaCola, Pepsi, Sprite, State


class SelectProductSTM:
    select_product_state: SelectProduct  # state-ul initial
    coca_cola_state: CocaCola  # state-ul in care alegem coca-cola
    pepsi_state: Pepsi  # state-ul in care alegem pepsi
    sprite_state: Sprite  # state-ul in care alegem sprite
    current_state: State

    def __init__(self):
        self.current_state = self.select_product_state

    def choose_another_product(self):
        input = self.select_product_state.choose()
        if input is "Coca-Cola":
            self.current_state = self.coca_cola_state
        elif input is "Pepsi":
            self.current_state = self.pepsi_state
        elif input is "Sprite":
            self.current_state = self.sprite_state
