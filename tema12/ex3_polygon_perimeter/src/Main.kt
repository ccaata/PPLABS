import java.lang.Math.pow
import java.util.Scanner
import kotlin.math.pow
import kotlin.math.sqrt

fun getPolygonPoints(numberOfPoints: Int) : MutableList<Pair<Int,Int>> {

    val sc = Scanner(System.`in`)

    var listOfPoints : MutableList<Pair<Int, Int>> = mutableListOf()


    for(i in 1..numberOfPoints) {
        val x = sc.nextInt()
        val y = sc.nextInt()
        listOfPoints.add(Pair(x,y))
    }

    listOfPoints.add(listOfPoints.first())

    return listOfPoints
}

fun distanceCalculus(pair1: Pair<Int,Int>, pair2: Pair<Int,Int>) : Double {
    return sqrt(((pair2.first - pair1.first).toDouble()).pow(2.0) + ((pair2.second - pair1.second).toDouble()).pow(2.0))
}

fun main() {

    val sc = Scanner(System.`in`)

    println("Number of polygon points: ")
    val points = sc.nextInt()

    val listOfPoints = getPolygonPoints(points)

    val perimeter = listOfPoints.zipWithNext().sumOf { (x, y) ->  distanceCalculus(x, y)}

    println(perimeter)

}