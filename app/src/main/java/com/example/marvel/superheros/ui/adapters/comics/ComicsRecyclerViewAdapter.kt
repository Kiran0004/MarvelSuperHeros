package com.example.marvel.superheros.ui.adapters.comics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.superheros.R
import com.example.marvel.superheros.model.data.MarvelHeroesModel
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.row_hero.*
import kotlinx.android.synthetic.main.row_hero.view.*

/**
 * @author kiran
 */


class ComicsRecyclerViewAdapter(private val onHeroClicked: (MarvelHeroesModel) -> Unit) : RecyclerView.Adapter<ComicsRecyclerViewAdapter.ItemViewHolder>() {

    private var heroList: MutableList<MarvelHeroesModel> = mutableListOf()
    lateinit var comicListData:ArrayList<MarvelHeroesModel>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_hero, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val hero = heroList[position]
        holder.heroName.text = hero.name
        holder.heroDescription.text = handleDescription(hero.description, holder.itemView.context)
        Picasso.get().load(hero.thumbnail).into(holder.heroImageView.heroImageView)
        holder.itemView.setOnClickListener { onHeroClicked(hero) }
    }

    private fun handleDescription(description: String, context: Context): String {
        return when {
            description.isEmpty() -> context.resources.getString(R.string.dummy_description)
            else -> description
        }
    }

    fun setHeroesList(heroList: MutableList<MarvelHeroesModel>) {
        this.heroList.clear()
        this.heroList.addAll(heroList)
        comicListData  = heroList as ArrayList<MarvelHeroesModel>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return heroList.size
    }

    class ItemViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if(charString.isEmpty()) {
                    heroList = comicListData
                    notifyDataSetChanged()
                } else {
                    val filteredList = ArrayList<MarvelHeroesModel>()
                    for (row in heroList) {
                        if (row.name.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    heroList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = heroList
                return filterResults
            }
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                if(filterResults.values!=null){
                    heroList = filterResults.values as ArrayList<MarvelHeroesModel>
                    notifyDataSetChanged()
                }
            }
        }
    }
}




