package rut.miit.simpleapp.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import rut.miit.simpleapp.R

class SignInActivity : AppCompatActivity() {
    private val TAG = "SignInActivity"
    private val REQUEST_CODE_SIGN_UP = 1

    private lateinit var textViewWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        textViewWelcome = findViewById(R.id.textViewWelcome)

        val intent = intent
        val username = intent.getStringExtra("username")
        val email = intent.getStringExtra("email")

        if (username != null && email != null) {
            textViewWelcome.text = "Привет, $username! Ваша почта: $email"
            textViewWelcome.visibility = View.VISIBLE
        }

        val buttonSignIn = findViewById<Button>(R.id.button_sign_in)
        val buttonSignUp = findViewById<Button>(R.id.button_register)

        buttonSignIn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        buttonSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SIGN_UP)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SIGN_UP && resultCode == Activity.RESULT_OK) {
            val username = data?.getStringExtra("username")
            val email = data?.getStringExtra("email")

            if (username != null && email != null) {
                textViewWelcome.text = "Привет, $username! Ваша почта: $email"
                textViewWelcome.visibility = View.VISIBLE
            }
        }
    }
}
