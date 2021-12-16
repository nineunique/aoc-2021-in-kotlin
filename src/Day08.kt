fun main() {
    val day = "Day08"

    val space = Regex("""\s""")

    fun part1(input: List<String>): Int {
        return input.map {
            it.substringAfterLast(" | ")
        }.sumOf { s ->
            s.split(space).map {
                if (it.length == 2 ||
                    it.length == 4 ||
                    it.length == 3 ||
                    it.length == 7
                ) {
                    1
                } else {
                    0
                }
            }.sum()
        }
    }

    fun part2(input: List<String>): Int {
        val zero = Regex("""^[cagedb]{6}$""")
        val one = Regex("""^[ab]{2}$""")
        val two = Regex("""^[gcdfa]{5}$""")
        val three = Regex("""^[fbcad]{5}$""")
        val four = Regex("""^[bcdf]{4}$""")
        val five = Regex("""^[cdfbe]{5}$""")
        val six = Regex("""^[cdfgeb]{6}$""")
        val seven = Regex("""^[dab]{3}$""")
        val eight = Regex("""^[acedgfb]{7}$""")
        val nine = Regex("""^[cefabd]{6}$""")
        return input.map {
            it.substringAfterLast(" | ")
        }.sumOf { s ->
            s.split(space).map {
                if (it.matches(zero)) {
                    "0"
                } else if (it.matches(one)) {
                    "1"
                } else if (it.matches(two)) {
                    "2"
                } else if (it.matches(three)) {
                    "3"
                } else if (it.matches(four)) {
                    "4"
                } else if (it.matches(five)) {
                    "5"
                } else if (it.matches(six)) {
                    "6"
                } else if (it.matches(seven)) {
                    "7"
                } else if (it.matches(eight)) {
                    "8"
                } else if (it.matches(nine)) {
                    "9"
                } else {
                    throw Exception(it)
                }
            }.joinToString().toInt()
        }
    }

// sample case
    val testInput = readInput("${day}_test")
    check(part1(testInput) == 26) { "part1: ${part1(testInput)}" }
    check(part2(testInput) == 61229) { "part2: ${part2(testInput)}" }

// answer
    val input = readInput(day)
    println(part1(input))
    println(part2(input))
}
