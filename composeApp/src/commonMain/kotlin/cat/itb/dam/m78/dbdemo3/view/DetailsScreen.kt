package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cat.itb.dam.m78.dbdemo3.model.DatabaseViewModel
import cat.itb.dam.m78.dbdemo3.model.Game
import coil3.compose.AsyncImage

@Composable
fun detailsScreen(navListScreen: () -> Unit, game: Game) {
    val viewModel = DatabaseViewModel()
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
                onClick = { viewModel.insertGame(game) },
            ) {
                Icon(Icons.Default.Star, contentDescription = "Preferit")
            }
        }
    ) {
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