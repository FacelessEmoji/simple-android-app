package rut.miit.simpleapp.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Character(
    val name: String?,
    val culture: String?,
    val born: String?,
    val titles: List<String> = emptyList(),
    val aliases: List<String> = emptyList(),
    @SerialName("playedBy") val playedBy: List<String> = emptyList()
)
