package rut.miit.simpleapp.data

import java.io.Serializable

data class User(val username: String, val email: String, val password: String) : Serializable
