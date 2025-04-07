package cat.itb.dam.m78.dbdemo3.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.dam.m78.dbdemo3.db.MyTable

import kotlinx.coroutines.launch

class DatabaseViewModel : ViewModel() {
    val allGames = mutableStateOf<List<MyTable>>(emptyList())

    init {
        _fetchAllGames()
    }

    private fun _fetchAllGames() {
        viewModelScope.launch {
            val myTableQueries = database.myTableQueries
            allGames.value = myTableQueries.selectAll().executeAsList()
        }
    }

    fun updateAllGames() { _fetchAllGames() }

    fun insertGame(game: Game) {
        viewModelScope.launch {
            val myTableQueries = database.myTableQueries
            myTableQueries.insert(game.id.toLong(), game.title, game.thumbnail, game.desc, game.genre)
            _fetchAllGames()
        }
    }

    fun deleteGame(id: Long) {
        viewModelScope.launch {
            val myTableQueries = database.myTableQueries
            myTableQueries.delete(id)
            _fetchAllGames()
        }
    }
}