package com.ajax.ajaxtestassignment.repository

import com.ajax.ajaxtestassignment.api.UserApi
import com.ajax.ajaxtestassignment.data.User
import com.ajax.ajaxtestassignment.data.UserDTO
import com.ajax.ajaxtestassignment.db.UserEntity
import com.ajax.ajaxtestassignment.db.UserDao
import java.util.*
import javax.inject.Inject

class UsersRepository @Inject constructor(val api: UserApi, val dao: UserDao){

    suspend fun getUsers() : List<User> {
        val usersFromDB = dao.getAll()
        if (usersFromDB.isNotEmpty()) {
            return usersFromDB.map { it.convert() }
        } else {
            val users = api.loadUsers().map { it.convert() }
            dao.insertAll(*(users.map { it.convertToEntity() }.toTypedArray()))
            return users
        }
    }

    suspend fun getUser(uuid: String) : User?{
        return dao.findUser(uuid).convert()
    }

    suspend fun updateUser(user: User) {
        dao.update(user.convertToEntity())
    }

    private fun UserDTO.convert() : User = User(login.uuid,
        name?.first,
        name?.last,
        picture?.large,
        email)

    private fun UserEntity.convert() : User = User(
        uuid,
        firstName,
        lastName,
        photo,
        email
    )

    private fun User.convertToEntity() : UserEntity = UserEntity(uuid, firstName, lastName, photo, email)


}