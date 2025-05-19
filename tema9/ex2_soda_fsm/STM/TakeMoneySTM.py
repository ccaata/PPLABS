from STM import WaitingForClient
from STM.SelectProduct import State
from STM.WaitingForClient import InsertMoney


class TakeMoneySTM:
    wait_state: WaitingForClient
    insert_money_state: InsertMoney
    current_state: State
    money: int

    def __init__(self):
        money = 0
        self.current_state = WaitingForClient
        pass

    # se actualizeaza in momentul inserarii unei bancnote

    def add_money(self, value):
        self.money += value
        if self.money != 0:
            self.current_state = InsertMoney

    # se actualizeaza in momentul achizitionarii unui produs

    def update_amount_of_money(self, value):
        self.money += value




