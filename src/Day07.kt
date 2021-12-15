import kotlin.math.abs
import kotlin.math.min

fun main() {
    val day = "Day07"

    fun part1(input: List<String>): Int {
        val crabs = input[0].split(",").map { it.toInt() }

        val maxP = crabs.maxOf { it }
        val minP = crabs.minOf { it }

        var leastFuel = Int.MAX_VALUE
        for (i in minP..maxP) {
            val totalFuel = crabs.sumOf { p ->
                abs(p - i)
            }
            leastFuel = min(totalFuel, leastFuel)
        }

        return leastFuel
    }

    fun part2(input: List<String>): Int {
        val crabs = input[0].split(",").map { it.toInt() }

        val maxP = crabs.maxOf { it }
        val minP = crabs.minOf { it }

        var leastFuel = Int.MAX_VALUE
        for (i in minP..maxP) {
            val totalFuel = crabs.sumOf { p ->
                (1..abs(p - i)).sum()
            }
            leastFuel = min(totalFuel, leastFuel)
        }

        return leastFuel
    }

    // sample case
    val testInput = readInput("${day}_test")
    check(part1(testInput) == 37) { "part1: ${part1(testInput)}" }
    check(part2(testInput) == 168) { "part2: ${part2(testInput)}" }

    // answer
    val input = readInput(day)
    println(part1(input))
    println(part2(input))
}
