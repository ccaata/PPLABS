from STM import TakeMoneySTM, SelectProductSTM


class VendingMachineSTM:
    take_money_stm: TakeMoneySTM
    select_product_stm: SelectProductSTM

    def __init__(self):
        self.take_money_stm = TakeMoneySTM
        self.select_product_stm = SelectProductSTM

    def proceed_to_checkout(self):
        pass