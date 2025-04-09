package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import cat.itb.dam.m78.dbdemo3.model.ProvaDbVM

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Game List",
        state = WindowState(width = 422.dp, height = 800.dp)
    ) {
        // Per a inicialitzar la base de dades s'ha d'inicialitzar el viewmodel
        //val viewModel = ProvaDbVM()
        App()
    }
}