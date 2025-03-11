import java.io.File

// Funcție pentru normalizarea textului: elimină spațiile și liniile noi multiple
fun normalizeText(text: String): String {
    val singleSpacedText = text.replace(Regex("\\s+"), " ")
    return singleSpacedText.replace(Regex("\\n+"), "\n")
}

// Funcție pentru eliminarea numerelor de pagină
fun removePageNumbers(text: String): String {
    return text.replace(Regex("\\s+\\d+\\s+"), " ")
}

// Funcție pentru eliminarea numelui autorului
fun removeAuthorName(text: String): String {
    return text.replace(Regex("\\b(\\w+\\s+\\w+)\\b.*\\1\\b"), "")
}

// Funcție pentru eliminarea titlurilor capitolelor
fun removeChapterTitle(text: String): String {
    return text.replace(Regex("Capitolul?\\s+\\d+.*\\n"), "")
}

// Funcție pentru corectarea caracterelor românești
fun correctRomanianCharacters(text: String): String {
    val oldChars = mapOf(
        'ă' to 'a',
        'â' to 'a',
        'î' to 'i',
        'ș' to 's',
        'ț' to 't'
        // Adăugați aici toate mapările necesare
    )
    var correctedText = text
    for ((oldChar, newChar) in oldChars) {
        correctedText = correctedText.replace(oldChar, newChar)
    }
    return correctedText
}

// Funcție principală pentru procesarea fișierului
fun processEbook(inputFilePath: String, outputFilePath: String) {
    val text = File(inputFilePath).readText()
    val processedText = text
        .let { normalizeText(it) }
        .let { removePageNumbers(it) }
        .let { removeAuthorName(it) }
        .let { removeChapterTitle(it) }
        .let { correctRomanianCharacters(it) }
    File(outputFilePath).writeText(processedText)
}

// Funcția main care rulează aplicația
fun main() {
    val inputFilePath = "C:\\Users\\User59\\Documents\\GitHub\\PPLABS\\Tema 3\\ebook.txt.txt"
    val outputFilePath = "C:\\Users\\User59\\Documents\\GitHub\\PPLABS\\Tema 3\\output.txt"
    processEbook(inputFilePath, outputFilePath)
}
