import kotlin.reflect.typeOf

fun main() {
    val result = listOf(1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8)
        .filter { it >= 5 }
        .chunked(2).map { (x,y) -> x * y }
        .fold(0) {acc, i -> acc + i }
    
    println(result)
}