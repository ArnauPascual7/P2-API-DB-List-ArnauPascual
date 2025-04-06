package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)

val navigationItems = listOf(
    NavigationItem(
        title = "Jocs",
        icon = Icons.Default.Home,
        screen = Screen.GameList
    ),
    NavigationItem(
        title = "Preferits",
        icon = Icons.Default.Star,
        screen = Screen.FavList
    )
)

@Composable
fun bottomNavBar(navViewModel: NavViewModel) {
    val selectedNavigationIndex = rememberSaveable { mutableIntStateOf(0) }
    NavigationBar(
        containerColor = Color.White
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    navViewModel.navTo(item.screen)
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