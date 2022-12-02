  fun main(args: Array<String>) {
    println("Hello Day Two!")

    println("--- Part One----")
    val partOne = PartOne()
    println("Total score from current strategy: ${partOne.calculateTotalScore()}")

    println("--- Part Two----")
    val partTwo = PartTwo()
    println("Total score from current strategy: ${partTwo.calculateTotalScore()}")
}