package cat.itb.dam.m78.dbdemo3.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id: Int,
    val title: String,
    val thumbnail: String,
    @SerialName("short_description")
    val desc: String,
    val genre: String
)