package com.example.sigthseeingguide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CardAdapter(private val cards: List<CardModel>, private val context: Context) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        val card = cards[position]
        holder.cardName.text = card.cardName
        holder.cardDesc.text = card.cardDesc
        Glide.with(context)
            .load(card.cardImage)
            .into(holder.cardImage)
        holder.button.setOnClickListener {
            val action = PlacesFragmentDirections.actionPlacesFragmentToBottomNavigationFragment(
                card.cardName,
                card.info,
                card.latitude,
                card.longitude
            )
            it.findNavController().navigate(action)

        }
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardName: TextView = itemView.findViewById(R.id.text_view_card_name)
        val cardDesc: TextView = itemView.findViewById(R.id.text_view_card_desc)
        val cardImage: ImageView = itemView.findViewById(R.id.image_view_card)
        val button: Button = itemView.findViewById(R.id.button_details)
    }
}
