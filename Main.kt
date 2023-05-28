package minesweeper

var field = Array(9) { CharArray(9) { '.' } }

fun main() {
    initField()
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