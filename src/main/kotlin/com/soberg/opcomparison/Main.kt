package com.soberg.opcomparison

import com.soberg.opcomparison.OperationRunner.Result
import kotlin.math.pow

fun main(args: Array<String>) {
    val sizes = intArrayOf(3, 5, 6, 7)
        .map { exp -> 10.0.pow(exp).toInt() }
    val opRunner = OperationRunner(sizes)

    singleMapTest(opRunner)
}

private fun singleMapTest(opRunner: OperationRunner) {
    opRunner.runOperation { values ->
        values.map { it * 10 }
    }.printResults("Collection - single map")

    opRunner.runOperation { values ->
        values.asSequence()
            .map { it * 10 }
            .toList()
    }.printResults("Sequence - single map")
}

private fun List<Result>.printResults(opName: String) {
    println(opName)
    forEach { result ->
        println("${result.collectionSize} : ${result.opTimeMillis}")
    }
    println()
}