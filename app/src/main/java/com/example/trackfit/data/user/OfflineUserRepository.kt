package com.example.trackfit.data.user

import kotlinx.coroutines.flow.Flow

class OfflineUserRepository(private val userDao: UserDao) : UserRepository {
    override fun getAllUsersStream(): Flow<List<User>> = userDao.getAllUsers()

    override fun getUserStream(id: Int): Flow<User?> = userDao.getUser(id)

    override fun getUserByEmailStream(email: String): Flow<User?> = userDao.getUserByEmail(email)

    override suspend fun insertUser(user: User) = userDao.insert(user)

    override suspend fun deleteUser(user: User) = userDao.delete(user)

    override suspend fun updateUser(user: User) = userDao.update(user)
}
