interface Profile {
    fun saveToFavorites(name: String)
}

interface Logger {
    fun log(message: String)
}

class DefaultProfile(private val logger: Logger) : Profile {

    val favorites = mutableListOf<String>()

    override fun saveToFavorites(name: String) {
        favorites += name
        logger.log("The $name added to favorites list")
    }
}

class DefaultLogger : Logger {

    override fun log(message: String) {
        println(message)
    }
}

fun main() {
    val logger = DefaultLogger()
    val profile = DefaultProfile(logger)

    val names = arrayOf("Kotlin", "TestDouble", "Dummy")
    for (name in names) {
        profile.saveToFavorites(name)
    }
}
