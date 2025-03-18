import java.io.File

class NoteManager {
    private val notes: MutableList<Note> = mutableListOf()
    private var currentId = 1

    init {
        val notesDirectory = File("notes/")
        if (!notesDirectory.exists()) {
            notesDirectory.mkdirs() // Crează directorul dacă nu există
        }

        val userDirectories = notesDirectory.listFiles()
        if (userDirectories == null) {
            println("No user directories found.")

        }

        for (userDirectory in userDirectories) {
            if (!userDirectory.isDirectory) continue

            val user = User(userDirectory.name)
            val noteFiles = userDirectory.listFiles()
            if (noteFiles == null) continue

            for (note in noteFiles) {
                val id = note.name.replace(".txt", "").toIntOrNull() ?: continue
                currentId = maxOf(currentId, id)
                Note.fromString(user, id, note.readText())?.let { notes.add(it) }
            }
        }
    }


    fun createNote(user: User, title: String, date: Date, time: Time, contents: String): Note {
        val note = Note(user, ++currentId, title, date, time, contents)
        notes.add(note)

        return note
    }

    fun deleteNote(user: User, id: Int) {
        for (note in notes) {
            if (note.getId() == id && note.getUser() == user) {
                val file = File(note.getPath())
                file.delete()
            }
        }
    }

    fun getNotesFor(user: User): List<Note> {
        val notesForUser = mutableListOf<Note>()

        for (note in notes) {
            if (note.getUser() == user) {
                notesForUser.add(note)
            }
        }

        return notesForUser
    }

    fun getNote(user: User, id: Int): Note? {
        for (note in notes) {
            if (note.getUser() == user && note.getId() == id) {
                return note
            }
        }

        return null
    }

    fun writeToDisk() {
        for (note in notes) {
            val file = File(note.getPath())


            file.parentFile?.mkdirs()

            println(note)
            file.writeText(note.serialized())
        }
    }
}
