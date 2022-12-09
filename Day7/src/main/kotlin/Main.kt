fun main(args: Array<String>) {
    println("Hello Day Seven!")

    println("---Part One---")
    val terminal = Terminal()
    println("Folder filtered weigth: ${terminal.getTotalFolderFilteredWeight()}")
    println("---Part Two---")
    println("Deletable folder total size: ${terminal.findDirectoryToDeleteWeight()}")
}