package rut.miit.simpleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import rut.miit.simpleapp.fragments.HomeFragment
import rut.miit.simpleapp.fragments.OnboardFragment
import rut.miit.simpleapp.fragments.SignInFragment
import rut.miit.simpleapp.fragments.SignUpFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // При запуске приложения показываем OnboardFragment
        if (savedInstanceState == null) {
            loadFragment(OnboardFragment())
        }
    }

    // Метод для навигации к OnboardFragment
    fun navigateToOnboard() {
        loadFragment(OnboardFragment())
    }

    // Метод для навигации к SignInFragment
    fun navigateToSignIn() {
        loadFragment(SignInFragment())
    }

    // Метод для навигации к SignUpFragment
    fun navigateToSignUp() {
        loadFragment(SignUpFragment())
    }

    // Метод для навигации к HomeFragment
    fun navigateToHome() {
        loadFragment(HomeFragment())
    }

    // Общий метод для замены фрагментов
    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null) // Добавляем в back stack для навигации "назад"
            .commit()
    }
}
