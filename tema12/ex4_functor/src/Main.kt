
fun addTestString(string: String) : String{
    return "Test $string"
}

fun String.toPascalCase(): String {
    val components = this.split(" ")
    var result = ""
    components.forEach() {
        result += it.capitalize()
    }
    return result
}

fun main() {

    val mapForTest : MutableMap<Int, String> = mutableMapOf()

    mapForTest[1] = "thank you"
    mapForTest[2] = "multumesc mult"
    mapForTest[3] = "danke schoen"
    mapForTest[4] = "gracias"
    mapForTest[5] = "merci beaucoup"
    mapForTest[6] = "grazie"


    println(MutableMapFunctor(mapForTest).map { addTestString(it)}.map { it.toPascalCase() }.map)

}