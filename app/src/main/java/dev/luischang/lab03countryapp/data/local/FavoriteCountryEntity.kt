package dev.luischang.lab03countryapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_countries")
data class FavoriteCountryEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val countryId: Int,
    val name: String,
    val ranking: Int,
    val imageUrl: String
)