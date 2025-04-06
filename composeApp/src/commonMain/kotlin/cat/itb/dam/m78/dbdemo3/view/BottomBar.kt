package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import cat.itb.dam.m78.dbdemo3.model.Game

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

val navigationItems = listOf(
    NavigationItem(
        title = "GameList",
        icon = Icons.Default.Home,
        route = Screen.GameList.rout
    ),
    NavigationItem(
        title = "FavList",
        icon = Icons.Default.Star,
        route = Screen.FavList.rout
    )
)

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