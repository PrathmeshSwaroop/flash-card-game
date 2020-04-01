package flashcards.game

import flashcards.game.commands.*
import java.util.*

/**
 * Created By Prathmesh
 */
class FlashCardGame(val commandList: Array<UserCommand<LinkedList<FlashCard>>> = arrayOf(
        Add(),
        Remove(),
        Import(),
        Export(),
        Ask(),
        Exit(),
        Log(),
        HardestCard(),
        ResetStats())) {

    private val cardMap by lazy {
        LinkedList<FlashCard>()
    }

    fun printCommandList() {
        val list = Array(commandList.size) { i ->
            commandList[i].value
        }
        println(CARD_ADD_MESSAGE_1.format(*list))
    }


    fun execute(command: UserCommand<LinkedList<FlashCard>>,inputArgument:String? = "") {
        command.function.invoke(cardMap,inputArgument)
    }

    companion object {
        const val CARD_ADD_ERROR_1 = "The card \"%s\" already exists."
        const val CARD_ADD_ERROR_2 = "The definition \"%s\" already exists."
        const val CARD_ADD_MESSAGE_1 = "Input the action (%s, %s, %s, %s, %s, %s, %s, %s, %s):"
        const val CARD_ADD_MESSAGE_2 = "The pair (\"%s\":\"%s\") has been added."
        const val CARD_ADD_MESSAGE_3 = "The card:"
        const val CARD_ADD_MESSAGE_4 = "The definition of the card:"
        const val CARD_REMOVE_ERROR_1 = "Can't remove \"%s\": there is no such card."
        const val CARD_REMOVE_MESSAGE_1 = "The card has been removed."
        const val FILE_NAME_INPUT = "File name:"
        const val CARD_IMPORT_ERROR_1 = "File not found."
        const val CARD_IMPORT_MESSAGE_1 = "%s cards have been loaded."
        const val CARD_ASK_MESSAGE_1 = "How many times to ask?"
        const val CARD_ASK_MESSAGE_2 = "Print the definition of \"%s\":"
        const val CARD_ASK_MESSAGE_3 = "Correct answer."
        const val CARD_ASK_ERROR_1 = "Wrong answer. The correct one is \"%s\""
        const val CARD_ASK_ERROR_2 = "Wrong answer. (The correct one is \"%s\", you've just written the definition of \"%s\".)"
        const val CARD_EXPORT_MESSAGE_1 = "%s cards have been saved."
        const val CARD_EXIT_MESSAGE_1 = "Bye bye!"
        const val CARD_LOG_MESSAGE_1 = "The log has been saved."
        const val CARD_RESET_STATE_MESSAGE_1 = "Card statistics has been reset."
        const val CARD_HARDEST_CARD_MESSAGE_1 = "There are no cards with errors."
        const val CARD_HARDEST_CARD_MESSAGE_2 = "The hardest card is %s. You have %s errors answering it."
        const val CARD_HARDEST_CARD_MESSAGE_3 = "The hardest cards are %s. You have %s errors answering them."
        const val ADD = "add"
        const val EXIT = "exit"
        const val REMOVE = "remove"
        const val IMPORT = "import"
        const val ASK = "ask"
        const val EXPORT = "export"
        const val LOG = "log"
        const val HARDEST_CARD = "hardest card"
        const val RESET_STATS = "reset stats"
        const val OBJECT_SPLIT_EXPRESSION = ":"
        const val CARD_EXPORT_WRITE_FORMAT = "%s$OBJECT_SPLIT_EXPRESSION%s$OBJECT_SPLIT_EXPRESSION%s"
    }


    object Extension {
        val logList by lazy {
            LinkedList<String>()
        }

        fun String.log(): String {
            logList.add(this)
            return this
        }

        fun LinkedList<FlashCard>.importCard(flashCard: FlashCard) {
            val (cardName, definition, errorCount) = flashCard
            var flag = 0
            for (card in this) {
                if (card.card == cardName) {
                    card.definition = definition
                    card.errorCount = errorCount
                    flag = 1
                }
            }
            if (flag == 0) {
                this.add(flashCard)
            }
        }

        fun String.toFlashCommand(gameInstance: FlashCardGame): UserCommand<LinkedList<FlashCard>>? {
            for (command in gameInstance.commandList) {
                if (command.value == this) {
                    return command
                }
            }
            return null
        }

        fun LinkedList<FlashCard>.getCardOfDefinition(answer: String): String? {
            for (flashCard in this) {
                if (flashCard.definition == answer) {
                    return flashCard.card
                }
            }
            return null
        }

        fun LinkedList<FlashCard>.containsCard(cardValue: String?): Boolean {
            for (flashCard in this) {
                if (flashCard.card == cardValue) {
                    return true
                }
            }
            return false
        }

        fun LinkedList<FlashCard>.containsDefinition(definition: String?): Boolean {
            for (flashCard in this) {
                if (flashCard.definition == definition) {
                    return true
                }
            }
            return false
        }

        fun LinkedList<FlashCard>.removeCard(card: String?) {
            for (flashCard in this) {
                if (flashCard.card == card) {
                    this.remove(flashCard)
                    break
                }
            }
        }
    }
}