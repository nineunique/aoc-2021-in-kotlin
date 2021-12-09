fun main() {
    val day = "Day02"
    val space = Regex("\\s")

    fun part1(input: List<String>): Int =
        input.fold(Pair(0, 0)) { (hr, dep), s ->
            val (cmd, x) = s.split(space).let { it.first() to it.last().toInt() }
            when (cmd) {
                "forward" -> Pair(hr + x, dep)
                "up" -> Pair(hr, dep - x)
                else -> Pair(hr, dep + x)
            }
        }.let {
            it.first * it.second
        }

    fun part2(input: List<String>): Int =
        input.fold(Triple(0, 0, 0)) { (hr, dep, aim), s ->
            val (cmd, x) = s.split(space).let { it.first() to it.last().toInt() }
            when (cmd) {
                "forward" -> Triple(hr + x, dep + (aim * x), aim)
                "up" -> Triple(hr, dep, aim - x)
                else -> Triple(hr, dep, aim + x)
            }
        }.let { (hr, dep, _) ->
            hr * dep
        }


    // sample case
    val testInput = readInput("${day}_test")
    check(part1(testInput) == 150) { "part1: ${part1(testInput)}" }
    check(part2(testInput) == 900) { "part2: ${part2(testInput)}" }

    // answer
    val input = readInput(day)
    println("part1: ${part1(input)}")
    println("part2: ${part2(input)}")

    // check regression
    check(part1(input) == readInput("${day}_part1_answer").first().toInt())
    check(part2(input) == readInput("${day}_part2_answer").first().toInt())
}
