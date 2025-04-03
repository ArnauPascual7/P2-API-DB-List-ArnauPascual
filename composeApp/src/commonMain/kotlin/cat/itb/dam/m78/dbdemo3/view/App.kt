package cat.itb.dam.m78.dbdemo3.view
import androidx.compose.runtime.*
import cat.itb.dam.m78.dbdemo3.model.DatabaseViewModel

@Composable
fun App(viewModel: DatabaseViewModel=DatabaseViewModel()) {
    ListScreen()
    //MainScreen()

    /*MaterialTheme {
        // API: https://www.freetogame.com/api-doc
        //Llista amb tots els registres, obtinguda del ViewModel
        val all = viewModel.allTexts.value
        var inputText by remember { mutableStateOf("") }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            // Text field and button
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    //El textFiel està enllaçat al camp inputText.  No està al ViewModel per què és funcionament de la pantalla
                    TextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        label = { Text("Enter text") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                            .background(MaterialTheme.colors.background, shape = RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        // Quanes fa click, el botó cirda al ViewModel per fer un insert a la base de dades
                        onClick = { viewModel.insertText(inputText) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("Add", color = MaterialTheme.colors.onPrimary)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // List of items
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(all) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(MaterialTheme.colors.surface, shape = RoundedCornerShape(8.dp))
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(item.text, style = MaterialTheme.typography.body1)
                        IconButton(onClick = {viewModel.deleteText(item.id) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }
            }
        }
    }*/
}