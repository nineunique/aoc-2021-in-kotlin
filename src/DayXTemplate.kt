fun main() {
    val day = "DayX"

    fun part1(input: List<String>): Int = 0
    fun part2(input: List<String>): Int = 0

    // sample case
    val testInput = readInput("${day}_test")
    check(part1(testInput) == 0) { "part1: ${part1(testInput)}" }
    check(part2(testInput) == 0) { "part2: ${part2(testInput)}" }

    // answer
    val input = readInput(day)
    println(part1(input))
    println(part2(input))

    // check regression
    check(part1(input) == readInput("${day}_part1_answer").first().toInt())
    check(part2(input) == readInput("${day}_part2_answer").first().toInt())
}
