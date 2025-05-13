import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class HistoryLogRecord(record: String) : Comparable<HistoryLogRecord> {
    var timestamp: Long = 0L
    var commandLine: String = ""

    init {
        for (line in record.lines()) {
            if (line.isBlank()) continue
            val colonIndex = line.indexOf(':')
            if (colonIndex == -1) continue

            val type = line.substring(0, colonIndex).trim()
            val value = line.substring(colonIndex + 1).trim()

            when (type) {
                "Start-Date" -> {
                    // Convertim data în timestamp
                    timestamp = parseDateToTimestamp(value)
                }
                "Commandline" -> commandLine = value
            }
        }
    }

    private fun parseDateToTimestamp(dateStr: String): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        return try {
            val date = dateFormat.parse(dateStr)
            date.time
        } catch (e: Exception) {
            0L
        }
    }


    override fun compareTo(other: HistoryLogRecord): Int {
        return timestamp.compareTo(other.timestamp)
    }

    override fun toString(): String {
        return "Timestamp: $timestamp, Command: $commandLine"
    }
}


fun <T : Comparable<T>> max(a: T, b: T): T {
    return if (a > b) a else b
}

fun main(args: Array<String>) {
    val contents = File("C:\\Users\\User59\\Documents\\GitHub\\PPLABS\\Tema7\\src\\history.log").readText()


    val recordsList = contents.split(Regex("\\r\\n\\r\\n"))
        .map { HistoryLogRecord(it) }
        .filter { it.timestamp != 0L } // Filtrăm înregistrările invalide


    val last50Records = recordsList.takeLast(50)


    if (last50Records.size > 1) {
        val maxRecord = max(last50Records[0], last50Records[1])
        println("""Max between 'a' and 'b' is 'c'
            - 'a' = ${last50Records[0]}
            - 'b' = ${last50Records[1]}
            - 'c' = $maxRecord
        """.trimMargin())
    }

    
    println("All records:")
    last50Records.forEach { println(it) }
}
