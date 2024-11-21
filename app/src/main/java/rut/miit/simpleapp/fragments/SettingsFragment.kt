package rut.miit.simpleapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.navigation.fragment.findNavController
import rut.miit.simpleapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        // Установка значений из SharedPreferences или значений по умолчанию
        initializeSettings()

        // Слушатель переключателя темы
        binding.themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            saveThemePreference(isChecked)
        }

        // Обработчик кнопки "Сохранить"
        binding.saveButton.setOnClickListener {
            saveSettings()
        }

        // Добавляем обработчик кнопки "Назад"
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Возвращаемся назад
                    findNavController().popBackStack()
                }
            }
        )
    }

    private fun initializeSettings() {
        val notificationsEnabled = sharedPreferences.getBoolean("notifications", true)
        val isDarkTheme = sharedPreferences.getBoolean("theme", false)
        val userEmail = sharedPreferences.getString("email", "")

        binding.notificationsSwitch.isChecked = notificationsEnabled
        binding.themeSwitch.isChecked = isDarkTheme
        binding.userEmail.setText(userEmail)
    }

    private fun saveSettings() {
        val email = binding.userEmail.text.toString()

        // Проверка корректности email
        if (email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            sharedPreferences.edit().apply {
                putBoolean("notifications", binding.notificationsSwitch.isChecked)
                putBoolean("theme", binding.themeSwitch.isChecked)
                putString("email", email)
                apply()
            }
            Toast.makeText(requireContext(), "Настройки сохранены", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Введите корректный email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveThemePreference(isDarkTheme: Boolean) {
        sharedPreferences.edit().putBoolean("theme", isDarkTheme).apply()
        Toast.makeText(
            requireContext(),
            if (isDarkTheme) "Тёмная тема включена" else "Светлая тема включена",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}