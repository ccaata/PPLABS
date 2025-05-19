# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.

from vending_machine import TakeMoneySTM, SelectProductSTM, VendingMachineSTM
from observer import MoneyDisplayObserver, ProductSelectionObserver

def main():
    # Create state machines
    take_money_stm = TakeMoneySTM()
    select_product_stm = SelectProductSTM(take_money_stm)
    vending_machine_stm = VendingMachineSTM()

    # Create and attach observers
    money_display = MoneyDisplayObserver()
    product_selection = ProductSelectionObserver(vending_machine_stm)

    take_money_stm.add_observer(money_display)
    select_product_stm.add_observer(product_selection)

    # Simulate vending machine operation
    print("\n=== Vending Machine Demo ===")
    
    # Insert money
    take_money_stm.handle_event("insert_money", 5.00)
    take_money_stm.handle_event("insert_money", 2.50)

    # Select product
    select_product_stm.handle_event("select_product", {
        'product_id': 'A1',
        'price': 6.00
    })

    # Try to select another product
    select_product_stm.handle_event("select_product", {
        'product_id': 'B2',
        'price': 2.00
    })

    # Return remaining money
    take_money_stm.handle_event("return_money")

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    main()

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
