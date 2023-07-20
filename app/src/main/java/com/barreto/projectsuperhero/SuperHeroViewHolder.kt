package com.barreto.projectsuperhero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.barreto.projectsuperhero.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view:View): RecyclerView.ViewHolder(view) {
    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHeroesItemResponse: SuperHeroesItemResponse, onItemSelected:(String)->Unit){
        binding.tvSuperHeroName.text = superHeroesItemResponse.name
        Picasso.get().load(superHeroesItemResponse.superHeroImage.url).into(binding.ivSuperhero)
        binding.root.setOnClickListener { onItemSelected(superHeroesItemResponse.superHeroeId) }
    }
}