package minesweeper

import kotlin.system.exitProcess

var field = Array(9) { CharArray(9) { '.' } }
var hideField = Array(field.size) { field[it].clone() }
var mines = 0
var hits = 0

fun main() {
    initField()
    hideField = Array(field.size) { field[it].clone() }
    fillFieldWithHints()
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
    for (i in 0 until field.size) {
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
    for (i in 0 until field.size) {
        for (y in 0 until field[i].size) {
            if (field[i][y] == 'X') {
                field[i][y] = '.'
            }
        }
    }
}

fun shot() {
    if (hits == mines) {
        println("Congratulations! You found all the mines!")
        exitProcess(0)
    }
    print("Set/delete mines marks (x and y coordinates): ")
    val (yShot, xShot) = readln().split(" ").map { it.toInt() }
    if (hideField[xShot - 1][yShot - 1] == 'X') {
        hits++
        field[xShot - 1][yShot - 1] = '*'
    } else if (field[xShot - 1][yShot - 1].isDigit()) {
        println("There is a number here!")
        shot()
    } else if (field[xShot - 1][yShot - 1] == '*') {
        field[xShot - 1][yShot - 1] = '.'
    } else {
        field[xShot - 1][yShot - 1] = '*'
    }
}