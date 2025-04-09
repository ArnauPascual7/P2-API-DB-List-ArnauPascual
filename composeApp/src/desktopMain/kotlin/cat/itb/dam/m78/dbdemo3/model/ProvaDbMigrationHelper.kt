package cat.itb.dam.m78.dbdemo3.model

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import cat.itb.dam.m78.dbdemo3.db.Database
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString

// Aquest arxiu pot ser únic, i només utilitzar un si només volem una base de dades

// Canviar el nom aquí i en el DriverFactory
actual fun createProvaDriver(): SqlDriver {

    val currentDir = System.getProperty("user.dir")
    println("Current directory: $currentDir")

    val userHome = System.getProperty("user.home")
    println("userHome: $userHome")

    //val file = Path(userHome, "myDatabase.db")

    // S'ha de canviar el nom de la base de dades o utilitzar el mateix,
    // en la base de dades es creen tantes taules com arxius de taules hi hagui
    val file = Path(currentDir, "myProvaDb.db")
    val driver = JdbcSqliteDriver("jdbc:sqlite:${file.absolutePathString()}")

    println(file.absolutePathString())

    // Descomentar només la primera vegada que s'executa
    //Database.Schema.create(driver)
    return driver
}

