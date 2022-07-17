import SpyClassicCar.Companion.any
import kotlin.test.Test
import kotlin.test.assertEquals

class SpyClassicCar : Car by ClassicCar() {

    private var calledCount = 0
    private var expectedCount = 0

    override fun fillFuelTank(amount: Int) {
        if (fuelTankCapacity - availableFuel < amount) throw IllegalStateException()

        availableFuel += amount

        calledCount += 1
    }

    fun verify(countInvoke: Int, block: () -> Unit) {
        expectedCount += countInvoke
        repeat(countInvoke) { block() }

        assertEquals(expectedCount, calledCount - expectedCount)
    }

    companion object {
        fun any() = 0
    }
}

class ClassicGasStationTest {

    @Test
    fun shouldFillTheFuelTankOfTheCarWhenRequestedInAGasStation() {
        val spyClassicCar = SpyClassicCar()
        val classicGasStation = ClassicGasStation()

        classicGasStation.fillFuel(spyClassicCar, 10)

        spyClassicCar.verify(countInvoke = 1) { spyClassicCar.fillFuelTank(any()) }
    }
}
