import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class StubPeripheralDevice : PeripheralDevice {
    override val connected: Boolean = true

    override fun dispatchKey() = Unit
}

class ConnectionManagerImplTest {

    @Test
    fun shouldThrowExceptionIfPeripheralDeviceHasConnectedBefore() {
        val connectionManager = ConnectionManagerImpl()
        val stubPeripheralDevice = StubPeripheralDevice()
        val computer = Computer()

        assertThrows<IllegalStateException> {
            with(connectionManager) { stubPeripheralDevice.connectTo(computer) }
        }
    }
}