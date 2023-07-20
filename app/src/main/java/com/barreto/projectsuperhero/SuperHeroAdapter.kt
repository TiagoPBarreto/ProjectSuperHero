package com.barreto.projectsuperhero

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperHeroAdapter(var superHeroeList:List<SuperHeroesItemResponse> = emptyList(),
                       private val onItemSelected:(String)->Unit) :
                        RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updateList(list:List<SuperHeroesItemResponse>){
        superHeroeList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        return SuperHeroViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_superhero,parent,false)
        )
    }

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(superHeroeList[position],onItemSelected)
    }

    override fun getItemCount() = superHeroeList.size
}
