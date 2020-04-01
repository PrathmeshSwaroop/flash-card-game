package flashcards.game.commands

import flashcards.game.FlashCard
import flashcards.game.FlashCardGame
import flashcards.game.FlashCardGame.Companion.CARD_RESET_STATE_MESSAGE_1
import flashcards.game.FlashCardGame.Extension.log
import java.util.*

data class ResetStats(override val value: String = FlashCardGame.RESET_STATS,
                      override val function: (map: LinkedList<FlashCard>, arg: String?) -> Unit = { it, arg ->
                          for (card in it) {
                              card.errorCount = 0
                          }
                          println(CARD_RESET_STATE_MESSAGE_1.log())
                      }) : UserCommand<LinkedList<FlashCard>>