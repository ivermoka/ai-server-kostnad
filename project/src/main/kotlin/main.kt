import it.justwrote.kjob.InMem
import it.justwrote.kjob.KronJob
import it.justwrote.kjob.kjob
import it.justwrote.kjob.kron.Kron
import it.justwrote.kjob.kron.KronModule

// define a Kron job with a name and a cron expression (e.g. 5 seconds)
object PrintStuff : KronJob("print-stuff", "*/5 * * ? * * *")

// ...



fun main() {
    val kjob = kjob(InMem) {
        extension(KronModule) // enable the Kron extension
    }

// ...

// define the executed code
    kjob(Kron).kron(PrintStuff) {
        maxRetries = 3 // and you can access the already familiar settings you are used to
        execute {
            println("${Instant.now()}: executing kron task '${it.name}' with jobId '$jobId'")
        }
    }
    val price = Price("03-13")
    println(price.nokPerKWh)
}