package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)

val navigationItems = listOf(
    NavigationItem(
        title = "Estudiants",
        icon = Icons.Default.Home,
        screen = Screen.StList
    ),
    NavigationItem(
        title = "Faltes",
        icon = Icons.Default.Check,
        screen = Screen.StFaltes
    ),
    NavigationItem(
        title = "Número Faltes",
        icon = Icons.Default.Add,
        screen = Screen.StListFaltesNum
    )
)

@OptIn(InternalComposeApi::class)
@Composable
fun StudentsBottomBar() {
    val navViewModel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it) { ExamNavViewModel() } }
    val selectedNavigationIndex = rememberSaveable { mutableIntStateOf(0) }
    NavigationBar {
        if (navViewModel != null) {
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
}