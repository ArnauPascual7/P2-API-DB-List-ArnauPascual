package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cat.itb.dam.m78.dbdemo3.model.DatabaseViewModel

@Composable
fun favScreen(){
    val dbViewModel = DatabaseViewModel()
    val games = dbViewModel.allGames.value
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(20.dp))
        Text("Preferits", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(Modifier.height(20.dp))
        LazyColumn {
            items(games) { game ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp).border(
                        width = 2.dp,
                        brush = Brush.horizontalGradient(listOf(Color.Red, Color.Blue)),
                        shape = RectangleShape
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        game.title,
                        modifier = Modifier.padding(start = 20.dp),
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = { dbViewModel.deleteGame(game.id) }) {
                        Icon(Icons.Filled.Close, contentDescription = "Eliminar")
                    }
                }
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}