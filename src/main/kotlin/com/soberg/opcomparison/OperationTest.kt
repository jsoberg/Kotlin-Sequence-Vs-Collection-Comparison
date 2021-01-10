package com.soberg.opcomparison

class OperationTest(testSizes: List<Int>) {

    private val opRunner = OperationRunner(testSizes)

    fun run() {
        singleMapTest(opRunner)
    }

    private fun singleMapTest(opRunner: OperationRunner) {
        opRunner.runOperation { values ->
            values.map { it * 10 }
        }.printResults(">> Collection - single map")

        opRunner.runOperation { values ->
            values.asSequence()
                .map { it * 10 }
                .toList()
        }.printResults(">> Sequence - single map")
        println()
    }

    private fun List<OperationRunner.Result>.printResults(opName: String) {
        println(opName)
        forEach { result ->
            println("${result.collectionSize} : ${result.opTimeMillis}")
        }
    }
}