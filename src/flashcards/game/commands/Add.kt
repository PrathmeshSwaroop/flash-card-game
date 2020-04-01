package flashcards.game.commands

import flashcards.game.FlashCard
import flashcards.game.FlashCardGame.Companion.ADD
import flashcards.game.FlashCardGame.Companion.CARD_ADD_ERROR_1
import flashcards.game.FlashCardGame.Companion.CARD_ADD_ERROR_2
import flashcards.game.FlashCardGame.Companion.CARD_ADD_MESSAGE_2
import flashcards.game.FlashCardGame.Companion.CARD_ADD_MESSAGE_3
import flashcards.game.FlashCardGame.Companion.CARD_ADD_MESSAGE_4
import flashcards.game.FlashCardGame.Extension.containsCard
import flashcards.game.FlashCardGame.Extension.containsDefinition
import flashcards.game.FlashCardGame.Extension.log
import java.util.*

/**
 * Created By Prathmesh
 */
data class Add(override val value: String = ADD,
               override val function: (map: LinkedList<FlashCard>, arg: String?) -> Unit = { it, arg ->
                   val scanner = Scanner(System.`in`)
                   println(CARD_ADD_MESSAGE_3.log())
                   val card = scanner.nextLine().log()
                   if (it.containsCard(card)) {
                       println(CARD_ADD_ERROR_1.format(card).log())
                   } else {
                       println(CARD_ADD_MESSAGE_4.log())
                       val definition = scanner.nextLine().log()
                       if (it.containsDefinition(definition)) {
                           println(CARD_ADD_ERROR_2.format(definition).log())
                       } else {
                           it.add(FlashCard(card, definition))
                           println(CARD_ADD_MESSAGE_2.format(card, definition).log())
                       }
                   }
               }) : UserCommand<LinkedList<FlashCard>>
