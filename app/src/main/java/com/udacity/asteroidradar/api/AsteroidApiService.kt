package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.utils.Constants
import com.udacity.asteroidradar.pojo.PictureOfDay
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface AsteroidApiService {

    //Get list of Asteroids from API
    @GET("neo/rest/v1/feed")
    fun getAsteroidsList(
        @Query("start_date") start: String,
        @Query("end_date") end: String,
        @Query("api_key") Key: String = Constants.API_KEY
    ): Deferred<ResponseBody>

    //Get Image of the day from API
    @GET("planetary/apod")
    fun getPictureOfDay(
        @Query("api_key") Key: String = Constants.API_KEY
    ): Deferred<PictureOfDay>
}