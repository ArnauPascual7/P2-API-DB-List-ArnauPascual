package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex

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
    if (games != null) {
        SimpleSearchBar(rememberTextFieldState(), { text -> println(text) },games)
    }
    /*
    Column(Modifier.fillMaxSize().padding(25.dp)) {
        if (games != null) {
            Text("Hola!")
        }
        else {
            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                CircularProgressIndicator()
            }
        }
    }*/
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSearchBar(textFieldState: TextFieldState, onSearch: (String) -> Unit, games: List<Game>, modifier: Modifier = Modifier){
    var expanded by rememberSaveable { mutableStateOf(false) }
    Box(Modifier.fillMaxSize().semantics { isTraversalGroup = true }) {
        SearchBar(
            modifier = Modifier.align(Alignment.TopCenter).semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = textFieldState.text.toString(),
                    onQueryChange = { textFieldState.edit { replace(0, length, it) } },
                    onSearch = {
                        onSearch(textFieldState.text.toString())
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Cercar") }
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                games.forEach { game ->
                    ListItem(
                        headlineContent = { Text(game.title) },
                        modifier = Modifier.clickable {
                            textFieldState.edit { replace(0, length, game.title) }
                            expanded = false
                        }.fillMaxWidth()
                    )
                }
            }
        }
    }
}