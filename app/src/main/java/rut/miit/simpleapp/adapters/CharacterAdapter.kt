package rut.miit.simpleapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rut.miit.simpleapp.databinding.ItemCharacterBinding
import rut.miit.simpleapp.data.Character

class CharacterAdapter(private val characters: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size

    class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.textViewName.text = character.name
            binding.textViewCulture.text = character.culture
            binding.textViewBorn.text = character.born
            binding.textViewTitles.text = character.titles.joinToString(", ")
            binding.textViewAliases.text = character.aliases.joinToString(", ")
            binding.textViewPlayedBy.text = character.playedBy.joinToString(", ")
        }
    }
}
