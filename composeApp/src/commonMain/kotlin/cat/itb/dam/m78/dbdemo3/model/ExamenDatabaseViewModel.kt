package cat.itb.dam.m78.dbdemo3.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.dam.m78.dbdemo3.db.MyTable

import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ExamenDatabaseViewModel : ViewModel() {
    val allFaltes = mutableStateOf<List<MyTable>>(emptyList())

    init {
        _fetchAllFaltes()
    }

    private fun _fetchAllFaltes() {
        viewModelScope.launch {
            val myTableQueries = studentsDatabase.myTableQueries
            allFaltes.value = myTableQueries.selectAll().executeAsList()
        }
    }

    fun insertFalta(stId: Int) {
        val dataFalta = LocalDateTime.now().toString()
        viewModelScope.launch {
            val myTableQueries = studentsDatabase.myTableQueries
            myTableQueries.insert(stId.toLong(), dataFalta)
            _fetchAllFaltes()
        }
    }

    fun deleteFalta(id: Long) {
        viewModelScope.launch {
            val myTableQueries = studentsDatabase.myTableQueries
            myTableQueries.delete(id)
            _fetchAllFaltes()
        }
    }
}