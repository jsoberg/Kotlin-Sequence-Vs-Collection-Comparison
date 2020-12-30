package com.soberg.opcomparison

import kotlin.math.pow

fun main(args: Array<String>) {
    val sizes = intArrayOf(2, 4, 5, 6)
        .map { exp -> 10.0.pow(exp).toInt() }
    val opRunner = OperationRunner(sizes)
    val results = opRunner.runOperation { values ->
        values.asSequence()
            .map { it * 10 }
            .map { it + 10 }
            .map { it - 2 }
    }
    results.forEach {
        println("${it.collectionSize} : ${it.opTimeMillis}")
    }
}