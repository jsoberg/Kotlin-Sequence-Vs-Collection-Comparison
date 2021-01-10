package com.soberg.opcomparison

import kotlin.math.pow

fun main(args: Array<String>) {
    val sizes = intArrayOf(3, 5, 6, 7)
        .map { exp -> 10.0.pow(exp).toInt() }
    val test = OperationTest(sizes)
    test.run()
}