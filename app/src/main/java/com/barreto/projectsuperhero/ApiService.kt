package com.barreto.projectsuperhero

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/1604243770060346/search/{name}")
    suspend fun getSuperheroes(@Path("name")superheroName:String):Response<SuperHeroDataResponse>

    @GET("/api/1604243770060346/{id}")
    suspend fun getSuperHeroDetail(@Path("id")superHeroId:String):Response<SuperHeroDetailResponse>

}