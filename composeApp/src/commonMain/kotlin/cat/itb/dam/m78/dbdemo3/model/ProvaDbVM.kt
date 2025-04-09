package cat.itb.dam.m78.dbdemo3.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.itb.dam.m78.dbdemo3.db.Prova
import kotlinx.coroutines.launch

// Nom del viewmodel, és el que s'ha d'utilitzar en el codi
class ProvaDbVM : ViewModel() {

    // Obté tots els registres de la base de dades
    val allTexts = mutableStateOf<List<Prova>>(emptyList())

    // Inicialitza amb la funció per a obtenir els registres
    init {
        _fetchAllTexts()
    }

    // Canviar nom a la funció
    private fun _fetchAllTexts() {
        viewModelScope.launch {
            // IMPORTANT: myProvaQueries es genera amb el run, primer no existeix
            val myTableQueries = provaDatabase.myProvaQueries
            allTexts.value = myTableQueries.selectAll().executeAsList()
        }
    }

    fun insertText(text: String) {
        viewModelScope.launch {
            // IMPORTANT: myProvaQueries es genera amb el run, primer no existeix
            val myTableQueries = provaDatabase.myProvaQueries
            myTableQueries.insert(text)
            _fetchAllTexts()
        }
    }

    fun deleteText(id: Long) {
        viewModelScope.launch {
            // IMPORTANT: myProvaQueries es genera amb el run, primer no existeix
            val myTableQueries = provaDatabase.myProvaQueries
            myTableQueries.delete(id)
            _fetchAllTexts()
        }
    }
}