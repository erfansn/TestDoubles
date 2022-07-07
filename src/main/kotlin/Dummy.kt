interface A {
    fun saveName(name: String)
}

interface B {
    fun log(message: String)
}

class AImpl(private val b: B) : A {

    val names = mutableListOf<String>()

    override fun saveName(name: String) {
        names += name
        b.log("The $name saved")
    }
}

class BImpl : B {

    override fun log(message: String) {
        println(message)
    }
}

fun main() {
    val b = BImpl()
    val a = AImpl(b)

    val names = arrayOf("Kotlin", "TestDouble", "Dummy")
    for (name in names) {
        a.saveName(name)
    }
}
