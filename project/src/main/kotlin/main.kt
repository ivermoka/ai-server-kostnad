import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

fun main() {
    val currentDateTime = LocalDateTime.now()
    val year = currentDateTime.year
    val date = SimpleDateFormat("MM-dd").format(Date())

    val powerUsage = Usage("/Users/ivermorlandk/Repositories/ai-server-kostnad/project/src/main/kotlin/data.json")
    val price = Price(year, date)
    var totalCost = 0.0

    powerUsage.hourlyAverageWattUsage.forEachIndexed { index, item ->
        val hourCost = (item.watt_avg / 1000.0) * price.nokPerKWh[index]
        totalCost += hourCost
        println("Hour ${item.hour + 1}: ${String.format("%.2f", hourCost)} NOK")
    }
    println("Summarized: ${String.format("%.2f", totalCost)} NOK")
}