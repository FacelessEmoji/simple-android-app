package rut.miit.simpleapp.fragments

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
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
import rut.miit.simpleapp.utils.FileManager
import java.io.File

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

        activity?.window?.decorView?.systemUiVisibility =
            View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        fetchCharacters()
    }

    private fun saveCharactersToFile(heroes: List<Character>) {
        val content = "Некоторый текст для сохранения в файл"
        val isSaved = FileManager.saveToExternalStorage(content)

        if (isSaved) {
            val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "myfile.txt")
            Toast.makeText(requireContext(), "Файл сохранён: ${file.absolutePath}", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Не удалось сохранить файл.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fetchCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val people: List<Character> = GameOfThronesApi.getCharacters(1, 50)

                withContext(Dispatchers.Main) {
                    if (people.isNotEmpty()) {
                        binding.recyclerView.adapter = CharacterAdapter(people)
                        saveCharactersToFile(people)
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

        GameOfThronesApi.closeClient()
    }
}
