package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cat.itb.dam.m78.dbdemo3.model.Game

@Composable
fun detailsScreen(navListScreen: () -> Unit, game: Game) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(game.title)
        Button(onClick = { navListScreen() }) {
            Text("Return")
        }
    }
}