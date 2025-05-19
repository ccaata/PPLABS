import java.io.File
import java.util.*

fun caesarCipher(string: String, offset: Int) : String{
    var result = ""
    for (i in string) {
        if (i.isUpperCase()) {
            result += ((i.code + offset - 65) % 26 + 65).toChar()

        } else if (i.isLowerCase()) {
            result += ((i.code + offset - 97) % 26 + 97).toChar()
        }
    }
    return result
}

fun main() {

    val sc = Scanner(System.`in`)

    println("Type the offset for the caesar_cipher: ")
    val offset = sc.nextInt()

    val file = File("fisier.txt");

    val stringList = file
        .readText()
        .replace(",", "")
        .replace(".", "")
        .split(" ")


    val finalList = stringList
        .asSequence()
        .filter { it.length in 4..7 }
        .map {caesarCipher(it,offset)}
        .joinToString(", ")

    println(finalList)
}