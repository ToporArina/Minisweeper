package minesweeper

import kotlin.system.exitProcess

var field = Array(9) { CharArray(9) { '.' } }
var hideField = Array(field.size) { field[it].clone() }
var mines = 0
var hits = 0

fun main() {
    initField()
    fillFieldWithHints()
    hideField = Array(field.size) { field[it].clone() }
    field = Array(9) { CharArray(9) { '.' } }
    while (true) {
        println()
        printField()
        shot()
    }
}

fun initField() {
    print("How many mines do you want on the field? ")

    mines = readln().toInt()
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
    println(" |123456789|")
    println("-|---------|")
    for (i in field.indices) {
        print("${i + 1}|")
        for (y in 0 until field[i].size) {
            print(field[i][y])
        }
        print("|")
        println()
    }
    println("-|---------|")
}

fun fillFieldWithHints() {
    var count: Int
    for (i in field.indices) {
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

fun shot() {
    if (hits == mines) {
        println("Congratulations! You found all the mines!")
        exitProcess(0)
    }

    print("Set/unset mines marks or claim a cell as free: ")

    val answ = readln().split(" ")
    val y = answ[0].toInt() - 1
    val x = answ[1].toInt() - 1
    val mark = answ[2]
    if (mark == "mine") {
        if (hideField[x][y] == 'X' && field[x][y] == '.') {
            hits++
            field[x][y] = '*'
        } else if (hideField[x][y] == 'X' && field[x][y] == '*') {
            hits--
            field[x][y] = '.'
        } else if (field[x][y] == '*') {
            field[x][y] = '.'
        } else {
            field[x][y] = '*'
        }
    } else {
        if (hideField[x][y].isDigit()) {
            field[x][y] = hideField[x][y]
        } else if (hideField[x][y] == 'X') {
            for (i in hideField.indices) {
                for (z in 0 until hideField[i].size) {
                    if (hideField[i][z] == 'X') {
                        field[i][z] = hideField[i][z]
                    }
                }
            }
            println()
            printField()
            println("You stepped on a mine and failed!")
            exitProcess(0)
        } else {
            field[x][y] = '/'
            hideField[x][y] = '/'
            checkAround()
        }
    }
}

fun checkAround() {
    for (i in field.indices) {
        for (y in field.indices) {
            try {
                if (field[i][y] == '/' && hideField[i - 1][y] == '.') {
                    field[i - 1][y] = '/'
                    hideField[i - 1][y] = '/'
                    checkAround()
                } else if (field[i][y] == '/' && hideField[i - 1][y].isDigit()) {
                    field[i - 1][y] = hideField[i - 1][y]
                }
            } catch (_: Exception) {
            }

            try {
                if (field[i][y] == '/' && hideField[i + 1][y] == '.') {
                    field[i + 1][y] = '/'
                    hideField[i + 1][y] = '/'
                    checkAround()
                } else if (field[i][y] == '/' && hideField[i + 1][y].isDigit()) {
                    field[i + 1][y] = hideField[i + 1][y]
                }
            } catch (_: Exception) {
            }

            try {
                if (field[i][y] == '/' && hideField[i][y - 1] == '.') {
                    field[i][y - 1] = '/'
                    hideField[i][y - 1] = '/'
                    checkAround()
                } else if (field[i][y] == '/' && hideField[i][y - 1].isDigit()) {
                    field[i][y - 1] = hideField[i][y - 1]
                }
            } catch (_: Exception) {
            }

            try {
                if (field[i][y] == '/' && hideField[i][y + 1] == '.') {
                    field[i][y + 1] = '/'
                    hideField[i][y + 1] = '/'
                    checkAround()
                } else if (field[i][y] == '/' && hideField[i][y + 1].isDigit()) {
                    field[i][y + 1] = hideField[i][y + 1]
                }
            } catch (_: Exception) {
            }

            try {
                if (field[i][y] == '/' && hideField[i + 1][y + 1] == '.') {
                    field[i + 1][y + 1] = '/'
                    hideField[i + 1][y + 1] = '/'
                    checkAround()
                } else if (field[i][y] == '/' && hideField[i + 1][y + 1].isDigit()) {
                    field[i + 1][y + 1] = hideField[i + 1][y + 1]
                }
            } catch (_: Exception) {
            }

            try {
                if (field[i][y] == '/' && hideField[i - 1][y - 1] == '.') {
                    field[i - 1][y - 1] = '/'
                    hideField[i - 1][y - 1] = '/'
                    checkAround()
                } else if (field[i][y] == '/' && hideField[i - 1][y - 1].isDigit()) {
                    field[i - 1][y - 1] = hideField[i - 1][y - 1]
                }
            } catch (_: Exception) {
            }

            try {
                if (field[i][y] == '/' && hideField[i + 1][y - 1] == '.') {
                    field[i + 1][y - 1] = '/'
                    hideField[i + 1][y - 1] = '/'
                    checkAround()
                } else if (field[i][y] == '/' && hideField[i + 1][y - 1].isDigit()) {
                    field[i + 1][y - 1] = hideField[i + 1][y - 1]
                }
            } catch (_: Exception) {
            }

            try {
                if (field[i][y] == '/' && hideField[i - 1][y + 1] == '.') {
                    field[i - 1][y + 1] = '/'
                    hideField[i - 1][y + 1] = '/'
                    checkAround()
                } else if (field[i][y] == '/' && hideField[i - 1][y + 1].isDigit()) {
                    field[i - 1][y + 1] = hideField[i - 1][y + 1]
                }
            } catch (_: Exception) {
            }
        }
    }
}