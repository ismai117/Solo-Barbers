package com.im.solobarbers.views.reset_password

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.im.solobarbers.R
import com.im.solobarbers.databinding.FragmentForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_forgot_password_.*
import javax.inject.Inject

@AndroidEntryPoint
class ForgotPassword_Fragment
@Inject
constructor(
    private val fAuth: FirebaseAuth
) : Fragment() {


    private var ForgetPasswordBinding: FragmentForgotPasswordBinding? = null
    private val binding get() = ForgetPasswordBinding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ForgetPasswordBinding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        val view = binding.root




        binding.submitFpBtn.setOnClickListener {

            val emailValue = binding.fpEmailInput.text.trim().toString()

            binding.fpProgressBar.visibility = View.VISIBLE

            resetPassword(emailValue)
        }
        binding.fpGotoLogIn.setOnClickListener {
            gotoLogin()
        }


        return view


    }


    private fun resetPassword(emailValue: String) {



        when {
            TextUtils.isEmpty(emailValue) -> {
                binding.fpEmailInput.error = "Empty Email Field"
                binding.fpProgressBar.visibility = View.GONE
            }
            else -> {


                fAuth.sendPasswordResetEmail(emailValue)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {

                            val snackbar = Snackbar.make(
                                this.fpCoordinatorLayout,
                                "Reset link sent to your inbox",
                                Snackbar.LENGTH_LONG
                            )
                            snackbar.show()

                            binding.fpProgressBar.visibility = View.GONE
                        } else {
                            val snackbar = Snackbar.make(
                                this.fpCoordinatorLayout,
                                "${task.exception}",
                                Snackbar.LENGTH_LONG
                            )
                            snackbar.show()

                            binding.fpProgressBar.visibility = View.GONE
                        }

                    }
            }

        }


    }


    private fun gotoLogin() {
        findNavController().navigate(R.id.action_forgotPassword_Fragment_to_logIn_Fragment)
    }


}