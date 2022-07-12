import kotlin.test.Test
import java.lang.Thread.sleep
import kotlin.test.assertEquals

/**
 * The Fake is a test double with a very similar purpose to Stub:
 * providing simple and quick answers to a client who consumes it.
 *
 * The main difference is that the Fake uses a simple and lightweight working implementation under the hoods.
 */
class FakeCryptocurrencyApi : CryptocurrencyApi {
    override fun fetchSellPrice(symbol: Symbol): Float {
        sleep(1000)
        return when (symbol) {
            Symbol.BTC -> 2000f
            Symbol.ETC -> 800f
        }
    }
}

class CryptocurrencyExchangeImplTest {

    @Test
    fun shouldCalculateMoneyEarnedThroughSoldCoinsCorrectly() {
        val fakeCryptocurrencyApi = FakeCryptocurrencyApi()
        val cryptocurrencyExchange = CryptocurrencyExchangeImpl(fakeCryptocurrencyApi)

        val amountOfReceivedMoney = cryptocurrencyExchange.sellCoins(2, Symbol.BTC)

        assertEquals(amountOfReceivedMoney, 4000f)
    }
}