package flashcards.game.commands

import flashcards.game.FlashCard
import flashcards.game.FlashCardGame
import flashcards.game.FlashCardGame.Companion.CARD_REMOVE_ERROR_1
import flashcards.game.FlashCardGame.Companion.CARD_REMOVE_MESSAGE_1
import flashcards.game.FlashCardGame.Companion.REMOVE
import flashcards.game.FlashCardGame.Extension.containsCard
import flashcards.game.FlashCardGame.Extension.log
import flashcards.game.FlashCardGame.Extension.removeCard
import java.util.*

/**
 * Created By Prathmesh
 */
data class Remove(override val value: String = REMOVE,
                  override val function: (map: LinkedList<FlashCard>, arg: String?) -> Unit = { it, arg ->
                      val scanner = Scanner(System.`in`)
                      println(FlashCardGame.CARD_ADD_MESSAGE_3.log())
                      val card = scanner.nextLine().log()
                      if (!it.containsCard(card)) {
                          println(CARD_REMOVE_ERROR_1.format(card).log())
                      } else {
                          it.removeCard(card)
                          println(CARD_REMOVE_MESSAGE_1.log())
                      }
                  }) : UserCommand<LinkedList<FlashCard>>
