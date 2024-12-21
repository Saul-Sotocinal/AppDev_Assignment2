package com.example.assignment2

data class Question (
    val question: String,
    val choices: List<String>,
    val answer: String
)

fun GetMathQuiz(): List<Question> {
    return listOf(
        Question("2 + 2", listOf("1", "2", "3", "4",),"4"),
        Question("5 + 2", listOf("5", "6", "7", "8"), "7"),
        Question("12 - 2", listOf("9", "10", "11", "12",), "10"),
        Question("3 - 2", listOf("1", "2", "3", "4",), "1"),
        Question("6 + 2", listOf("5", "6", "7", "8"), "8"),
        Question("END", listOf(), "none")
    )
}

fun GetHexColorsQuiz(): List<Question> {
    return listOf(
        Question("What color does #0000ff make?", listOf("Red", "Blue", "Green", "Black",),"Blue"),
        Question("What color does #000000 make?", listOf("Red", "Blue", "Green", "Black",), "Black"),
        Question("What color does #ffee00 make?", listOf("Purple", "Green", "Yellow", "Grey",), "Yellow"),
        Question("What color does #ccfc1e make?", listOf("Chartreuse", "Auburn", "Periwinkle", "White",), "Chartreuse"),
        Question("What color does #fa7dc4 make?", listOf("Pink", "Blue", "Orange", "Green"), "Pink"),
        Question("END", listOf(), "none")
    )
}