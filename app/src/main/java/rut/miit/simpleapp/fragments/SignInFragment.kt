package rut.miit.simpleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import rut.miit.simpleapp.R
import rut.miit.simpleapp.MainActivity

class SignInFragment : Fragment() {

    private lateinit var textViewWelcome: TextView
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        textViewWelcome = view.findViewById(R.id.textViewWelcome)
        editTextEmail = view.findViewById(R.id.email)  // Поле для ввода email
        editTextPassword = view.findViewById(R.id.password)  // Поле для ввода пароля

        // Получаем данные из аргументов
        val username = arguments?.getString("username")
        val email = arguments?.getString("email")

        // Если данные переданы, выводим их на экран
        if (username != null && email != null) {
            textViewWelcome.text = "Привет, $username! Ваша почта: $email"
            textViewWelcome.visibility = View.VISIBLE

            // Устанавливаем email в поле формы
            editTextEmail.setText(email)
        }

        val buttonSignIn = view.findViewById<Button>(R.id.button_sign_in)
        val buttonSignUp = view.findViewById<Button>(R.id.button_register)

        buttonSignIn.setOnClickListener {
            // Переход на HomeFragment
            (activity as? MainActivity)?.navigateToHome()
        }

        buttonSignUp.setOnClickListener {
            // Переход на SignUpFragment
            (activity as? MainActivity)?.navigateToSignUp()
        }

        return view
    }
}
