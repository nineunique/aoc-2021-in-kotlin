import kotlin.math.abs

fun main() {
    val day = "Day05"

    val ventRegex = Regex("""(\d+),(\d+) -> (\d+),(\d+)""")


    fun part1(input: List<String>): Int {
        val linePoints = input.map {
            val matches = ventRegex.matchEntire(it)?.groupValues ?: throw Exception()
            val startX = matches[1].toInt()
            val startY = matches[2].toInt()
            val endX = matches[3].toInt()
            val endY = matches[4].toInt()

            if (startX == endX) {
                List(abs(endY - startY) + 1) { i ->
                    Point(startX, minOf(startY, endY) + i)
                }
            } else if (startY == endY) {
                List(abs(endX - startX) + 1) { i ->
                    Point(minOf(startX, endX) + i, startY)
                }
            } else {
                emptyList()
            }
        }.flatten()

        return linePoints.groupBy { "${it.x},${it.y}" }.filter { it.value.size > 1 }.size
    }

    fun part2(input: List<String>): Int {
        val linePoints = input.map {
            val matches = ventRegex.matchEntire(it)?.groupValues ?: throw Exception()
            val startX = matches[1].toInt()
            val startY = matches[2].toInt()
            val endX = matches[3].toInt()
            val endY = matches[4].toInt()

            if (startX == endX) {
                List(abs(endY - startY) + 1) { i ->
                    Point(startX, minOf(startY, endY) + i)
                }
            } else if (startY == endY) {
                List(abs(endX - startX) + 1) { i ->
                    Point(minOf(startX, endX) + i, startY)
                }
            } else if (abs(endX - startX) == abs(endY - startY)) {
                val directX = if (endX - startX < 0) {
                    -1
                } else {
                    1
                }
                val directY = if (endY - startY < 0) {
                    -1
                } else {
                    1
                }
                List(abs(endX - startX) + 1) { i ->
                    Point(startX + (i * directX), startY + (i * directY))
                }
            } else {
                emptyList()
            }
        }.flatten()

        return linePoints.groupBy { "${it.x},${it.y}" }.filter { it.value.size > 1 }.size
    }

    // sample case
    val testInput = readInput("${day}_test")
    check(part1(testInput) == 5) { "part1: ${part1(testInput)}" }
    check(part2(testInput) == 12) { "part2: ${part2(testInput)}" }

    // answer
    val input = readInput(day)
    println(part1(input))
    println(part2(input))
}

data class Point(
    val x: Int,
    val y: Int
)
