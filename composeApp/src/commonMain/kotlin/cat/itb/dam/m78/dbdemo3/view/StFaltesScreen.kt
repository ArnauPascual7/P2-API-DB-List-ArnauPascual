package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.dam.m78.dbdemo3.model.ExamenDatabaseViewModel
import cat.itb.dam.m78.dbdemo3.model.StudentsViewModel
import java.time.LocalDateTime

@OptIn(InternalComposeApi::class)
@Composable
fun StudentsFaltesScreen() {
    val apiViewModel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it) { StudentsViewModel() } }
    val dbViewModel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it) { ExamenDatabaseViewModel() } }
    val apiStudents = apiViewModel?.students
    val dbStudents = dbViewModel?.allFaltes?.value
    if (dbStudents != null) {
        dbStudents.sortedBy { LocalDateTime.parse(it.dataFalta) }
        if (apiStudents != null) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Faltes", Modifier.padding(10.dp), fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                HorizontalDivider(thickness = 2.dp)
                LazyColumn {
                    dbStudents.forEach { dbStudent ->
                        val apiStudent = apiStudents.find { st -> st.id == dbStudent.studentId.toInt() }
                        val stName = if (apiStudent != null) "${apiStudent.name} ${apiStudent.surnames}" else dbStudent.studentId.toString()
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    stName,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(dbStudent.dataFalta)
                            }
                        }
                    }
                }
            }
        }
        else {
            Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                CircularProgressIndicator()
            }
        }
    }
    else {
        Row(Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Text("ERROR")
        }
    }
}