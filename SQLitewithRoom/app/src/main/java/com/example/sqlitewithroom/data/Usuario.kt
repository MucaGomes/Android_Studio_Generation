package com.example.sqlitewithroom.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
class Usuario(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var nome: String,
    var sobrenome: String,
    var idade: Int,
) {
}