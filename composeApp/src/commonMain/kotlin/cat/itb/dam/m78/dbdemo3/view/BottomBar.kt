package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.createGraph

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

val navigationItems = listOf(
    NavigationItem(
        title = "Home",
        icon = Icons.Default.Home,
        route = Screen.Home.rout
    ),
    NavigationItem(
        title = "Profile",
        icon = Icons.Default.Person,
        route = Screen.Profile.rout
    ),
    NavigationItem(
        title = "Cart",
        icon = Icons.Default.ShoppingCart,
        route = Screen.Cart.rout
    ),
    NavigationItem(
        title = "Setting",
        icon = Icons.Default.Settings,
        route = Screen.Setting.rout
    )
)

sealed class Screen(val rout: String) {
    object Home: Screen("home_screen")
    object Profile: Screen("profile_screen")
    object Cart: Screen("cart_screen")
    object Setting: Screen("setting_screen")
}

//HomeScreen.kt
@Composable
fun HomeScreen(){
    Box (modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Home Screen",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

//ProfileScreen.kt
@Composable
fun ProfileScreen(){
    Box (modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Profile Screen",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

//CartScreen.kt
@Composable
fun CartScreen(){
    Box (modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Cart Screen",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

//SettingScreen
@Composable
fun SettingScreen(){
    Box (modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Setting Screen",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val selectedNavigationIndex = rememberSaveable { mutableIntStateOf(0) }
    NavigationBar(
        containerColor = Color.White
    ) {
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
                        text = item.title,
                        color = if (index == selectedNavigationIndex.intValue)
                            Color.Black
                        else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )

            )
        }
    }
}

@Composable
fun MainScreen() {

    //val navController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        var currentScreen by remember { mutableStateOf("main") }

        when (currentScreen) {
            "main" -> MainScreen(onNavigate = { currentScreen = "detail" })
            "detail" -> DetailScreen(onBack = { currentScreen = "main" })
        }

        /*val graph =
            navController.createGraph(startDestination = Screen.Home.rout) {
                composable(route = Screen.Cart.rout) {
                    CartScreen()
                }
                composable(route = Screen.Setting.rout) {
                    SettingScreen()
                }
                composable(route = Screen.Home.rout) {
                    HomeScreen()
                }
                composable(route = Screen.Profile.rout) {
                    ProfileScreen()
                }
            }
        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(innerPadding)
        )*/

    }
}