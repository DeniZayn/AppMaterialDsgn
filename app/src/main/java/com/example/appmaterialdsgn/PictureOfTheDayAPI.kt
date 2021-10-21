package com.example.appmaterialdsgn

import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
        @GET("planetary/apod")

        fun getPictureOfTheDay(@Query("api_key") apiKey: String):
                retrofit2.Call<PODServerResponseData>

}