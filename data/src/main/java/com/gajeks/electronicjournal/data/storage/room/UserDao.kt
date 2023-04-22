package com.gajeks.electronicjournal.data.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT password FROM ${AccountEntity.TABLE_NAME} WHERE email LIKE :email")
    suspend fun getPassword(email: String): String

    @Query("SELECT password FROM ${AccountEntity.TABLE_NAME} WHERE id LIKE :id")
    suspend fun getPassword(id: Int): String

    @Query("SELECT type FROM ${AccountEntity.TABLE_NAME} WHERE email LIKE :email")
    suspend fun getType(email: String): String

    @Insert(entity = AccountEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(accountEntity: AccountEntity)
}