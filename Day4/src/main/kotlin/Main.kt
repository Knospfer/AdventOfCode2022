import java.io.File

fun main(args: Array<String>) {
    println("Hello Day Four!")
    println("---Part One---")
    println("Number of overlaps: ${fromFiltToPairOverlapsExclusive()}")
    println("---Part Two---")
    println("Number of overlaps: ${fromFiltToPairOverlapsInclusive()}")
}

//Part One
fun fromFiltToPairOverlapsExclusive(): Int {
    var result = 0
    File("src/main/resources/pairs.txt").useLines {
        it.toList().forEach { value ->
            val pairs = value.split(',')
            if (doesPairsOverlapExclusive(pairs.first(), pairs.last()))
                result++
        }
    }

    return result
}

fun doesPairsOverlapExclusive(first: String, second: String): Boolean {
    val firstRange = first.split('-').toList().map { value -> value.toInt() }
    val secondRange = second.split('-').toList().map { value -> value.toInt() }

    val firstCointainsSecond = checkExtremesExclusive(firstRange, secondRange)
    val secondContainsFirst = checkExtremesExclusive(secondRange, firstRange)

    return firstCointainsSecond || secondContainsFirst
}

fun checkExtremesExclusive(firstRange: List<Int>, lastRange: List<Int>): Boolean {
    return firstRange.first() <= lastRange.first() && firstRange.last() >= lastRange.last()
}

//Part Two
fun fromFiltToPairOverlapsInclusive(): Int {
    var result = 0
    File("src/main/resources/pairs.txt").useLines {
        it.toList().forEach { value ->
            val pairs = value.split(',')
            if (doesPairsOverlapInclusive(pairs.first(), pairs.last()))
                result++
        }
    }

    return result
}

fun doesPairsOverlapInclusive(first: String, second: String): Boolean {
    val firstRange = first.split('-').toList().map { value -> value.toInt() }
    val secondRange = second.split('-').toList().map { value -> value.toInt() }

    val firstCointainsSecond = checkExtremesInclusive(firstRange, secondRange)
    val secondContainsFirst = checkExtremesInclusive(secondRange, firstRange)

    return firstCointainsSecond || secondContainsFirst
}

fun checkExtremesInclusive(firstRange: List<Int>, lastRange: List<Int>): Boolean {
    var result = false
    lastRange.forEach {
        val lowerBound = firstRange.first()
        val uppperBound = firstRange.last()
        if (it in lowerBound..uppperBound) result = true
    }

    return  result
}
