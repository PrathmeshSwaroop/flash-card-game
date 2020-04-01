package flashcards.game

data class FlashCard(val card: String,
                     var definition: String,
                     var errorCount: Int = 0)