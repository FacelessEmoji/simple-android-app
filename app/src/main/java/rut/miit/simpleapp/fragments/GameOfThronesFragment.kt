package rut.miit.simpleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rut.miit.simpleapp.adapters.CharacterAdapter
import rut.miit.simpleapp.databinding.FragmentGameOfThronesBinding

class GameOfThronesFragment : Fragment() {

    private var _binding: FragmentGameOfThronesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameOfThronesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Устанавливаем LayoutManager для RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        // Получение данных с API
        fetchCharacters()
    }

    private fun fetchCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Получаем 50 персонажей
                val characters = GameOfThronesApi.getCharacters(1, 50)

                // Обновляем UI в главном потоке
                withContext(Dispatchers.Main) {
                    binding.recyclerView.adapter = CharacterAdapter(characters)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
