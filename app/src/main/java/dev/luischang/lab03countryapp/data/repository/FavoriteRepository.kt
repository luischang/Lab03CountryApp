package dev.luischang.lab03countryapp.data.repository

import dev.luischang.lab03countryapp.data.local.FavoriteCountryDao
import dev.luischang.lab03countryapp.data.local.FavoriteCountryEntity

class FavoriteRepository(private val dao: FavoriteCountryDao) {

    suspend fun insert(favoriteCountry: FavoriteCountryEntity) = dao.insert(favoriteCountry)
    suspend fun delete(favoriteCountry: FavoriteCountryEntity) = dao.delete(favoriteCountry)
    suspend fun getAll(): List<FavoriteCountryEntity> = dao.getAll()

}