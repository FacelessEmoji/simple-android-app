package rut.miit.simpleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rut.miit.simpleapp.adapters.CharacterAdapter
import rut.miit.simpleapp.data.Character
import rut.miit.simpleapp.databinding.FragmentGameOfThronesBinding

class GameOfThronesFragment : Fragment() {

    private var _binding: FragmentGameOfThronesBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding should not be accessed before onCreateView or after onDestroyView")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameOfThronesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        fetchCharacters()
    }

    private fun fetchCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val people: List<Character> = GameOfThronesApi.getCharacters(1, 50)

                withContext(Dispatchers.Main) {
                    if (people.isNotEmpty()) {
                        binding.recyclerView.adapter = CharacterAdapter(people)
                    } else {
                        showToast("No characters found.")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    showToast("Error fetching characters.")
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
