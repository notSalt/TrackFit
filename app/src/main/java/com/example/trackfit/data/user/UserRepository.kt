package com.example.trackfit.data.user

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getAllUsersStream(): Flow<List<User>>

    fun getUserStream(id: Int): Flow<User?>

    fun getUserByEmailStream(email: String): Flow<User?>

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun updateUser(user: User)
}
