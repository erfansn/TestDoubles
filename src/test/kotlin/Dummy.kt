import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * The Dummy is the simplest test double.
 * It has the sole purpose of being passed as argument, while not having much relevance to the test itself.
 */
class LoggerDummy : Logger {

    override fun log(message: String) = Unit
}

class DefaultProfileTest {

    @Test
    fun shouldSaveNameWhenPassedToArgument() {
        val name = "Dummy"
        val profile = DefaultProfile(LoggerDummy())

        profile.saveToFavorites(name)

        assertEquals(profile.favorites.first(), name)
    }
}