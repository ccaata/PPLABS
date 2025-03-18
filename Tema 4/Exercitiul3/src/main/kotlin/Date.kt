import java.util.Date as JavaDate
import java.util.Calendar
class Date(private val year: Int, private val month: Int, private val day: Int) {
    companion object {
        fun fromString(string: String): Date? {
            val components = string.split("-").map { it.trim() }
            if (components.size < 3) {
                return null
            }

            return Date(components[0].toInt(), components[1].toInt(), components[2].toInt())
        }

        fun now(): Date {
            val calendar = Calendar.getInstance()
            return Date(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
        }
    }

    override fun toString(): String = "$year-$month-$day"
}