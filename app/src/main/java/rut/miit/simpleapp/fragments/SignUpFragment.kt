package rut.miit.simpleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import rut.miit.simpleapp.R
import rut.miit.simpleapp.MainActivity

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        val editTextUsername = view.findViewById<EditText>(R.id.editTextUsername)
        val editTextEmail = view.findViewById<EditText>(R.id.editTextEmail)
        val buttonRegister = view.findViewById<Button>(R.id.button_register)

        buttonRegister.setOnClickListener {
            val username = editTextUsername.text.toString().trim()
            val email = editTextEmail.text.toString().trim()

            if (username.isNotEmpty() && email.isNotEmpty()) {
                // Передаем данные в SignInFragment
                val fragment = SignInFragment().apply {
                    arguments = Bundle().apply {
                        putString("username", username)
                        putString("email", email)
                    }
                }

                (activity as? MainActivity)?.loadFragment(fragment)
            } else {
                // Выводим ошибку, если поля пустые
                Toast.makeText(activity, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
