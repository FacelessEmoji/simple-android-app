package rut.miit.simpleapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import rut.miit.simpleapp.R
import rut.miit.simpleapp.adapters.ChatAdapter
import rut.miit.simpleapp.data.Chat
import rut.miit.simpleapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chats = listOf(
            Chat(R.drawable.ic_profile_placeholder, "Alice", "Hello, how are you?", "10:30 AM"),
            Chat(R.drawable.ic_profile_placeholder, "Bob", "Meeting at 2 PM", "9:15 AM"),
            Chat(R.drawable.ic_profile_placeholder, "Charlie", "Can you send me the report?", "8:45 AM")
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ChatAdapter(chats)
    }
}
