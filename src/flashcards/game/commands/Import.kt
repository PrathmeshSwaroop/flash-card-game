package flashcards.game.commands

import flashcards.game.FlashCard
import flashcards.game.FlashCardGame
import flashcards.game.FlashCardGame.Companion.CARD_IMPORT_ERROR_1
import flashcards.game.FlashCardGame.Companion.CARD_IMPORT_MESSAGE_1
import flashcards.game.FlashCardGame.Companion.IMPORT
import flashcards.game.FlashCardGame.Companion.OBJECT_SPLIT_EXPRESSION
import flashcards.game.FlashCardGame.Extension.importCard
import flashcards.game.FlashCardGame.Extension.log
import java.io.File
import java.io.FileNotFoundException
import java.util.*

/**
 * Created By Prathmesh
 */
data class Import(override val value: String = IMPORT,
                  override val function: (map: LinkedList<FlashCard>, arg: String?) -> Unit = { it, arg ->
                      val scanner = Scanner(System.`in`)
                      val fileName = if (!arg.isNullOrEmpty()) {
                          arg
                      } else {
                          println(FlashCardGame.FILE_NAME_INPUT.log())
                          scanner.nextLine().log()
                      }
                      try {
                          val file = File(fileName)
                          val fileScanner = Scanner(file)
                          var cardCount = 0
                          while (fileScanner.hasNextLine()) {
                              val record = fileScanner.nextLine().split(OBJECT_SPLIT_EXPRESSION)
                              it.importCard(FlashCard(record[0], record[1], record[2].toInt()))
                              cardCount++
                          }
                          println(CARD_IMPORT_MESSAGE_1.format(cardCount).log())
                      } catch (e: FileNotFoundException) {
                          println(CARD_IMPORT_ERROR_1.log())
                      }

                  }) : UserCommand<LinkedList<FlashCard>>