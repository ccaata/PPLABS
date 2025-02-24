import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextProcessor {

    // Elimină semnele de punctuație din text
    public static String removePunctuation(String text) {
        return text.replaceAll("[\\p{Punct}]", "");
    }

    // Convertește textul la lower case
    public static String toLowerCase(String text) {
        return text.toLowerCase();
    }

    // Elimină spațiile multiple din text
    public static String removeExtraSpaces(String text) {
        return text.trim().replaceAll("\\s+", " ");
    }

    public static void main(String[] args) {
        String fileName = "input.txt";
        try {
            // Citește tot conținutul fișierului
            String content = new String(Files.readAllBytes(Paths.get(fileName)));

            // Procesare în trei etape:
            String processed = removePunctuation(content);
            processed = toLowerCase(processed);
            processed = removeExtraSpaces(processed);

            System.out.println("Text procesat:");
            System.out.println(processed);

        } catch (IOException e) {
            System.err.println("Eroare la citirea fișierului: " + e.getMessage());
        }
    }
}
