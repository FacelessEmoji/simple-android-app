package rut.miit.simpleapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import rut.miit.simpleapp.data.Character
import rut.miit.simpleapp.databinding.CharacterItemBinding

class CharacterAdapter(private val people: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(people[position])
    }

    override fun getItemCount(): Int = people.size

    class CharacterViewHolder(private val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.textViewName.text = character.name ?: "Unknown"
            binding.textViewCulture.text = character.culture ?: "No culture information"
            binding.textViewBorn.text = character.born ?: "Date of birth not available"
            binding.textViewTitles.text = if (character.titles.isNotEmpty()) character.titles.joinToString(", ") else "No titles"
            binding.textViewAliases.text = if (character.aliases.isNotEmpty()) character.aliases.joinToString(", ") else "No aliases"
            binding.textViewPlayedBy.text = if (character.playedBy.isNotEmpty()) character.playedBy.joinToString(", ") else "No actor information"
        }

    }
}
