package rut.miit.simpleapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import rut.miit.simpleapp.R
import rut.miit.simpleapp.MainActivity

class OnboardFragment : Fragment() {

    private val TAG = "OnboardFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboard, container, false)

        val buttonSignIn: Button = view.findViewById(R.id.button_sign_in)
        buttonSignIn.setOnClickListener {
            // Меняем фрагмент на SignInFragment
            (activity as? MainActivity)?.navigateToSignIn()
        }

        Log.d(TAG, "onCreateView called")
        return view
    }
}
