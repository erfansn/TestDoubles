import java.lang.Thread.sleep
import kotlin.random.Random

enum class Symbol { BTC, ETC }

interface CryptocurrencyApi {
    fun fetchSellPrice(symbol: Symbol): Float
}

interface CryptocurrencyExchange {
    fun sellCoins(count: Int, symbol: Symbol): Float
}

class CryptocurrencyApiImpl : CryptocurrencyApi {
    override fun fetchSellPrice(symbol: Symbol): Float {
        sleep(5000L)
        return Random.nextFloat() * when (symbol) {
            Symbol.BTC -> (10000..40000).random()
            Symbol.ETC -> (100..1000).random()
        }
    }
}

class CryptocurrencyExchangeImpl(private val cryptocurrencyApi: CryptocurrencyApi): CryptocurrencyExchange {

    override fun sellCoins(count: Int, symbol: Symbol): Float {
        return cryptocurrencyApi.fetchSellPrice(symbol) * count
    }
}

fun main() {
    val cryptocurrencyApi = CryptocurrencyApiImpl()
    val cryptocurrencyExchange = CryptocurrencyExchangeImpl(cryptocurrencyApi)

    println(cryptocurrencyExchange.sellCoins(2, Symbol.BTC))
}
