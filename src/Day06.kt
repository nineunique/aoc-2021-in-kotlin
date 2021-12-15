fun main() {
    val day = "Day06"

    fun part1(input: List<String>): Long {
//        val fishes = mutableListOf(*(input[0].split(",").map { LanternFish(it.toLong()) }.toTypedArray()))
//        val tmpFishes = mutableListOf<LanternFish>()
//        for (i in 0..79) {
//            for (fish in fishes) {
//                fish.tick()?.let { tmpFishes.add(it) }
//            }
//            fishes.addAll(tmpFishes)
//            tmpFishes.clear()
//        }
//        return fishes.size.toLong()
        var fishes = input[0].split(",").map { it.toInt() }.toIntArray()
        val tmpFishes = mutableListOf<Int>()
        for (i in 0..79) {
            println(("day $i"))
            for (f in fishes.indices) {
                fishes[f] = --fishes[f]
                if (fishes[f] == -1) {
                    fishes[f] = 6
                    tmpFishes.add(8)
                }
            }
            fishes = intArrayOf(*fishes, *(tmpFishes.toIntArray()))
            tmpFishes.clear()
        }
        return fishes.size.toLong()
    }

    fun part2(input: List<String>): Long {
        var fishes = input[0].split(",").map { it.toInt() }.toIntArray()
        val tmpFishes = mutableListOf<Int>()
        for (i in 0..255) {
            println(("day $i"))
            for (f in fishes.indices) {
                fishes[f] = --fishes[f]
                if (fishes[f] == -1) {
                    fishes[f] = 6
                    tmpFishes.add(8)
                }
            }
            fishes = intArrayOf(*fishes, *(tmpFishes.toIntArray()))
            tmpFishes.clear()
        }
        return fishes.size.toLong()
    }

    // sample case
    val testInput = readInput("${day}_test")
    check(part1(testInput) == 5934L) { "part1: ${part1(testInput)}" }
    check(part2(testInput) == 26984457539) { "part2: ${part2(testInput)}" }

    // answer
    val input = readInput(day)
    println(part1(input))
    println(part2(input))
}

data class LanternFish(
    private var timer: Long
) {
    fun tick(): LanternFish? {
        timer--
        return if (timer == -1L) {
            timer = 6
            LanternFish(8)
        } else {
            null
        }
    }
}
