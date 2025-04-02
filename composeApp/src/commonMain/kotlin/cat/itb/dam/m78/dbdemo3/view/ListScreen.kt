package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.Json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Serializable
data class Game(
    val id: Int,
    val title: String,
    val thumbnail: String,
    @SerialName("short_description")
    val desc: String,
    val genre: String
)

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

@OptIn(InternalComposeApi::class)
@Composable
fun List2Screen() {
    val viewmodel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it) { GamesViewModel() } }
    val games = viewmodel?.games
    Column(Modifier.fillMaxSize().padding(25.dp)) {
        if (games != null) {
            LazyColumn {
                games.forEach { game ->
                    item {
                        Row {
                            Text(game.title)
                        }
                    }
                }
            }
        }
        else {
            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                CircularProgressIndicator()
            }
        }
    }
}