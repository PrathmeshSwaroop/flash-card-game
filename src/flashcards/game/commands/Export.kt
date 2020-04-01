package flashcards.game.commands

import flashcards.game.FlashCard
import flashcards.game.FlashCardGame
import flashcards.game.FlashCardGame.Companion.CARD_EXPORT_MESSAGE_1
import flashcards.game.FlashCardGame.Companion.CARD_EXPORT_WRITE_FORMAT
import flashcards.game.FlashCardGame.Companion.EXPORT
import flashcards.game.FlashCardGame.Extension.log
import java.io.File
import java.util.*

/**
 * Created By Prathmesh
 */
data class Export(override val value: String = EXPORT,
                  override val function: (map: LinkedList<FlashCard>, arg: String?) -> Unit = { it, arg ->
                      val scanner = Scanner(System.`in`)
                      val fileName = if (!arg.isNullOrEmpty()) {
                          arg
                      } else {
                          println(FlashCardGame.FILE_NAME_INPUT.log())
                          scanner.nextLine().log()
                      }

                      val file = File(fileName)
                      file.apply {
                          if (!exists()) {
                              createNewFile()
                          }
                      }
                      val writer = file.bufferedWriter()
                      for (flashCard in it) {
                          writer.write(CARD_EXPORT_WRITE_FORMAT.format(flashCard.card, flashCard.definition, flashCard.errorCount))
                          writer.newLine()
                      }
                      writer.flush()
                      writer.close()
                      println(CARD_EXPORT_MESSAGE_1.format(it.size).log())
                  }) : UserCommand<LinkedList<FlashCard>>