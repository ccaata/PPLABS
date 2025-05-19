# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.

from collections import deque
import asyncio


async def gauss_summation(task_nr, n):
    suma = 0
    for i in range(0, n + 1):
        await asyncio.sleep(0.1)
        suma += i
    print("TASK ", task_nr, ": S(", n, ") = ", suma, "\n")
    return suma


async def main():
    queue = deque()
    queue.append(5)
    queue.append(7)
    queue.append(12)
    queue.append(15)

    await asyncio.gather(
        gauss_summation("A", queue.pop()),
        gauss_summation("B", queue.pop()),
        gauss_summation("C", queue.pop()),
        gauss_summation("D", queue.pop())
    )


if __name__ == '__main__':
    asyncio.run(main())
