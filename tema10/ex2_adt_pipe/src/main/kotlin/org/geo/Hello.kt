package org.geo

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.toList

suspend fun constantMultiplier(vector: List<Int>, alfa: Int, outputChannel: SendChannel<Int>) {
    vector.forEach() {
        outputChannel.send(it * alfa)
    }
    outputChannel.close()
}

suspend fun sortingFunction(inputChannel: ReceiveChannel<Int>, outputChannel: SendChannel<Int>) {
    val sorted = inputChannel.toList().sorted()
    sorted.forEach() {
        outputChannel.send(it)
    }
    outputChannel.close()
}

suspend fun printADT(inputChannel: ReceiveChannel<Int>) {
    inputChannel.toList().forEach() {
        println("$it ")
    }
}

suspend fun main() {
    val vector = listOf(21, 34, 12, 14, 7, 9, 81, 22)

    val pipe1 = Channel<Int>()
    val pipe2 = Channel<Int>()

    val part1 = CoroutineScope(Dispatchers.Default).launch {
        constantMultiplier(vector, 5, pipe1)
    }

    val part2 = CoroutineScope(Dispatchers.Default).launch {
        sortingFunction(pipe1, pipe2)
    }

    val part3 = CoroutineScope(Dispatchers.Default).launch {
        printADT(pipe2)
    }

    listOf(part1, part2, part3).joinAll()

    pipe1.close()
    pipe2.close()

}


