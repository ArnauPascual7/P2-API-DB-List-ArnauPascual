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

sealed interface Screen {
    data object StList: Screen
    data object StFaltes: Screen
    data object StListFaltesNum: Screen
}

class ExamNavViewModel : ViewModel() {
    val currentScreen = mutableStateOf<Screen>(Screen.StList)
    fun navTo(screen: Screen) { currentScreen.value = screen }
}

@OptIn(InternalComposeApi::class)
@Composable
fun StudentsNavigation() {
    val viewModel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it) { ExamNavViewModel() } }
    if (viewModel != null){
        val currentScreen = viewModel.currentScreen.value
        Scaffold(
            Modifier.fillMaxSize(),
            bottomBar = { StudentsBottomBar() }
        ) {
            when (currentScreen) {
                Screen.StList -> StudentsListScreen()
                is Screen.StFaltes -> StudentsFaltesScreen()
                is Screen.StListFaltesNum -> StudentsListWithFaltes()
            }
        }
    }
}