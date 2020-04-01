package flashcards.game.commands

/**
 * Created By Prathmesh
 */
interface UserCommand<in A> {
    val value: String
    val function: (A, String?) -> Unit
}