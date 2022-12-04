import java.io.File

fun main(args: Array<String>) {
    println("Hello Day Three!")
    println("---Part One---")
    println("Total Value: ${parseFileIntoRucksack()}")
    println("---Part Two---")
    println("Total Value: ${parseFileIntoThreeLineRucksack()}")
}

//Day One
fun parseFileIntoRucksack(): Int {
    var result = 0

    File("src/main/resources/rucksack.txt").useLines {
        it.toList().forEach { value ->
            val itemFound = findDoubleItemInSignleRow(value)
            result += fromCharToValue(itemFound)
        }
    }

    return result
}

fun findDoubleItem(row: List<Char>): Char {
    for (i in row.indices) {
        if (i + 1 == row.size) break;

        val currentValue = row[i]
        val nextValue = row[i + 1]

        if (currentValue == nextValue) return currentValue;
    }

    throw Error("Character not found")
}

//Day Two
fun parseFileIntoThreeLineRucksack(): Int {
    var result = 0
    val parsedLines = mutableListOf<String>()

    File("src/main/resources/rucksack.txt").useLines {
        it.toList().forEach { line ->
            parsedLines.add(line)
        }
    }

    (parsedLines.indices step 3).forEach { index ->
        if (index + 2 < parsedLines.size) {

            val firstRow = parsedLines[index].toSet().toList()
            val secondRow = parsedLines[index + 1].toSet().toList()
            val thirdRow = parsedLines[index + 2].toSet().toList()
            val sortedTotalRow = (firstRow + secondRow + thirdRow).sorted()

            val foundItem = findTripleItem(sortedTotalRow)

            result += fromCharToValue(foundItem)
        }
    }

    return result
}

fun findTripleItem(row: List<Char>): Char {
    for (i in row.indices) {
        if (i >= 1 && i < row.size - 1) {
            val previousValue = row[i - 1]
            val currentValue = row[i]
            val nextValue = row[i + 1]

            val allValuesMatches = currentValue == nextValue && currentValue == previousValue
            if (allValuesMatches) return currentValue
        }
    }
    throw  Error("Character not found")
}

//Utility Functions
fun findDoubleItemInSignleRow(row: String): Char {
    val rowLength = row.length + 1
    val firstHalf = row.toList().subList(0, rowLength / 2).toSet().toList()
    val secondHalf = row.toList().subList(rowLength / 2, row.length).toSet().toList()

    val unifiedRowWithoutDoubles = (firstHalf + secondHalf).sorted()

    return findDoubleItem(unifiedRowWithoutDoubles)
}

fun fromCharToValue(c: Char): Int {
    if (c in 'a'..'z') return c.code - 96;
    if (c in 'A'..'Z') return c.code - 38

    throw Error("Invalid Character")
}
