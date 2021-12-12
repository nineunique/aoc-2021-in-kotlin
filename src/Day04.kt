fun main() {
    val day = "Day04"

    fun part1(input: List<String>): Int {
        val calledNumbers = input[0].split(",").map { it.toInt() }

        val boards = mutableListOf<Board>()
        for (i in 2..input.size step 6) {
            boards.add(Board.of(input.subList(i, i + 5)))
        }

        var wonScore = 0
        root@ for (calledNumber in calledNumbers) {
            for (board in boards) {
                board.mark(calledNumber)
                if (board.won()) {
                    wonScore = board.unmarkedTotal * calledNumber
                    break@root
                }
            }
        }

        return wonScore
    }

    fun part2(input: List<String>): Int {
        val calledNumbers = input[0].split(",").map { it.toInt() }

        val boards = mutableListOf<Board>()
        for (i in 2..input.size step 6) {
            boards.add(Board.of(input.subList(i, i + 5)))
        }

        var wonScore = 0
        root@ for (calledNumber in calledNumbers) {
            for (board in boards) {
                if(!board.left) {
                    board.mark(calledNumber)
                }
                if (!board.left && board.won()) {
                    board.left = true
                    if (boards.all { it.left }) {
                        wonScore = board.unmarkedTotal * calledNumber
                        break@root
                    }
                }
            }
        }

        return wonScore
    }

    // sample case
    val testInput = readInput("${day}_test")
    check(part1(testInput) == 4512) { "part1: ${part1(testInput)}" }
    check(part2(testInput) == 1924) { "part2: ${part2(testInput)}" }

    // answer
    val input = readInput(day)
    println(part1(input))
    println(part2(input))
}

data class Board(
    private val numbers: List<List<Number>>,
    var left: Boolean = false
) {
    class Number(
        val v: Int,
        var marked: Boolean = false
    )

    fun mark(called: Int) {
        numbers.forEach { row ->
            row.forEach { col ->
                if (col.v == called) {
                    col.marked = true
                }
            }
        }
    }

    fun won(): Boolean {
        val rowWon = numbers.any { it.all { it.marked } }
        val colWon = List(5) { i ->
            numbers[0][i].marked && numbers[1][i].marked && numbers[2][i].marked && numbers[3][i].marked && numbers[4][i].marked
        }.any { it }
        return rowWon || colWon
    }

    val unmarkedTotal
        get() = numbers.flatten().filter {
            !it.marked
        }.sumOf {
            it.v
        }

    companion object {
        private val space = Regex("""\s+""")
        fun of(list: List<String>): Board =
            list.map {
                listOf(
                    Number(it.substring(0, 2).trim().toInt()),
                    Number(it.substring(3, 5).trim().toInt()),
                    Number(it.substring(6, 9).trim().toInt()),
                    Number(it.substring(9, 11).trim().toInt()),
                    Number(it.substring(12, 14).trim().toInt())
                )
            }.let {
                Board(it)
            }

    }
}

