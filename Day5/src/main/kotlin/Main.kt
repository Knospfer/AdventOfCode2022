import java.io.File

fun main(args: Array<String>) {
    println("Hello Day Five!")
    println("---Part One---")
    println("Final result: ${fromFileToOrderedStacks()}")
    println("---Part Two---")
    println("Final result: ${fromFileToOrderedStacksMultiple()}")
}
//Part 1
fun fromFileToOrderedStacks(): String {
    val arranger = ContainerArranger()

    File("src/main/resources/stacks.txt").useLines {
        it.toList().forEach { value ->
            arranger.insertBoxIntoStack(value)

            if (value.contains("move"))
                arranger.arrangeSingleStacks(value)
        }
    }

    return arranger.getFinalResult()
}

//Part 2
fun fromFileToOrderedStacksMultiple(): String {
    val arranger = ContainerArranger()

    File("src/main/resources/stacks.txt").useLines {
        it.toList().forEach { value ->
            arranger.insertBoxIntoStack(value)

            if (value.contains("move"))
                arranger.arrangeMultipleStack(value)
        }
    }

    return arranger.getFinalResult()
}


//metti null ogni 4 spazi finchè non trovi una lettera che matcha la regex
//poi scorri finchè trovi altre lettere che marchano
//poi metti null fino ad arrivare alla lunghezza dell'array


//ricordati di fare il reverse degli array perchè devi usarli come stack