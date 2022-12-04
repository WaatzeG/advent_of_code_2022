fun main() {
    val testInput = readInput("Day03_input")

    val solutionPart1 = part1(testInput)
    println("Solution part 1: $solutionPart1")

    val solutionPart2 = part2(testInput)
    println("Solution part 2: $solutionPart2")
}

/**
 * Total score of overlap
 */
private fun part1(input: List<String>): Int {
    return input.sumOf { ruckSackContents ->
        ruckSackContents
            .chunkedSequence(ruckSackContents.length / 2)
            .map(String::toSet)
            .reduce { acc, s -> acc.intersect(s) }
            .let { String(it.toCharArray()) }
            .let { getValue(it) }
    }
}

/**
 * Total score of overlap per 3 elves
 */
private fun part2(input: List<String>): Int {
    return input
        .chunked(3)
        .sumOf { group ->
            group
                .map(String::toSet)
                .reduce { acc, s -> acc.intersect(s) }
                .let { String(it.toCharArray()) }
                .let { getValue(it) }
        }
}

private const val lowercaseOffset = 'a'.code - 1
private const val uppercaseOffset = 'A'.code - 1

private fun getValue(priority: String): Int {
    return priority.sumOf {
        if (it.isLowerCase()) {
            it.code - lowercaseOffset
        } else {
            it.code - uppercaseOffset + 26
        }
    }
}
