package rut.miit.simpleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import rut.miit.simpleapp.R
import rut.miit.simpleapp.MainActivity

class SignInFragment : Fragment() {

    private lateinit var textViewWelcome: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        textViewWelcome = view.findViewById(R.id.textViewWelcome)

        // Пример отображения данных (в реальности, данные могут приходить от SignUpFragment)
        val username = arguments?.getString("username")
        val email = arguments?.getString("email")

        if (username != null && email != null) {
            textViewWelcome.text = "Привет, $username! Ваша почта: $email"
            textViewWelcome.visibility = View.VISIBLE
        }

        val buttonSignIn = view.findViewById<Button>(R.id.button_sign_in)
        val buttonSignUp = view.findViewById<Button>(R.id.button_register)

        buttonSignIn.setOnClickListener {
            // Меняем фрагмент на HomeFragment
            (activity as? MainActivity)?.navigateToHome()
        }

        buttonSignUp.setOnClickListener {
            // Меняем фрагмент на SignUpFragment
            (activity as? MainActivity)?.navigateToSignUp()
        }

        return view
    }
}
