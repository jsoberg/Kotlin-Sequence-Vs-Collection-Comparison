package com.soberg.opcomparison

import kotlin.random.Random
import kotlin.system.measureTimeMillis

typealias Operation = (Collection<Int>) -> Unit

class OperationRunner(
    private val collectionSizes: List<Int>
) {
    private val random = Random.Default

    /** Creates a [Collection] for each value in [collectionSizes], and calculates the time it takes to run [op].
     * @return a List of [Result]s for running each operation at each size. */
    fun runOperation(op: Operation): List<Result> =
        collectionSizes.map { size ->
            val values = createListOfSize(size)
            val time = measureTimeMillis { op(values) }
            Result(size, time)
        }

    private fun createListOfSize(size: Int): Collection<Int> =
        (0 until size).map { random.nextInt() }

    data class Result(
        val collectionSize: Int,
        val opTimeMillis: Long
    )
}