package rut.miit.simpleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import rut.miit.simpleapp.R
import rut.miit.simpleapp.adapters.ChatAdapter
import rut.miit.simpleapp.data.Chat
import rut.miit.simpleapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding should not be accessed before onCreateView or after onDestroyView")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chats = listOf(
            Chat(R.drawable.ic_profile_placeholder, "Alice", "Hello, how are you?", "10:30 AM"),
            Chat(R.drawable.ic_profile_placeholder, "Bob", "Meeting at 2 PM", "9:15 AM"),
            Chat(R.drawable.ic_profile_placeholder, "Charlie", "Can you send me the report?", "8:45 AM")
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = ChatAdapter(chats)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

