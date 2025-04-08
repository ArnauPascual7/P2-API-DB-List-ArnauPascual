package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.russhwolf.settings.Settings

@OptIn(InternalComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navDetailsScreen: (Int) -> Unit) {
    val viewmodel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it) { GamesViewModel() } }
    val games = viewmodel?.games
    val settings = Settings()
    val pinnedGameId = settings.getIntOrNull("key")
    if (games != null) {
        var filteredGames by remember { mutableStateOf(games) }
        var expanded by rememberSaveable { mutableStateOf(false) }
        val textFieldState = rememberTextFieldState()
        Column(
            Modifier.fillMaxSize().semantics { isTraversalGroup = true },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(20.dp))
            SearchBar(
                modifier = Modifier.semantics { traversalIndex = 0f }.align(Alignment.CenterHorizontally),
                inputField = {
                    SearchBarDefaults.InputField(
                        query = textFieldState.text.toString(),
                        onQueryChange = { textFieldState.edit { replace(0, length, it) } },
                        onSearch = {
                            query -> filteredGames = games.filter { it.title.contains(query, ignoreCase = true) }
                            expanded = false
                        },
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        placeholder = { Text("Cercar") }
                    )
                },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                shape = RectangleShape
            ) {
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    filteredGames.forEach { game ->
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
            Spacer(Modifier.height(20.dp))
            if (!expanded) {
                if (pinnedGameId != null) {
                    val pinnedGame = games.find { game -> game.id == pinnedGameId }
                    if (pinnedGame != null) {
                        Column(
                            modifier = Modifier.fillMaxWidth().background(Color.Red).padding(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                modifier = Modifier.width(400.dp),
                                shape = RectangleShape,
                                onClick = { navDetailsScreen(pinnedGame.id) }
                            ) {
                                Text(pinnedGame.title)
                            }
                        }
                    }
                }
                LazyColumn {
                    filteredGames.forEach { game ->
                        item {
                            Row {
                                Button(
                                    modifier = Modifier.width(400.dp),
                                    shape = RectangleShape,
                                    onClick = { navDetailsScreen(game.id) }
                                ) {
                                    Text(game.title)
                                }
                            }
                        }
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