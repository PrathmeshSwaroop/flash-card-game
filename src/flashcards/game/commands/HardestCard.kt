package flashcards.game.commands

import flashcards.game.FlashCard
import flashcards.game.FlashCardGame
import flashcards.game.FlashCardGame.Companion.CARD_HARDEST_CARD_MESSAGE_1
import flashcards.game.FlashCardGame.Companion.CARD_HARDEST_CARD_MESSAGE_2
import flashcards.game.FlashCardGame.Companion.CARD_HARDEST_CARD_MESSAGE_3
import flashcards.game.FlashCardGame.Extension.log
import java.util.*
import kotlin.collections.ArrayList

data class HardestCard(override val value: String = FlashCardGame.HARDEST_CARD,
                       override val function: (map: LinkedList<FlashCard>, arg: String?) -> Unit = { it, arg ->
                           var maxErrorCount = 0
                           for (card in it) {
                               if (maxErrorCount < card.errorCount) {
                                   maxErrorCount = card.errorCount
                               }
                           }
                           if (maxErrorCount > 0) {
                               val cardNames = ArrayList<String>()
                               for (card in it) {
                                   if (card.errorCount == maxErrorCount) {
                                       cardNames.add("\"${card.card}\"")
                                   }
                               }
                               println((if (cardNames.size > 1) {
                                   val stringBuffer = StringBuffer()
                                   for (i in cardNames.indices) {
                                       stringBuffer.append(cardNames[i])
                                       if (i != cardNames.lastIndex) {
                                           stringBuffer.append(", ")
                                       }
                                   }
                                   CARD_HARDEST_CARD_MESSAGE_3.format(stringBuffer.toString(), maxErrorCount.toString())
                               } else {
                                   CARD_HARDEST_CARD_MESSAGE_2.format(cardNames[0], maxErrorCount.toString())
                               }).log())
                           } else {
                               println(CARD_HARDEST_CARD_MESSAGE_1.log())
                           }

                       }) : UserCommand<LinkedList<FlashCard>>