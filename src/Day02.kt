fun main() {
    val testInput = readInput("Day02_input")

    val solutionPart1 = part1(testInput)
    println("Solution part 1: $solutionPart1")

    val solutionPart2 = part2(testInput)
    println("Solution part 2: $solutionPart2")
}

/**
 * Total score strategy 1
 */
private fun part1(input: List<String>): Int {
    val opponentActions = mapOf(
        "A" to RockPaperScissorsAction.Rock,
        "B" to RockPaperScissorsAction.Paper,
        "C" to RockPaperScissorsAction.Scissors
    )
    val counterActions = mapOf(
        "X" to CounterAction(RockPaperScissorsAction.Rock, 1),
        "Y" to CounterAction(RockPaperScissorsAction.Paper, 2),
        "Z" to CounterAction(RockPaperScissorsAction.Scissors, 3),
    )

    return input.sumOf { games ->
        val (opponentAction, counterAction) = games.split(" ")
            .let { game -> opponentActions.getValue(game[0]) to counterActions.getValue(game[1]) }

        val gameResult = determineGameResult(opponentAction, counterAction.action)
        gameResult.points + counterAction.points
    }
}


/**
 * Total score strategy 2
 */
private fun part2(input: List<String>): Int {
    val opponentActions = mapOf(
        "A" to RockPaperScissorsAction.Rock,
        "B" to RockPaperScissorsAction.Paper,
        "C" to RockPaperScissorsAction.Scissors
    )
    val gameResults = mapOf(
        "X" to RockPaperScissorsGameResult.Loss,
        "Y" to RockPaperScissorsGameResult.Draw,
        "Z" to RockPaperScissorsGameResult.Win,
    )

    return input.sumOf { games ->
        val (opponentAction, gameResult) = games.split(" ")
            .let { game ->
                opponentActions.getValue(game[0]) to gameResults.getValue(game[1])
            }

        val counterAction = determineCounterAction(gameResult, opponentAction)
        gameResult.points + counterAction.points
    }
}


private fun determineGameResult(
    opponentAction: RockPaperScissorsAction,
    ownAction: RockPaperScissorsAction,
): RockPaperScissorsGameResult {
    return when {
        opponentAction == ownAction -> RockPaperScissorsGameResult.Draw
        opponentAction.defeats(ownAction) -> RockPaperScissorsGameResult.Loss
        else -> RockPaperScissorsGameResult.Win
    }
}

private fun determineCounterAction(
    gameResult: RockPaperScissorsGameResult,
    opponentsAction: RockPaperScissorsAction,
): RockPaperScissorsAction {
    return when (gameResult) {
        RockPaperScissorsGameResult.Draw -> opponentsAction
        RockPaperScissorsGameResult.Win -> RockPaperScissorsAction.values().first { it.defeats(opponentsAction) }
        else -> RockPaperScissorsAction.values().first { opponentsAction.defeats(it) }
    }
}

private data class CounterAction(val action: RockPaperScissorsAction, val points: Int)

private enum class RockPaperScissorsAction(val points: Int) {
    Rock(1),
    Paper(2),
    Scissors(3);

    fun defeats(other: RockPaperScissorsAction): Boolean {
        return when (this) {
            Scissors -> other == Paper
            Paper -> other == Rock
            Rock -> other == Scissors
        }
    }
}

private enum class RockPaperScissorsGameResult(val points: Int) {
    Draw(3),
    Win(6),
    Loss(0);
}
