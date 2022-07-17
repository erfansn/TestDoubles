interface Car {
    var availableFuel: Int
    val fuelTankCapacity: Int
    fun fillFuelTank(amount: Int)
}

interface GasStation {
    var totalAvailableFuel: Long
    fun fillFuel(car: Car, amount: Int)
}

class ClassicCar : Car {

    override var availableFuel: Int = 10
    override val fuelTankCapacity: Int = 37

    override fun fillFuelTank(amount: Int) {
        if (fuelTankCapacity - availableFuel < amount) throw IllegalStateException()

        availableFuel += amount
    }
}

class ClassicGasStation : GasStation {

    override var totalAvailableFuel: Long = 500L

    override fun fillFuel(car: Car, amount: Int) {
        car.fillFuelTank(amount)

        totalAvailableFuel -= amount
    }
}

fun main() {
    val classicCar = ClassicCar()
    val classicGasStation = ClassicGasStation()

    println("Available fuel is ${classicGasStation.totalAvailableFuel}")
    classicGasStation.fillFuel(classicCar, 10)
    println("After fueling its amount decreased to ${classicGasStation.totalAvailableFuel}")
}
