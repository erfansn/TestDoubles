import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * The Dummy is the simplest test double.
 * It has the sole purpose of being passed as argument, while not having much relevance to the test itself.
 */
class DummyB : B {

    override fun log(message: String) = Unit
}

class TestA {

    @Test
    fun shouldSaveNameWhenPassedToArgument() {
        val name = "Dummy"
        val a = AImpl(DummyB())

        a.saveName(name)

        assertEquals(a.names.first(), name)
    }
}