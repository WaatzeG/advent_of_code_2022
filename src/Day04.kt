fun main() {
    val testInput = readInput("Day04_input")

    val solutionPart1 = part1(testInput)
    println("Solution part 1: $solutionPart1")

    val solutionPart2 = part2(testInput)
    println("Solution part 2: $solutionPart2")
}

/**
 * Count of fully overlapping schedules
 */
private fun part1(input: List<String>): Int {
    return splitToRanges(input).count {
        (it.first - it.second).isEmpty() || (it.second - it.first).isEmpty()
    }
}

/**
 * Count of overlapping schedules
 */
private fun part2(input: List<String>): Int {
    return splitToRanges(input).count {
        (it.first - it.second).count() < it.first.count() || (it.second - it.first).count() < it.second.count()
    }
}

private fun splitToRanges(lines: List<String>): List<Pair<IntRange, IntRange>> {
    return lines.map { line ->
        line.split(",").let { ranges ->
            ranges.map {
                it.split("-").let {
                    IntRange(it[0].toInt(), it[1].toInt())
                }
            }
        }.let {
            it[0] to it[1]
        }
    }
}
