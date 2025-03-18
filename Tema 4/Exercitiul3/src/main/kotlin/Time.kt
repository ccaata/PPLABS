import java.util.Date
import java.util.Calendar
class Time(private val hour: Int, private val minute: Int, private val second: Int) {
    companion object {
        fun fromString(string: String): Time? {
            val components = string.split(":").map { it.trim() }
            if (components.size < 3) {
                return null
            }

            return Time(components[0].toInt(), components[1].toInt(), components[2].toInt())
        }

        fun now(): Time {
            val calendar = Calendar.getInstance()
            return Time(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND))
        }
    }

    override fun toString(): String = "$hour:$minute:$second"
}