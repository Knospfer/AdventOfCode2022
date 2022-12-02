import java.io.File

fun main(args: Array<String>) {
    println("Hello Day One!")

    println("---- Part One ----")
    val calories = parseFileIntoTotalCaloriesList()
    println("Maximum calories value: ${calories.max()}")

    println("---- Part Two ----")
    val sortedCalories = calories.sortedDescending()
    val first = sortedCalories[0]
    val second = sortedCalories[1]
    val third = sortedCalories[2]
    println(
        "Top 3 calories value: \n" +
                "1. $first\n" +
                "2. $second\n" +
                "3. $third\n" +
                "Total: ${first + second + third}"
    )
}

fun parseFileIntoTotalCaloriesList(): MutableList<Int> {
    val calories = mutableListOf(0)

    File("src/main/resources/calories.txt").useLines {
        it.toList().forEach { value ->
            if (value == "") calories.add(0)
            else {
                var currentCalory = calories.last()
                currentCalory += value.toInt()
                calories[calories.size - 1] = currentCalory
            }
        }
    }

    return calories
}