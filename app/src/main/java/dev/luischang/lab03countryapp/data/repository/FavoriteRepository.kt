package dev.luischang.lab03countryapp.data.repository

import dev.luischang.lab03countryapp.data.local.FavoriteCountryDao
import dev.luischang.lab03countryapp.data.local.FavoriteCountryEntity
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(private val dao: FavoriteCountryDao) {

    suspend fun insert(favoriteCountry: FavoriteCountryEntity) = dao.insert(favoriteCountry)
    suspend fun delete(favoriteCountry: FavoriteCountryEntity) = dao.delete(favoriteCountry)
    fun getAll(): Flow<List<FavoriteCountryEntity>> = dao.getAll()

}