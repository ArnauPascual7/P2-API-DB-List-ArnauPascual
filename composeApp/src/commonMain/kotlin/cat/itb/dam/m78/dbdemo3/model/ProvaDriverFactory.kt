package cat.itb.dam.m78.dbdemo3.model

import app.cash.sqldelight.db.SqlDriver
import cat.itb.dam.m78.dbdemo3.db.Database

// Canviar nom a les funcions
expect fun createProvaDriver(): SqlDriver

fun createProvaDatabase(): Database {
    // Nom general o createProvaDriver
    val driver = createProvaDriver()
    return Database(driver)
}

// Canviar el nom aquí i assignar-lo al viewmodel
val provaDatabase by lazy { createProvaDatabase() }