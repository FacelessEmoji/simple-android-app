package rut.miit.simpleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import rut.miit.simpleapp.adapters.CharacterAdapter
import rut.miit.simpleapp.data.Character
import rut.miit.simpleapp.databinding.FragmentGameOfThronesBinding
import rut.miit.simpleapp.viewmodels.CharacterViewModel
import rut.miit.simpleapp.viewmodels.CharacterViewModelFactory
import rut.miit.simpleapp.db.CharacterEntity

class GameOfThronesFragment : Fragment() {

    private var _binding: FragmentGameOfThronesBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding should not be accessed before onCreateView or after onDestroyView")

    private val viewModel: CharacterViewModel by viewModels {
        CharacterViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameOfThronesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.allCharacters.observe(viewLifecycleOwner) { characters ->
            val adapter = CharacterAdapter(characters.map {
                Character(
                    name = it.name,
                    culture = it.culture,
                    born = it.born,
                    titles = it.titles?.split(", ") ?: emptyList(),
                    aliases = it.aliases?.split(", ") ?: emptyList(),
                    playedBy = it.playedBy?.split(", ") ?: emptyList()
                )
            })
            binding.recyclerView.adapter = adapter
        }

        binding.refreshButton.setOnClickListener {
            val inputText = binding.searchEditText.text.toString()
            val pageStart = inputText.toInt()
            val pageSize = 50

            if (pageStart < 1) {
                Toast.makeText(
                    context,
                    "Invalid input. Please enter a number greater than 0.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            viewModel.fetchAndSaveCharacters(page = pageStart, pageSize = pageSize)
            Toast.makeText(
                context,
                "Fetching data for student $pageStart...",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun updateRecyclerView(characters: List<CharacterEntity>) {
        val adapter = CharacterAdapter(characters.map {
            Character(
                name = it.name,
                culture = it.culture,
                born = it.born,
                titles = it.titles?.split(", ") ?: emptyList(),
                aliases = it.aliases?.split(", ") ?: emptyList(),
                playedBy = it.playedBy?.split(", ") ?: emptyList()
            )
        })
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}