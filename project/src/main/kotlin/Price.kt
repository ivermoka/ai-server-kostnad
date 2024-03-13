import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PriceInfo(
    @SerialName("NOK_per_kWh") val nokPerkWh: Double,
    @SerialName("EUR_per_kWh") val eurPerkWh: Double,
    @SerialName("time_start") val time_start: String
)

class Price(private val date: String) {
    val nokPerKWh: Double
    val eurPerKWh: Double

    init {
        val url = URL("https://www.hvakosterstrommen.no/api/v1/prices/2024/${date}_NO1.json")

        try {
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connect()

            val responseCode = conn.responseCode

            if (responseCode != 200) {
                throw RuntimeException("HttpResponseCode: $responseCode")
            } else {
                val inline = Scanner(url.openStream()).useDelimiter("\\A").next()
                val json = Json { ignoreUnknownKeys = true }
                val priceList = json.decodeFromString<List<PriceInfo>>(inline)

                nokPerKWh = priceList.first().nokPerkWh
                eurPerKWh = priceList.first().eurPerkWh
            }
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            throw e
        }
    }
}
