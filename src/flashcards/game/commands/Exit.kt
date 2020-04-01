package flashcards.game.commands

import flashcards.game.FlashCard
import flashcards.game.FlashCardGame
import flashcards.game.FlashCardGame.Companion.CARD_EXIT_MESSAGE_1
import flashcards.game.FlashCardGame.Companion.EXIT
import java.util.*

/**
 * Created By Prathmesh
 */
data class Exit(override val value: String = EXIT,
                override val function: (map: LinkedList<FlashCard>, arg: String?) -> Unit = { it, arg ->
                    FlashCardGame.Extension.logList.clear()
                    println(CARD_EXIT_MESSAGE_1)
                }) : UserCommand<LinkedList<FlashCard>>