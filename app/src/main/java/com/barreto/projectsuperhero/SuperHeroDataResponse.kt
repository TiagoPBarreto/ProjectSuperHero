package com.barreto.projectsuperhero

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse (
    @SerializedName("response") val response:String,
    @SerializedName("results")val superheroes:List<SuperHeroesItemResponse>
    )


data class SuperHeroesItemResponse(
    @SerializedName("id") val superHeroeId:String,
    @SerializedName("name") val name:String,
    @SerializedName("image") val superHeroImage:SuperHeroImageResponse

    )
data class SuperHeroImageResponse(
    @SerializedName("url") val url:String
)