package minesweeper

var field = Array(9) { CharArray(9) { '.' } }

fun main() {
    initField()
    fillFieldWithHints()
    printField()
}

fun initField() {
    print("How many mines do you want on the field? ")
    val mines = readln().toInt()
    val selectedNumbers = mutableListOf<Int>()
    while (selectedNumbers.size < mines) {
        val randomNumber = (1..81).random()
        if (!selectedNumbers.contains(randomNumber)) {
            selectedNumbers.add(randomNumber)
        }
    }
    for (i in selectedNumbers) {
        field[(i - 1) / 9][(i - 1) % 9] = 'X'
    }
}

fun printField() {
    for (i in field) {
        for (y in i) {
            print(y)
        }
        println()
    }
}

fun fillFieldWithHints() {
    var count = 0
    for (i in 0 until field.size) {
        for (y in 0 until field[i].size) {
            if (field[i][y] == 'X') {
                continue
            } else {
                count = 0
                try {
                    if (field[i - 1][y - 1] == 'X') {
                        count++
                    }
                } catch (_: java.lang.Exception) {
                }
                try {
                    if (field[i][y - 1] == 'X') {
                        count++
                    }
                } catch (_: java.lang.Exception) {
                }
                try {
                    if (field[i + 1][y - 1] == 'X') {
                        count++
                    }
                } catch (_: java.lang.Exception) {
                }
                try {
                    if (field[i - 1][y] == 'X') {
                        count++
                    }
                } catch (_: java.lang.Exception) {
                }
                try {
                    if (field[i + 1][y] == 'X') {
                        count++
                    }
                } catch (_: java.lang.Exception) {
                }
                try {
                    if (field[i - 1][y + 1] == 'X') {
                        count++
                    }
                } catch (_: java.lang.Exception) {
                }
                try {
                    if (field[i][y + 1] == 'X') {
                        count++
                    }
                } catch (_: java.lang.Exception) {
                }
                try {
                    if (field[i + 1][y + 1] == 'X') {
                        count++
                    }
                } catch (_: java.lang.Exception) {
                }
            }
            if (count == 0) {
                continue
            } else {
                field[i][y] = count.digitToChar()
            }
        }
    }
}