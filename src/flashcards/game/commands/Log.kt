package flashcards.game.commands

import flashcards.game.FlashCard
import flashcards.game.FlashCardGame
import flashcards.game.FlashCardGame.Companion.CARD_LOG_MESSAGE_1
import flashcards.game.FlashCardGame.Companion.FILE_NAME_INPUT
import flashcards.game.FlashCardGame.Extension.log
import java.io.File
import java.util.*

data class Log(override val value: String = FlashCardGame.LOG,
               override val function: (map: LinkedList<FlashCard>, arg: String?) -> Unit = { it, arg ->
                   val scanner = Scanner(System.`in`)
                   println(FILE_NAME_INPUT.log())
                   val logFileName = scanner.nextLine().log()
                   val file = File(logFileName)
                   val writer = file.bufferedWriter()
                   println(CARD_LOG_MESSAGE_1.log())
                   for (line in FlashCardGame.Extension.logList) {
                       writer.write(line)
                       writer.newLine()
                   }
                   writer.flush()
                   writer.close()
               }) : UserCommand<LinkedList<FlashCard>>