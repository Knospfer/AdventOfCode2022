import java.io.File

class ContainerArranger() {
    val stasks = mutableListOf<List<Char>>()
    private var numberOfColumns: Int

    init {
        numberOfColumns = findNumberOfColumns()
        for (i in 1..numberOfColumns) {
            stasks.add(mutableListOf())
        }
    }

    fun insertBoxIntoStack(currentLine: String) {
        var spaceCounter = 0
        var currentStackIndex = 0

        val regex = """[1-9]+""".toRegex()

        currentLine.forEach {
            //Stop when reach numbers row
            if (regex.matches("$it")) return

            conditionalFillStackAtIndex(currentStackIndex, it)

            spaceCounter++
            if (spaceCounter == 4) {
                currentStackIndex++
                spaceCounter = 0
            }
        }
    }

    fun arrangeSingleStacks(currentLine: String) {
        val regex = """[0-9]+""".toRegex()
        val (itemsToMove, fromStackIndex, toStackIndex) = regex.findAll(currentLine)
            .map { it.value.toInt() }.toList()

        val fromStack = stasks[fromStackIndex - 1].toMutableList()
        val toStack = stasks[toStackIndex - 1].toMutableList()

        for (i in 1..itemsToMove) {
            val elementToMove = fromStack.removeFirst()
            toStack.add(0, elementToMove)
        }

        stasks[fromStackIndex - 1] = fromStack
        stasks[toStackIndex - 1] = toStack
    }

    fun arrangeMultipleStack(currentLine: String) {
        println(currentLine)
        val regex = """[0-9]+""".toRegex()
        val (itemsToMove, fromStackIndex, toStackIndex) = regex.findAll(currentLine)
            .map { it.value.toInt() }.toList()

        val fromStack = stasks[fromStackIndex - 1].toMutableList()
        val toStack = stasks[toStackIndex - 1].toMutableList()

        val elementToMove = fromStack.subList(0, itemsToMove)
        toStack.addAll(0, elementToMove)

        stasks[fromStackIndex - 1] = fromStack.subList(itemsToMove, fromStack.size)
        stasks[toStackIndex - 1] = toStack
    }

    fun getFinalResult(): String {
        return stasks.map {
            it.first()
        }.toCharArray()
            .concatToString()
    }

    private fun conditionalFillStackAtIndex(index: Int, it: Char) {
        val regex = """[A-Z]""".toRegex()

        if (regex.matches("$it")) {
            val currentStack = stasks[index].toMutableList()
            currentStack.add(it)

            stasks[index] = currentStack
        }
    }

    private fun findNumberOfColumns(): Int {
        File("src/main/resources/stacks.txt").useLines {
            it.toList().forEach { value ->
                if (value.length > 2 && value[1] == '1') {
                    return value.split(' ').filter { v ->
                        v.trim().isNotEmpty()
                    }.size
                }
            }
        }
        return 0
    }
}