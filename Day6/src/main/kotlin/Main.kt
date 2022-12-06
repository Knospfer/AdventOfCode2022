import java.io.File

fun main(args: Array<String>) {
    println("Hello Day Six!")

    println("---Part One---")
    println("Character found: ${fromFileToFirstCharacterSequence(4)}")
    println("---Part Two---")
    println("Character found: ${fromFileToFirstCharacterSequence(14)}")
}

fun fromFileToFirstCharacterSequence(group: Int): Int {
    File("src/main/resources/datastream.txt").useLines {
        it.toList().forEach { value ->
            for (i in 0..value.length - group) {
                val sublList = value.substring(i, i + group)
                val set = sublList.toList().toSet()
                if (set.size == group) return i + group
            }
        }
    }
    return 0
}