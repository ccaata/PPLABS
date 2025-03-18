class TicketManager(private val movies: Map<String, Double>) {
    fun buyTicketFor(movie: String, paymentMethod: PaymentMethod): String? {
        val fee = movies[movie] ?: return null

        if (!paymentMethod.pay(fee)) {
            return null
        }

        return """
                  You bought 
            ----------------------
                  Ticket for      
                 $movie  $fee      
            ----------------------
        """.trimIndent()
    }
}