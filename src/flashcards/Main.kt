package flashcards

import flashcards.game.FlashCard
import flashcards.game.FlashCardGame
import flashcards.game.FlashCardGame.Companion.EXPORT
import flashcards.game.FlashCardGame.Companion.IMPORT
import flashcards.game.FlashCardGame.Extension.log
import flashcards.game.FlashCardGame.Extension.toFlashCommand
import flashcards.game.commands.UserCommand
import java.util.*
import kotlin.collections.HashMap

/**
 * Created By Prathmesh
 */
fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val gameInstance = FlashCardGame()
    processArguments(args, gameInstance).let {
        if (it.size > 0) {
            IMPORT.toFlashCommand(gameInstance)?.apply {
                if (it.containsKey(this)) {
                    gameInstance.execute(this, it[this]!!)
                }
            }
        }

        gameLoop@ while (true) {
            gameInstance.printCommandList()
            val command = scanner.nextLine().log().toFlashCommand(gameInstance)
            command?.run {
                gameInstance.execute(this)
            }
            if (command != null && command.value == FlashCardGame.EXIT) {
                break@gameLoop
            }
        }

        if (it.size > 0) {
            EXPORT.toFlashCommand(gameInstance)?.apply {
                if (it.containsKey(this)) {
                    gameInstance.execute(this, it[this]!!)
                }
            }
        }

    }
}

fun processArguments(args: Array<String>, gameInstance: FlashCardGame): HashMap<UserCommand<LinkedList<FlashCard>>, String> {
    val command = HashMap<UserCommand<LinkedList<FlashCard>>, String>()
    if (args.isNotEmpty()) {
        var i = 0
        while (i <= args.lastIndex) {
            if (args[i].contains("-")) {
                val subSeq = args[i].subSequence(1, args[i].length).toString()
                subSeq.toFlashCommand(gameInstance)?.let {
                    command[it] = args[i + 1]
                }
            }
            i++
        }
    }
    return command
}
