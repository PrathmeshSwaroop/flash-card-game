package flashcards.game.commands

import flashcards.game.FlashCard
import flashcards.game.FlashCardGame.Companion.ASK
import flashcards.game.FlashCardGame.Companion.CARD_ASK_ERROR_1
import flashcards.game.FlashCardGame.Companion.CARD_ASK_ERROR_2
import flashcards.game.FlashCardGame.Companion.CARD_ASK_MESSAGE_1
import flashcards.game.FlashCardGame.Companion.CARD_ASK_MESSAGE_2
import flashcards.game.FlashCardGame.Companion.CARD_ASK_MESSAGE_3
import flashcards.game.FlashCardGame.Extension.containsDefinition
import flashcards.game.FlashCardGame.Extension.getCardOfDefinition
import flashcards.game.FlashCardGame.Extension.log
import java.util.*

/**
 * Created By Prathmesh
 */
data class Ask(override val value: String = ASK,
               override val function: (map: LinkedList<FlashCard>, arg: String?) -> Unit = { it, arg ->
                   val scanner = Scanner(System.`in`)
                   val query = { entry: FlashCard, result: (status: Boolean) -> Unit ->
                       println(CARD_ASK_MESSAGE_2.format(entry.card).log())
                       val answer = scanner.nextLine().log()
                       val messageToDisplay = if (answer == entry.definition) {
                           CARD_ASK_MESSAGE_3
                       } else {
                           if (it.containsDefinition(answer)) {
                               CARD_ASK_ERROR_2.format(entry.definition, it.getCardOfDefinition(answer))
                           } else {
                               CARD_ASK_ERROR_1.format(entry.definition)
                           }
                       }
                       result.invoke(answer == entry.definition)
                       println(messageToDisplay.log())
                   }
                   println(CARD_ASK_MESSAGE_1.log())
                   val n = scanner.nextLine().log().toInt()
                   val j = n / (it.lastIndex + 1)
                   val extra = n % (it.lastIndex + 1)
                   for (times in 1..j) {
                       for (i in 0..it.lastIndex) {
                           query.invoke(it[i]) { correct ->
                               if (!correct) {
                                   it[i].errorCount++
                               }
                           }
                           if (i == n - 1) {
                               break
                           }
                       }
                   }
                   for (i in 0 until extra) {
                       query.invoke(it[i]) { correct ->
                           if (!correct) {
                               it[i].errorCount++
                           }
                       }
                   }

               }) : UserCommand<LinkedList<FlashCard>>


