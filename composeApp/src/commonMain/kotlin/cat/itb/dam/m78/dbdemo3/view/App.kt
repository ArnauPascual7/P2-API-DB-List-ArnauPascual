package cat.itb.dam.m78.dbdemo3.view
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import cat.itb.dam.m78.dbdemo3.model.DatabaseViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(viewModel: DatabaseViewModel=DatabaseViewModel()) {
    List2Screen()
    //MainScreen()

    /*MaterialTheme {
        // API: https://www.freetogame.com/api-doc
        //Llista amb tots els registres, obtinguda del ViewModel
        val all = viewModel.allTexts.value
        var inputText by remember { mutableStateOf("") }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            // Text field and button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //El textFiel està enllaçat al camp inputText.  No està al ViewModel per què és funcionament de la pantalla
                    TextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        label = { Text("Enter text") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                            .background(MaterialTheme.colors.background, shape = RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        // Quanes fa click, el botó cirda al ViewModel per fer un insert a la base de dades
                        onClick = { viewModel.insertText(inputText) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Add", color = MaterialTheme.colors.onPrimary)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // List of items
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(all) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(MaterialTheme.colors.surface, shape = RoundedCornerShape(8.dp))
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(item.text, style = MaterialTheme.typography.body1)
                        IconButton(onClick = {viewModel.deleteText(item.id) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
    }*/
}

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

val navigationItems = listOf(
    NavigationItem(
        title = "ListScreen",
        icon = Icons.Default.Home,
        route = Screen.List.rout
    ),
    NavigationItem(
        title = "ListScreenFav",
        icon = Icons.Default.Star,
        route = Screen.Fav.rout
    )
)

sealed class Screen(val rout: String) {
    object List: Screen("list_screen")
    object Fav: Screen("fav_screen")
}

//HomeScreen.kt
@Composable
fun ListScreen(){
    Box (modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Home Screen",
        )
    }
}

//ProfileScreen.kt
@Composable
fun FavScreen(){
    Box (modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Profile Screen",
        )
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            val selectedNavigationIndex = rememberSaveable {
                mutableIntStateOf(0)
            }
            /*NavigationBar(containerColor = Color.White) {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedNavigationIndex.intValue == index,
                        onClick = {
                            selectedNavigationIndex.intValue = index
                            navController.navigate(item.route)
                        },
                        icon = {
                            Icon(imageVector = item.icon, contentDescription = item.title)
                        },
                        label = {
                            Text(
                                item.title,
                                color = if (index == selectedNavigationIndex.intValue)
                                    Color.Black
                                else Color.Gray
                            )
                        },
                        /*colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.surface,
                            indicatorColor = MaterialTheme.colorScheme.primary
                        )*/
                    )
                }
            }*/
        }
    ) { innerPadding ->
        val graph =
            navController.createGraph(startDestination = Screen.List.rout) {
                composable(route = Screen.List.rout) {
                    ListScreen()
                }
                composable(route = Screen.Fav.rout) {
                    FavScreen()
                }
            }
        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(innerPadding)
        )
    }
}