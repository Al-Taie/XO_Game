package com.example.xogame.util

object Constant {
    const val X = "X"
    const val O = "O"
    const val CELL_COUNT = 9
}

object Player {
    const val ONE = 1
    const val TWO = 2
    var CURRENT = ONE
}

object Data {
    var ROUND = 0

    fun reset() {
        ROUND = 0
        ROUND = 0
    }
}

object Points {
    var X = 0
    var O = 0

    fun reset() {
        X = 0
        O = 0
    }
}

object Index {
    const val ZERO = 0
    const val ONE = 1
    const val TWO = 2
    const val THREE = 3
    const val FOUR = 4
    const val FIVE = 5
    const val SIX = 6
    const val SEVEN = 7
    const val EIGHT = 8
}