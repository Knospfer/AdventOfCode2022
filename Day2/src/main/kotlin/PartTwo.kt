import java.io.File

class PartTwo {
    private val rockValue = 1
    private val paperValue = 2
    private val scissorValue = 3

    private val loseValue = 0
    private val drawValue = 3
    private val winValue = 6

    fun calculateTotalScore(): Int {
        var totalScore = 0
        File("src/main/resources/strategy.txt").useLines {
            it.toList().forEach { row ->
                val moves = row.split(" ")
                val opponent = fromMoveToScore(moves.first())

                totalScore += calculateMatch(opponent, moves.last())
            }
        }

        return totalScore
    }

    private fun fromMoveToScore(move: String): Int {
        return when (move) {
            "A" -> rockValue
            "B" -> paperValue
            "C" -> scissorValue
            else -> throw Error("Wrong Character!")
        }
    }

    private fun fromResultToScore(move: String): Int {
        return when (move) {
            "X" -> loseValue
            "Y" -> drawValue
            "Z" -> winValue
            else -> throw Error("Wrong Character!")
        }
    }

    private fun calculateMatch(opponent: Int, player: String): Int {
        val roundScore = fromResultToScore(player)
        val moveScore = when (player) {
            "X" -> looseCondition(opponent)
            "Y" -> opponent
            "Z" -> winCondition(opponent)
            else -> 0
        }

        return roundScore + moveScore
    }

    private fun winCondition(opponent: Int): Int {
        return when (opponent) {
            rockValue -> paperValue
            paperValue -> scissorValue
            scissorValue -> rockValue
            else -> 0
        }
    }

    private fun looseCondition(opponent: Int): Int {
        return when (opponent) {
            rockValue -> scissorValue
            paperValue -> rockValue
            scissorValue -> paperValue
            else -> 0
        }
    }
}