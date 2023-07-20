package com.barreto.projectsuperhero

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.core.view.isVisible
import com.barreto.projectsuperhero.databinding.ActivityDetailSuperHeroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt

class DetailSuperHeroActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id: String = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHeroInformation(id)
    }

    private fun getSuperHeroInformation(id:String) {
        CoroutineScope(Dispatchers.IO).launch {
           val superHeroDetail =  getRetrofit().create(ApiService::class.java).getSuperHeroDetail(id)

            if (superHeroDetail.body() != null){
                runOnUiThread { createUi(superHeroDetail.body()!!) }

            }
        }
    }

    private fun createUi(superHero: SuperHeroDetailResponse) {
        Picasso.get().load(superHero.image.url).into(binding.ivSuperHero)
        binding.tvSuperHeroName.text = superHero.name
        prepareStats(superHero.powerstats)
        binding.tvSuperHeroRealName.text = superHero.biography.fullName
        binding.tvPublisher.text = superHero.biography.publisher
    }

    private fun prepareStats(powerstats: PowerStatsResponse) {
        updateHeight(binding.viewCombat,powerstats.combat)
        updateHeight(binding.viewDurability,powerstats.durability)
        updateHeight(binding.viewIntelligence,powerstats.intelligence)
        updateHeight(binding.viewPower,powerstats.power)
        updateHeight(binding.viewSpeed,powerstats.speed)
        updateHeight(binding.viewStrength,powerstats.strength)

    }

    private fun updateHeight(view:View, stat:String){
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,px,resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}