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
        return input.sumOf { line ->
            val s = line.split(" | ")
            val left = s[0]
            val right = s[1]

            val signals = left.split(space)
            val digitMap = mutableMapOf<Int, String>()
            // 1, 4, 7, 8
            signals.forEach {
                when (it.length) {
                    2 -> digitMap.put(1, it)
                    3 -> digitMap.put(7, it)
                    4 -> digitMap.put(4, it)
                    7 -> digitMap.put(8, it)
                }
            }

            // 0, 6, 9
            signals.filter { it.length == 6 }.map {
                val containsOne = it.toList().containsAll(digitMap.getOrDefault(1, "").toList())
                val containsFour = it.toList().containsAll(digitMap.getOrDefault(4, "").toList())
                if (containsOne && containsFour) {
                    digitMap.put(9, it)
                } else if (containsOne && !containsFour) {
                    digitMap.put(0, it)
                } else if (!containsOne && !containsFour) {
                    digitMap.put(6, it)
                } else {
                    throw Exception(it)
                }
            }

            // 2, 3, 5
            signals.filter { it.length == 5 }.map {
                val containsOne = it.toList().containsAll(digitMap.getOrDefault(1, "").toList())
                val containsFive = digitMap.getOrDefault(6, "").toList().containsAll(
                    it.toList()
                )

                if (containsOne) {
                    digitMap.put(3, it)
                } else if (containsFive) {
                    digitMap.put(5, it)
                } else {
                    digitMap.put(2, it)
                }
            }

            val converted = digitMap.map { Regex("^[${it.value}]{${it.value.length}}$") to it.key.toString() }.toMap()
            right.split(space).map { signal ->
                converted.entries.find { it.key.matches(signal) }?.value
            }.joinToString("").toInt()
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
