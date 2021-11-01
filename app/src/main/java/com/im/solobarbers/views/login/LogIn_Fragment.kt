package com.im.solobarbers.views.login

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.im.solobarbers.R
import com.im.solobarbers.databinding.FragmentLogInBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LogIn_Fragment
@Inject
constructor(
    private val fAuth: FirebaseAuth
) : Fragment() {

    private var LogInBinding: FragmentLogInBinding? = null
    private val binding get() = LogInBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogInBinding = FragmentLogInBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.loginBtn.setOnClickListener {

            val emailValue = binding.emailInput.text.trim().toString()
            val passwordValue = binding.passwordInput.text.trim().toString()

            binding.LoginProgressBar.visibility = View.VISIBLE

            checkifValid(emailValue, passwordValue)


        }


        binding.gotoForgetpassword.setOnClickListener {
            gotoForgetPassword()
        }

        binding.gotosignup.setOnClickListener {

            gotoSignUp()
        }

        return view
    }


    private fun checkifValid(
        emailValue: String,
        passwordValue: String
    ) {

        when {

            TextUtils.isEmpty(emailValue) && TextUtils.isEmpty(passwordValue) -> {
                binding.emailInput.error = "Empty Email Field"
                binding.passwordInput.error = "Empty Password Field"
                binding.LoginProgressBar.visibility = View.GONE
            }


            TextUtils.isEmpty(emailValue) -> {
                binding.emailInput.error = "Empty Email Field"
                binding.LoginProgressBar.visibility = View.GONE
            }

            TextUtils.isEmpty(passwordValue) -> {
                binding.passwordInput.error = "Empty Password Field"
                binding.LoginProgressBar.visibility = View.GONE
            }



            else -> {

                fAuth.signInWithEmailAndPassword(emailValue, passwordValue)
                    .addOnCompleteListener(
                        OnCompleteListener { task ->

                            if (task.isSuccessful) {

                                Log.d("User_Logged", "User signed in")

                                findNavController().navigate(R.id.action_logIn_Fragment_to_dashboard_Fragment)
                                binding.LoginProgressBar.visibility = View.GONE

                            } else {

                                Log.d("User_Logged", "${task.exception?.message}")

                                binding.LoginProgressBar.visibility = View.GONE
                            }


                        }
                    )


            }

        }
    }


    private fun gotoForgetPassword() {
        findNavController().navigate(R.id.action_logIn_Fragment_to_forgotPassword_Fragment)
    }

    private fun gotoSignUp() {
        findNavController().navigate(R.id.action_logIn_Fragment_to_signUp_Fragment)
    }


    override fun onDestroy() {
        super.onDestroy()
        LogInBinding = null
    }


}