package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.dam.m78.dbdemo3.model.DatabaseViewModel
import coil3.compose.AsyncImage
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

@OptIn(InternalComposeApi::class)
@Composable
fun detailsScreen(navListScreen: () -> Unit, gameId: Int) {
    val dbViewModel = DatabaseViewModel()
    val gameViewModel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it) { GamesViewModel() } }
    gameViewModel?.setGameId(gameId)
    val game = gameViewModel?.game
    if (game != null) {
        Scaffold(
            bottomBar = {
                Button(
                    modifier = Modifier.fillMaxWidth().padding(5.dp).padding(bottom = 10.dp),
                    onClick = { navListScreen() },
                    shape = RectangleShape
                ) {
                    Text("Tornar", fontWeight = FontWeight.Bold)
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        if (dbViewModel.allGames.value.find { table -> table.gameid.toInt() == game.id } != null) {
                            dbViewModel.deleteGame(gameId.toLong())
                        }
                        else {
                            dbViewModel.insertGame(game)
                        }
                    }
                ) {
                    Icon(Icons.Default.Star, contentDescription = "Preferit")
                }
            }
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(bottom = 150.dp, end = 15.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(
                    onClick = {
                        val settings = Settings()
                        val pinnedGameId =  settings.getIntOrNull("key")
                        if (pinnedGameId == gameId) {
                            settings["key"] = null
                        }
                        else {
                            settings["key"] = gameId
                        }
                    }
                ) {
                    Icon(Icons.Default.Lock, contentDescription = "Pin")
                }
            }
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text(game.title, fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                AsyncImage(
                    model = game.thumbnail,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().padding(5.dp)
                )
                Column(Modifier.padding(25.dp)) {
                    Text("Gènere: ${game.genre}", fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(5.dp))
                    Text(game.desc)
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