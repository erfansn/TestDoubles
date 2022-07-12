interface PeripheralDevice {
    val connected: Boolean
    fun dispatchKey()
}

interface CentralDevice

interface ConnectionManager {
    val connections: Map<PeripheralDevice, CentralDevice?>
    fun PeripheralDevice.connectTo(centralDevice: CentralDevice)
    fun PeripheralDevice.disconnect()
}

class Keyboard(private val connectionManager: ConnectionManager) : PeripheralDevice {

    override val connected: Boolean
        get() = connectionManager.connections.containsKey(this)

    override fun dispatchKey() {
        check(connected) { "The central device isn't available" }
    }
}

class Computer : CentralDevice

class ConnectionManagerImpl : ConnectionManager {

    override val connections = mutableMapOf<PeripheralDevice, CentralDevice?>()

    override fun PeripheralDevice.connectTo(centralDevice: CentralDevice) {
        check(!connected) { "For each peripheral device one and only one central device can be connected" }

        connections[this] = centralDevice
    }

    override fun PeripheralDevice.disconnect() {
        connections[this] = null
    }
}

fun main() {
    val connectionManager = ConnectionManagerImpl()

    val keyboard = Keyboard(connectionManager)
    val computer = Computer()

    with(connectionManager) {
        keyboard.connectTo(computer)
        keyboard.disconnect()
    }

    keyboard.dispatchKey()
}
