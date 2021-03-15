package com.ajax.ajaxtestassignment.db

import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    suspend fun getAll() : List<UserEntity>

    @Query("SELECT * FROM users WHERE uuid == :uuid LIMIT 1")
    suspend fun findUser(uuid: String): UserEntity

    @Insert
    suspend fun insertAll(vararg users: UserEntity)

    @Delete
    suspend fun delete(user: UserEntity)

    @Update
    suspend fun update(user: UserEntity)
}