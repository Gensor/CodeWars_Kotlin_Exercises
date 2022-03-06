package kyu4

/*
    Create a function that takes a positive integer and returns the next bigger number that can be
    formed by rearranging its digits. For example:

        12 ==> 21
        513 ==> 531
        2017 ==> 2071

        nextBigger(num: 12)   // returns 21
        nextBigger(num: 513)  // returns 531
        nextBigger(num: 2017) // returns 2071

    If the digits can't be rearranged to form a bigger number, return -1 (or nil in Swift):

        9 ==> -1
        111 ==> -1
        531 ==> -1

        nextBigger(num: 9)   // returns nil
        nextBigger(num: 111) // returns nil
        nextBigger(num: 531) // returns nil
 */

fun main(){
    println(nextBiggerNumber(93210908))
}

fun nextBiggerNumber(n: Long): Long {
    if (n/10 < 1) return -1L
    if (n.toString().toList().sortedDescending().joinToString("").equals(n.toString())) return -1L

    var number = n
    do {
        number++
    }
    while (!isResult(number, n))

    return number
}

fun isResult(numberToCheck : Long, n: Long): Boolean {
    val numberToCheckList = numberToCheck.toString().toList()
    val nList = n.toString().toMutableList()

    if ( numberToCheckList.containsAll(nList) )
    {
        for (i in numberToCheckList){
            if (!nList.contains(i))return false else nList.remove(i)
        }
        return true
    }
    return false
}
