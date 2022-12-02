fun main() {
    /**
     * Return sum of top elf
     */
    fun part1(input: List<String>): Int {
        return split(input)
            .maxOf { it.sumOf(String::toInt) }
    }

    /**
     * Return sum of top 3 elves
     */
    fun part2(input: List<String>): Int {
        return split(input)
            .map { it.sumOf(String::toInt) }
            .sortedByDescending { it }
            .take(3)
            .sum()
    }

    val testInput = readInput("Day01_input")

    val solutionPart1 = part1(testInput)
    println("Solution part 1: $solutionPart1")

    val solutionPart2 = part2(testInput)
    println("Solution part 2: $solutionPart2")
}

/**
 * Split a list of strings into a sequence of lists by using an empty string as the separator.
 */
fun split(input: List<String>): Sequence<List<String>> {
    return sequence {
        var index = 0
        do {
            val subList = mutableListOf<String>()
            do {
                val string = input[index]
                if (string != "") {
                    subList.add(string)
                }
                index++
            } while (string != "" && index < input.size)
            yield(subList)
        } while (index < input.size)
    }
}
