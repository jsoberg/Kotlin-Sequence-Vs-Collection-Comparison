package com.soberg.opcomparison

class OperationTest(testSizes: List<Int>) {

    private val opRunner = OperationRunner(testSizes)

    fun run() {
        singleMapTest()
        doubleMapTest()

        singleFilterTest()
        doubleFilterTest()

        singleFilterSingleMapTest()
        doubleFilterDoubleMapTest()
    }

    private fun singleMapTest() {
        runTests(
            collectionOp = { values ->
                values.map { it * 10 }},
            sequenceOp = { values ->
                values.asSequence()
                .map { it * 10 }
                .toList() },
            opName = "1 map"
        )
    }

    private fun doubleMapTest() {
        runTests(
            collectionOp = { values ->
                values.map { it * 10 }
                    .map { it / 2 }},
            sequenceOp = { values ->
                values.asSequence()
                    .map { it * 10 }
                    .map { it / 2 }
                    .toList() },
            opName = "2 maps"
        )
    }

    private fun singleFilterTest() {
        runTests(
            collectionOp = { values ->
                values.filter { it % 2 == 0 }},
            sequenceOp = { values ->
                values.asSequence()
                    .filter { it % 2 == 0 }
                    .toList() },
            opName = "1 filter"
        )
    }

    private fun doubleFilterTest() {
        runTests(
            collectionOp = { values ->
                values.filter { it % 2 == 0 }
                    .filter { it % 6 == 0 }},
            sequenceOp = { values ->
                values.asSequence()
                    .filter { it % 2 == 0 }
                    .filter { it % 6 == 0 }
                    .toList() },
            opName = "2 filters"
        )
    }

    private fun singleFilterSingleMapTest() {
        runTests(
            collectionOp = { values ->
                values.filter { it % 2 == 0 }
                    .map { it / 2 }},
            sequenceOp = { values ->
                values.asSequence()
                    .filter { it % 2 == 0 }
                    .map { it / 2 }
                    .toList() },
            opName = "1 filter, 1 map"
        )
    }

    private fun doubleFilterDoubleMapTest() {
        runTests(
            collectionOp = { values ->
                values.filter { it % 2 == 0 }
                    .filter { it % 6 == 0 }
                    .map { it / 2 }
                    .map { it * 3 }},
            sequenceOp = { values ->
                values.asSequence()
                    .filter { it % 2 == 0 }
                    .filter { it % 6 == 0 }
                    .map { it / 2 }
                    .map { it * 3 }
                    .toList() },
            opName = "2 filters, 2 maps"
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
            println("${result.collectionSize} : ${result.opTimeMillis}ms")
        }
    }

}