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

    // Инициализация HttpClient с необходимыми настройками
    private val client = HttpClient(CIO) {
        // Плагин для обработки JSON с поддержкой игнорирования неизвестных полей и prettyPrint для удобства
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // Игнорирование неизвестных полей
                prettyPrint = true // Красивый вывод JSON
                isLenient = true // Снижение строгости обработки JSON
            })
        }

        // Включаем логирование для отслеживания запросов и ответов
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO // Уровень логирования
        }
    }

    // Функция для получения персонажей, где start - это страница, а count - количество персонажей на странице
    suspend fun getCharacters(start: Int, count: Int): List<Character> {
        return try {
            // Выполнение GET-запроса к API
            val response = client.get {
                url("${BASE_URL}characters?page=$start&pageSize=$count")
                contentType(ContentType.Application.Json) // Указываем тип контента
            }
            // Возвращаем десериализованный список персонажей
            response.body()
        } catch (e: Exception) {
            e.printStackTrace() // Логируем ошибку
            emptyList() // Возвращаем пустой список при ошибке
        }
    }

    // Функция для закрытия клиента при необходимости
    fun closeClient() {
        client.close()
    }
}