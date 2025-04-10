package cat.itb.dam.m78.dbdemo3.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Student (
    val id: Int,
    val name: String,
    val surnames: String,
    val email: String,
    @SerialName("photo_link")
    val photo: String
)