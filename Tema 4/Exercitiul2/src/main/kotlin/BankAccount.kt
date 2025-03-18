import java.time.LocalDate

typealias Date = LocalDate

class BankAccount(
    private var availableAmount: Double,
    private val cardNumber: String,
    private val expirationDate: Date,
    private val cvvCode: Int,
    private val userName: String,
) {

    fun updateAmount(delta: Double): Boolean {
        if (delta > availableAmount) {
            return false
        }

        availableAmount -= delta
        return true
    }
}