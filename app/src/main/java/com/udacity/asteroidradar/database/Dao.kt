package com.udacity.asteroidradar.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.udacity.asteroidradar.pojo.Asteroid
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Query("SELECT * FROM AsteroidEntity WHERE closeApproachDate >= :startDate AND closeApproachDate <= :endDate ORDER BY closeApproachDate ASC")
    fun getAsteroidsByCloseApproachDate(startDate: String, endDate: String): Flow<List<Asteroid>>

    @Query("SELECT * FROM AsteroidEntity ORDER BY closeApproachDate ASC")
    fun getAllAsteroids(): Flow<List<Asteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: AsteroidEntity)

    @Query("DELETE FROM AsteroidEntity WHERE closeApproachDate < :today")
    fun deletePreviousDayAsteroids(today: String): Int
}