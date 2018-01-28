package net.parkerstevens.imgurdemo.features.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import net.parkerstevens.imgurdemo.R
import javax.inject.Inject

class ImgurAdapter @Inject
constructor() : RecyclerView.Adapter<ImgurAdapter.ImgurViewHolder>() {

    private var pokemonsList: List<String>
    private var clickListener: ClickListener? = null

    init {
        pokemonsList = emptyList<String>()
    }

    fun setPokemon(pokemons: List<String>) {
        pokemonsList = pokemons
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgurViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_image, parent, false)
        return ImgurViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImgurViewHolder, position: Int) {
        val pokemon = pokemonsList[position]
        holder.bind(pokemon)
    }

    override fun getItemCount(): Int {
        return pokemonsList.size
    }

    interface ClickListener {
        fun onImageClick(pokemon: String)
    }

    inner class ImgurViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var selectedPokemon: String

        @BindView(R.id.pokemon_name)
        @JvmField var pokemonName: TextView? = null

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener {
                clickListener?.onImageClick(selectedPokemon)
            }
        }

        fun bind(pokemon: String) {
            selectedPokemon = pokemon
            pokemonName?.text = String.format("%s%s", pokemon.substring(0, 1).toUpperCase(),
                    pokemon.substring(1))
        }
    }

}