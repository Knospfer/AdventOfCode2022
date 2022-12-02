import java.io.File

class PartOne {
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
                val player = fromMoveToScore(moves.last())

                totalScore += calculateMatch(opponent, player)
            }
        }

        return totalScore
    }

    private fun fromMoveToScore(move: String): Int {
        return when (move) {
            "A", "X" -> rockValue
            "B", "Y" -> paperValue
            "C", "Z" -> scissorValue
            else -> throw Error("Wrong Character!")
        }
    }

    private fun calculateMatch(opponent: Int, player: Int): Int {
        var roundScore = loseValue

        if (opponent == player) roundScore = drawValue
        if (player == rockValue && opponent == scissorValue) roundScore = winValue
        if (player == paperValue && opponent == rockValue) roundScore = winValue
        if (player == scissorValue && opponent == paperValue) roundScore = winValue

        return roundScore + player
    }
}
