package com.soberg.opcomparison

class OperationTest(testSizes: List<Int>) {

    private val opRunner = OperationRunner(testSizes)

    fun run() {
        singleMapTest()
    }

    private fun singleMapTest() {
        runTests(
            collectionOp = { values ->
                values.map { it * 10 }},
            sequenceOp = { values ->
                values.asSequence()
                .map { it * 10 }
                .toList() },
            opName = "single map"
        )
    }

    private fun runTests(
        collectionOp: Operation,
        sequenceOp: Operation,
        opName: String
    ) {
        val collection = opRunner.runOperation(collectionOp)
        val sequence = opRunner.runOperation(sequenceOp)
        collection.printResults(">> Collection - $opName")
        sequence.printResults(">> Sequence - $opName")
        println()
    }

    private fun List<OperationRunner.Result>.printResults(opName: String) {
        println(opName)
        forEach { result ->
            println("${result.collectionSize} : ${result.opTimeMillis}")
        }
    }
}