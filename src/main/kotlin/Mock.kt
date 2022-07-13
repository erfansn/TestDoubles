data class User(val userName: String, val password: String)

val User.isValid get() = userName.isNotEmpty() && password.isNotEmpty()

interface WebService {
    val users: List<User>
    fun registerUser(user: User)
    fun verifyUser(user: User): Boolean
}

interface UserAuthentication {
    fun signUp(user: User)
    fun login(user: User): Boolean
}

class WebServiceImpl : WebService {

    override val users = mutableListOf<User>()

    override fun registerUser(user: User) {
        users += user
    }

    override fun verifyUser(user: User): Boolean {
        return user in users
    }
}

class UserAuthenticationImpl(
    private val webService: WebService
) : UserAuthentication {

    override fun signUp(user: User) {
        if (!user.isValid) throw IllegalArgumentException()

        webService.registerUser(user)
    }

    override fun login(user: User): Boolean {
        return webService.verifyUser(user)
    }
}

fun main() {
    val webService = WebServiceImpl()
    val userAuthentication = UserAuthenticationImpl(webService)

    val user = User(
        userName = "ErfanSn",
        password = "Mock"
    )
    userAuthentication.signUp(user)

    print("Login was ${userAuthentication.login(user)}")
}
