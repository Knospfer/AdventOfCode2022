import java.io.File

class Terminal {
    companion object Commands {
        const val back = "$ cd .."
        const val list = "$ ls"
        const val maxWeight = 100000
        const val totalSpace = 70000000
        const val minSpaceRequired = 30000000
    }

    private var folderDictionary = mutableMapOf<String, List<String>>()

    init {
        readCommands()
    }

    fun getTotalFolderFilteredWeight(): Int {
        return getWeightedDictionary("/")
            .toList()
            .map { it.second }
            .filter { it < maxWeight }
            .reduce { acc, i -> acc + i }
    }

    fun findDirectoryToDeleteWeight(): Int {
        val weightDictionary = getWeightedDictionary("/")
        val totalWeight = weightDictionary["/"] ?: return 0

        return weightDictionary
            .filter { totalWeight - it.value <= totalSpace - minSpaceRequired }
            .map { it.value }
            .toList()
            .minOf { it }
    }

    private fun readCommands() {
        var newDirectory = ""
        File("src/main/resources/commands.txt").useLines {
            it.toList().forEach { row ->
                if (row == back) {
                    newDirectory = newDirectory
                        .split("/")
                        .dropLast(1)
                        .joinToString(separator = "/")
                        .ifEmpty { "/" }
                } else if (row.first() == '$' && row != list) {
                    val currentDir = row.split(" ").last()
                    val isRootDirectory = newDirectory.isEmpty() || newDirectory == "/"
                    newDirectory += if (isRootDirectory) currentDir
                    else "/$currentDir"
                } else if (row.first() != '$') {
                    val currentItems = folderDictionary[newDirectory]?.toMutableList() ?: mutableListOf()
                    currentItems.add(row)
                    folderDictionary[newDirectory] = currentItems
                }
            }
        }
    }

    private tailrec fun getWeightedDictionary(
        startingDirectory: String,
        currentWeightDictionary: MutableMap<String, Int> = mutableMapOf()
    ): MutableMap<String, Int> {
        val currentFolder = folderDictionary[startingDirectory] ?: return mutableMapOf()
        val directories = mutableListOf<String>()
        var result = 0

        currentFolder.forEach {
            val splittedValue = it.split(" ")
            if (it.contains("dir"))
                directories.add(splittedValue.last())
            else
                result += splittedValue.first().toInt()
        }

        var nextPathToCheck = ""
        for (dir in directories) {
            nextPathToCheck = if (startingDirectory == "/") "/$dir"
            else "$startingDirectory/$dir"

            val weight = currentWeightDictionary[nextPathToCheck] ?: break
            result += weight

            nextPathToCheck = ""
        }

        if (nextPathToCheck.isEmpty()) {
            currentWeightDictionary[startingDirectory] = result
            //this folder is ready but some other might missing
            nextPathToCheck = folderDictionary.keys.find { key ->
                currentWeightDictionary[key] == null
            } ?: ""
        }

        return if (currentWeightDictionary.size == folderDictionary.size) currentWeightDictionary
        else getWeightedDictionary(nextPathToCheck, currentWeightDictionary)
    }
}
