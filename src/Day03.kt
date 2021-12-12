fun main() {
    val day = "Day03"

    fun part1(input: List<String>): Int {
        val container = MutableList<MutableList<Char>>(input[0].toCharArray().size) { mutableListOf() }
        val capsized = input.fold(
            container
        ) { cntnr, s ->
            s.toCharArray().forEachIndexed { index, c ->
                cntnr[index].add(s[index])
            }
            cntnr
        }
        val gamma = capsized.map { chars ->
            chars.groupBy { it }
                .let {
                    val zeroSize = it['0']?.size ?: 0
                    val oneSize = it['1']?.size ?: 0
                    if (zeroSize > oneSize) {
                        "0"
                    } else {
                        "1"
                    }
                }
        }
        val epsilon = gamma.map {
            if (it == "0") {
                "1"
            } else {
                "0"
            }
        }
        return Integer.parseInt(gamma.joinToString(""), 2) * Integer.parseInt(epsilon.joinToString(""), 2)
    }

    fun part2(input: List<String>): Int {

        fun findRate(array: List<CharArray>, i: Int, type:String): CharArray {
            array.map { it[i] }.groupBy { it }.let {
                val zeroSize = it['0']?.size ?: 0
                val oneSize = it['1']?.size ?: 0
                if (type == "oxygen") {
                    if (zeroSize > oneSize) {
                        '0'
                    } else {
                        '1'
                    }
                } else { // CO2
                    if (zeroSize <= oneSize) {
                        '0'
                    } else {
                        '1'
                    }
                }
            }.let { keepBit ->
                val filtered = array.filter {
                    it[i] == keepBit
                }
                return if (filtered.size == 1) {
                    filtered[0]
                } else {
                    findRate(filtered, i + 1, type)
                }
            }
        }

        val array = input.map { it.toCharArray() }
        val ogr = findRate(array, 0, "oxygen")
        val csr = findRate(array, 0, "co2")

        return Integer.parseInt(ogr.joinToString(""), 2) * Integer.parseInt(csr.joinToString(""), 2)
    }


    // sample case
    val testInput = readInput("${day}_test")
    check(part1(testInput) == 198) { "part1: ${part1(testInput)}" }
    check(part2(testInput) == 230) { "part2: ${part2(testInput)}" }

    // answer
    val input = readInput(day)
    println(part1(input))
    println(part2(input))
}
