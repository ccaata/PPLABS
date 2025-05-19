from STM import TakeMoneySTM
from STM.SelectProduct import State


class WaitingForClient:
    state_machine: State

    def __init__(self):
        self.TakeMoneySTM = None

    def client_arrived(self):
        self.state_machine = self.TakeMoneySTM


class InsertMoney:
    state_machine = TakeMoneySTM

    def insert_10bani(self):
        pass

    def insert_50bani(self):
        pass

    def insert_1leu(self):
        pass

    def insert_5lei(self):
        pass

    def insert_10lei(self):
        pass
