package kyu5

/*
    Coding decimal numbers with factorials is a way of writing out numbers in a base system that
    depends on factorials, rather than powers of numbers.

    In this system, the last digit is always 0 and is in base 0!. The digit before that is either
    0 or 1 and is in base 1!. The digit before that is either 0, 1, or 2 and is in base 2!, etc.
    More generally, the nth-to-last digit is always 0, 1, 2, ..., n and is in base n!.

    Read more about it at: http://en.wikipedia.org/wiki/Factorial_number_system

    Example
    The decimal number 463 is encoded as "341010", because:

        463 = 3×5! + 4×4! + 1×3! + 0×2! + 1×1! + 0×0!

    If we are limited to digits 0..9, the biggest number we can encode is 10!-1 (= 3628799). So we
    extend 0..9 with letters A..F. With these 36 digits we can now encode numbers up to
    36!-1 (= 3.72 × 1041)

    Task
    We will need two functions. The first one will receive a decimal number and return a string with
    the factorial representation.

    The second one will receive a string with a factorial representation and produce the decimal
    representation.

    Given numbers will always be positive.
 */

fun main(){
    println(factString2Dec("A0000000000"))
    println(dec2FactString(371993326789901217L))
}


fun dec2FactString(n: Long): String {
    val factorials = (1..15).toList()
        .map { (1.. it.toLong()).reduce{ x, y -> x * y} }
        .toMutableList()
    factorials.add(0,0L)

    var number = n
    val hexaString : ArrayList<String> = ArrayList()

    for (factorial in factorials.asReversed()){
        val positionOffactorial = factorials.indexOf(factorial)

        if ((factorial > number) && (hexaString.isNotEmpty())){
            hexaString.set(  positionOffactorial, "0")
        }
        if((factorial <= number) && (factorial != 0L)){
            val i  = number / factorial
            if (hexaString.size == 0) (0 .. positionOffactorial)
                .map{ hexaString.add(it,"0")}
            hexaString.set(positionOffactorial, "%X".format(i.toInt()) )
            number -= factorial*i.toInt()
        }
    }
    return hexaString
        .reversed()
        .joinToString("")
}

fun factString2Dec(str: String): Long {
    val input = str.toList()
    val inputSize = input.size
    var result = 0L

    for (i in  inputSize-1 downTo 1){
        val decNumber = Integer.decode("0x${input[((i * -1).mod(inputSize-1))]}")
        val factorial = (1 .. i).reduce{x, y -> x * y}
        result += decNumber*factorial
    }
    return result
}
