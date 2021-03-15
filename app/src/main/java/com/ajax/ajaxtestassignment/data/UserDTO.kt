package com.ajax.ajaxtestassignment.data

class UserDTO(
    val gender : String?,
    val name: Name?,
    val email: String?,
    val login: Login,
    val picture: Picture?,
)

class Name(
    val title: String,// "Mrs",
    val first: String,// "Alicia",
    val last: String,// "Martin"
)

class Picture(
    val large: String?,
    val medium: String?,
    val thumbnail: String?,
)

class Login(
    val uuid: String, //"0884a067-f209-4856-a26f-65dd4d434bcd"
)