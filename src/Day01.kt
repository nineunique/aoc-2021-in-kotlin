fun main() {
    fun part1(input: List<String>): Int =
        List(input.size) { i ->
            if (i + 1 < input.size &&
                input[i].toInt() < input[i + 1].toInt()
            ) {
                1
            } else {
                0
            }
        }.sum()

    fun part2(input: List<String>): Int =
        List(input.size) { i ->
            if (i + 3 < input.size &&
                (input[i].toInt() + input[i + 1].toInt() + input[i + 2].toInt())
                < (input[i + 1].toInt() + input[i + 2].toInt() + input[i + 3].toInt())
            ) {
                1
            } else {
                0
            }
        }.sum()

    // sample case
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7) { "part1: ${part1(testInput)}" }
    check(part2(testInput) == 5) { "part2: ${part1(testInput)}" }

    val input = readInput("Day01")
    // answer
    println("part1: ${part1(input)}")
    println("part2: ${part2(input)}")
}
