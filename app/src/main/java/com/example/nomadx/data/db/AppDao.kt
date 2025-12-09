package com.example.nomadx.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    // Auth
    @Insert
    suspend fun register(user: UserEntity)

    @Query("SELECT * FROM users WHERE email = :email AND pass = :pass LIMIT 1")
    suspend fun login(email: String, pass: String): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun checkUser(email: String): UserEntity?

    // Posts
    @Insert
    suspend fun addPost(post: PostEntity)

    @Query("SELECT * FROM posts ORDER BY id DESC")
    fun getAllPosts(): Flow<List<PostEntity>>
}