package cat.itb.dam.m78.dbdemo3.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

// API: https://fp.mateuyabar.com/DAM-M78/composeP2/exam/students.json

object StudentsApi {
    private const val URL = "https://fp.mateuyabar.com/DAM-M78/composeP2/exam/students.json"
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun getList() = client.get(URL).body<List<Student>>()
}

class StudentsViewModel : ViewModel() {
    var students by mutableStateOf<List<Student>?>(null)
    init {
        viewModelScope.launch(Dispatchers.Default) {
            students = StudentsApi.getList()
        }
    }
}