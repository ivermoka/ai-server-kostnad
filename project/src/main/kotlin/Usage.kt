import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class HourlyAverageWattUsage(
    val hour: Int,
    val watt_avg: Int
)

class Usage(private val filePath: String) {
    val hourlyAverageWattUsage: List<HourlyAverageWattUsage>

    init {
        try {
            val json = Json { ignoreUnknownKeys = true }
            hourlyAverageWattUsage = json.decodeFromString(File(filePath).readText())
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
            throw e
        }
    }
}