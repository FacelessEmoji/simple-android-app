package rut.miit.simpleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import rut.miit.simpleapp.R
import rut.miit.simpleapp.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("Binding should not be accessed before onCreateView or after onDestroyView")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Получаем данные с помощью Safe Args
        val args = SignInFragmentArgs.fromBundle(requireArguments())
        val username = args.username
        val email = args.email

        // Устанавливаем приветствие и email
        binding.textViewWelcome.text = "Привет, $username! Ваша почта: $email"
        binding.textViewWelcome.visibility = View.VISIBLE
        binding.email.setText(email)

        binding.buttonSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
        }

        binding.buttonRegister.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
