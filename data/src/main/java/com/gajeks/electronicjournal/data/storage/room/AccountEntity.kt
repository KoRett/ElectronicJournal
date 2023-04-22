package com.gajeks.electronicjournal.data.storage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.gajeks.electronicjournal.data.storage.room.AccountEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    indices = [
        Index("email")
    ]
)
data class AccountEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val email: String,
    val password: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val patronymic: String?,
    val type: String
) {

    companion object {
        const val TABLE_NAME = "users_table"
    }

}