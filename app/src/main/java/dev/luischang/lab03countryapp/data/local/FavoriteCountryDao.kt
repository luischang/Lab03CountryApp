package dev.luischang.lab03countryapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteCountryDao {
    //Insert, delete and select all favorite countries
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteCountry: FavoriteCountryEntity)

    @Delete
    suspend fun delete(favoriteCountry: FavoriteCountryEntity)

    @Query("SELECT * FROM favorite_countries")
    suspend fun getAll(): List<FavoriteCountryEntity>


}