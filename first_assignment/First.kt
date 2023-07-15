package first_assignment

import java.util.Scanner

fun main() {
    val sc = Scanner(System.`in`)

    val a = sc.nextInt()

    for (i in 1..9) {
        val multiple = a * i
        println("$a * $i = $multiple")
    }
}
