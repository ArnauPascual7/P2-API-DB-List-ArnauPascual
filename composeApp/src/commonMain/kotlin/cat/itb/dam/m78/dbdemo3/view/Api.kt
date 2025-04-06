package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.dam.m78.dbdemo3.model.Game
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

object FreeGamesApi {
    private const val URL = "https://www.freetogame.com/api/games"
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun getList() = client.get(URL).body<List<Game>>()
}

class GamesViewModel : ViewModel() {
    var games by mutableStateOf<List<Game>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            games = FreeGamesApi.getList()
        }
    }
}