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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

object FreeGamesApi {
    private const val URL = "https://www.freetogame.com/api/game"
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun getList() = client.get("${URL}s").body<List<Game>>()
    suspend fun getGameById(id: Int) = client.get("${URL}?id=${id}").body<Game>()
}

class GamesViewModel : ViewModel() {
    var games by mutableStateOf<List<Game>?>(null)
    private val _gameId = MutableStateFlow<Int?>(null)
    var game by mutableStateOf<Game?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            games = FreeGamesApi.getList()
            _gameId.collect { id ->
                id?.let {
                    game = FreeGamesApi.getGameById(it)
                }
            }
        }
    }
    fun setGameId(gameId: Int) {
        _gameId.value = gameId
    }
}