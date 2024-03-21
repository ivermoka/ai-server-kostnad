fun main() {
    val powerUsage = Usage("/Users/ivermorlandk/Repositories/ai-server-kostnad/project/src/main/kotlin/data.json")
    val price = Price("03-13")
    var totalCost = 0.0

    powerUsage.hourlyAverageWattUsage.forEachIndexed { index, item ->
        val hourCost = (item.watt_avg / 1000.0) * price.nokPerKWh[index]
        totalCost += hourCost
        println("Hour ${item.hour}: ${String.format("%.2f", hourCost)} NOK")
    }
    println("Summarized: ${String.format("%.2f", totalCost)} NOK")
}