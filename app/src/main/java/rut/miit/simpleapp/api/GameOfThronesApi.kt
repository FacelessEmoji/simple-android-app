import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import rut.miit.simpleapp.data.Character

object GameOfThronesApi {

    private const val BASE_URL = "https://www.anapioficeandfire.com/api/"

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }

    suspend fun getCharacters(start: Int, count: Int): List<Character> {
        return try {
            val response = client.get {
                url("${BASE_URL}characters?page=$start&pageSize=$count")
                contentType(ContentType.Application.Json)
            }
            response.body()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun closeClient() {
        client.close()
    }
}