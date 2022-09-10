package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.*
import com.udacity.asteroidradar.utils.Constants

@Database(entities = [AsteroidEntity::class], version = 2   , exportSchema = false)
abstract class AsteroidDatabase : RoomDatabase() {
    abstract val asteroidDao: Dao

    companion object {
        @Volatile
        private lateinit var INSTANCE: AsteroidDatabase

        fun getDatabase(context: Context): AsteroidDatabase {
            synchronized(AsteroidDatabase::class.java) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidDatabase::class.java,
                        Constants.DATABASE_NAME
                    ).fallbackToDestructiveMigration().build()
                }
            }
            // return instance of roomDB
            return INSTANCE
        }
    }
}