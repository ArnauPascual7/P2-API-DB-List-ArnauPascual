package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.dam.m78.dbdemo3.model.Game

sealed interface Screen {
    data object GameList: Screen
    data object FavList: Screen
    data class Details(val game: Game): Screen
}

class NavViewModel : ViewModel() {
    val currentScreen = mutableStateOf<Screen>(Screen.GameList)
    fun navTo(screen: Screen) { currentScreen.value = screen }
}

@OptIn(InternalComposeApi::class)
@Composable
fun navigation() {
    val viewModel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it) { NavViewModel() } }
    if (viewModel != null){
        val currentScreen = viewModel.currentScreen.value
        Scaffold(
            Modifier.fillMaxSize(),
            bottomBar = { if (currentScreen !is Screen.Details) { bottomNavBar(viewModel) } }
            ) {
            when (currentScreen) {
                Screen.GameList -> ListScreen(
                    navDetailsScreen = { viewModel.navTo(Screen.Details(it)) }
                )
                is Screen.FavList -> FavScreen()
                is Screen.Details -> DetailsScreen(
                    navListScreen = { viewModel.navTo(Screen.GameList) },
                    game = currentScreen.game
                )
            }
        }
    }
}