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

    val part1 = launch {
        org.geo.constantMultiplier(vector, 5, pipe1)
    }

    val part2 = launch {
        org.geo.sortingFunction(pipe1, pipe2)
    }

    val part3 = launch {
        org.geo.printADT(pipe2)
    }

    part1.join()
    part2.join()
    part3.join()

    pipe1.close()
    pipe2.close()

}
