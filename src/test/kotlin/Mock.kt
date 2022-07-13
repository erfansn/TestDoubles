import kotlin.test.Test
import kotlin.test.assertEquals

class MockWebService : WebService {

    private var calledCount = 0
    private var expectedCount = 0

    override val users: List<User>
        get() = TODO("Not yet implemented")

    override fun registerUser(user: User) {
        calledCount += 1
    }

    override fun verifyUser(user: User): Boolean {
        TODO("Not yet implemented")
    }

    fun verify(countInvoke: Int, block: () -> Unit) {
        expectedCount += countInvoke
        repeat(countInvoke) { block() }

        assertEquals(expectedCount, calledCount - expectedCount)
    }
}

class UserAuthenticationTest {

    @Test
    fun shouldCallRegisterUserWhenSignUpUser() {
        val mockWebService = MockWebService()
        val userAuthentication = UserAuthenticationImpl(mockWebService)
        val testerUser = User("Test", "Mock")

        userAuthentication.signUp(testerUser)

        mockWebService.verify(countInvoke = 1) { mockWebService.registerUser(testerUser) }
    }
}
