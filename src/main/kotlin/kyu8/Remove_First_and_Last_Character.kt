package kyu8

/**
 * It's pretty straightforward. Your goal is to create a function that removes the first and last characters of a string.
 * You're given one parameter, the original string. You don't have to worry with strings with less than two characters.
 */

fun removeChar(str: String): String {

    return if (str.length > 2)  str.slice(1..str.length-2) else ""
}

fun main() {
    println(removeChar("Slovo"))
}