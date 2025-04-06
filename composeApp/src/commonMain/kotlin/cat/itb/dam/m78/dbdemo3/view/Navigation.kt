package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.dam.m78.dbdemo3.model.Game

sealed class Screen(val rout: String) {
    data object GameList: Screen("GameList_Screen")
    data object FavList: Screen("FavList_Screen")
    data class Details(val game: Game): Screen("Details_Screen")
}

private class NavViewModel : ViewModel() {
    val currentScreen = mutableStateOf<Screen>(Screen.GameList)
    fun navTo(screen: Screen) { currentScreen.value = screen }
}

@OptIn(InternalComposeApi::class)
@Composable
fun navigation() {
    val viewModel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it) { NavViewModel() } }
    if (viewModel != null){
        when (val currentScreen = viewModel.currentScreen.value) {
            Screen.GameList -> ListScreen(
                navFavScreen = { viewModel.navTo(Screen.FavList) },
                navDetailsScreen = { viewModel.navTo(Screen.Details(it)) }
            )
            is Screen.FavList -> FavScreen(
                navListScreen = { viewModel.navTo(Screen.GameList) },
            )
            is Screen.Details -> DetailsScreen(
                navListScreen = { viewModel.navTo(Screen.GameList) },
                game = currentScreen.game
            )
        }
    }
}