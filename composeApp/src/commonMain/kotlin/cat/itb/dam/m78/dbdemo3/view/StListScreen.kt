package cat.itb.dam.m78.dbdemo3.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.findComposeDefaultViewModelStoreOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.dam.m78.dbdemo3.model.ExamenDatabaseViewModel
import cat.itb.dam.m78.dbdemo3.model.StudentsViewModel
import coil3.compose.AsyncImage

@OptIn(InternalComposeApi::class)
@Composable
fun StudentsListScreen() {
    val apiViewModel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it) { StudentsViewModel() } }
    val dbViewModel = findComposeDefaultViewModelStoreOwner()?.let { viewModel(viewModelStoreOwner = it) { ExamenDatabaseViewModel() } }
    val apiStudents = apiViewModel?.students
    val dbStudents = dbViewModel?.allFaltes?.value
    if (apiStudents != null && dbStudents != null) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Estudiants", Modifier.padding(10.dp), fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            HorizontalDivider(thickness = 2.dp)
            LazyColumn(
                modifier = Modifier.padding(bottom = 100.dp)
            ) {
                apiStudents.forEach { student ->
                    item {
                        Column(
                            Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("${student.name} ${student.surnames}", Modifier.padding(5.dp), fontWeight = FontWeight.Bold)
                            Text(student.email)
                            Spacer(Modifier.height(10.dp))
                            AsyncImage(
                                model = student.photo,
                                contentDescription = null,
                                modifier = Modifier.size(50.dp)
                            )
                            Button(
                                modifier = Modifier.padding(10.dp),
                                onClick = { dbViewModel.insertFalta(student.id) },
                                shape = RectangleShape
                            ){
                                Text("Afegir Falta")
                            }
                            HorizontalDivider(thickness = 2.dp)
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