import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

/**
 * The Stub is a test double that provides fixed or pre-configured answers to replace the actual implementation of a dependency.
 */
class StubPeripheralDevice(override val connected: Boolean) : PeripheralDevice {

    override fun dispatchKey() = Unit
}

class ConnectionManagerImplTest {

    @Test
    fun shouldThrowExceptionIfPeripheralDeviceHasConnectedBefore() {
        val connectionManager = ConnectionManagerImpl()
        val stubPeripheralDevice = StubPeripheralDevice(true)
        val computer = Computer()

        assertThrows<IllegalStateException> {
            with(connectionManager) { stubPeripheralDevice.connectTo(computer) }
        }
    }
}