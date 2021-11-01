package com.im.solobarbers.views.signup

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.im.solobarbers.R
import com.im.solobarbers.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUp_Fragment
@Inject
constructor(
    private val fAuth: FirebaseAuth
) : Fragment() {

    private var SignUpBinding: FragmentSignUpBinding? = null
    private val binding get() = SignUpBinding!!
    private val fStore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        SignUpBinding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root



        binding.createBtn.setOnClickListener {

            val nameValue = binding.signupNameInput.text.trim().toString()
            val phoneValue = binding.signupNumberInput.text.trim().toString()
            val emailValue = binding.signupEmailInput.text.trim().toString()
            val passwordValue = binding.signupPasswordInput.text.trim().toString()


            binding.SignUpProgressBar.visibility = View.VISIBLE


            insertUser(nameValue, phoneValue, emailValue, passwordValue)

            binding.signupNameInput.text.clear()
            binding.signupNumberInput.text.clear()
            binding.signupEmailInput.text.clear()
            binding.signupPasswordInput.text.clear()


        }


        binding.gotologin.setOnClickListener {
            gotoLogin()
        }


        return view
    }

    private fun insertUser(
        nameValue: String,
        phoneValue: String,
        emailValue: String,
        passwordValue: String
    ) {


        when {


            TextUtils.isEmpty(nameValue) && TextUtils.isEmpty(phoneValue) && TextUtils.isEmpty(
                emailValue
            ) && TextUtils.isEmpty(passwordValue) -> {
                binding.signupNameInput.error = "Empty Name Field"
                binding.signupNumberInput.error = "Empty Phone Field"
                binding.signupEmailInput.error = "Empty Email Field"
                binding.signupPasswordInput.error = "Empty Password Field"
                binding.SignUpProgressBar.visibility = View.GONE
            }

            TextUtils.isEmpty(nameValue) -> {
                binding.signupNameInput.error = "Empty Name Field"
                binding.SignUpProgressBar.visibility = View.GONE
            }
            TextUtils.isEmpty(phoneValue) -> {
                binding.signupNumberInput.error = "Empty Phone Field"
                binding.SignUpProgressBar.visibility = View.GONE
            }
            TextUtils.isEmpty(emailValue) -> {
                binding.signupEmailInput.error = "Empty Email Field"
                binding.SignUpProgressBar.visibility = View.GONE
            }
            TextUtils.isEmpty(passwordValue) -> {
                binding.signupPasswordInput.error = "Empty Password Field"
                binding.SignUpProgressBar.visibility = View.GONE
            }




            else -> {


                fAuth.createUserWithEmailAndPassword(emailValue, passwordValue)
                    .addOnCompleteListener(
                        OnCompleteListener { task ->
                            if (task.isSuccessful) {

                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                val userId = firebaseUser.uid

                                val user = hashMapOf(
                                    "name" to nameValue,
                                    "phone" to phoneValue,
                                    "userId" to userId,
                                    "email" to emailValue
                                )

                                fStore.collection("users")
                                    .add(user)
                                    .addOnSuccessListener(
                                        OnSuccessListener { documentReference ->


                                            Log.d(
                                                "User_Created_Details",
                                                "DocumentSnapchat made with ID ${documentReference.id}"
                                            )

                                        }
                                    )
                                    .addOnFailureListener(
                                        OnFailureListener { e ->

                                            Log.d(
                                                "User_Created_Details",
                                                "DocumentSnapchat made with ID ${e.message}"
                                            )

                                        }
                                    )

                                Log.d(
                                    "User_Created",
                                    "Successfully Created"
                                )

                                binding.SignUpProgressBar.visibility = View.GONE

                                findNavController().navigate(R.id.action_signUp_Fragment_to_logIn_Fragment)

                            } else {

                                Log.d(
                                    "User_Created",
                                    "Error: ${task.exception?.message}"
                                )

                                binding.SignUpProgressBar.visibility = View.GONE

                            }
                        }
                    )
            }

        }


    }

    private fun gotoLogin() {
        findNavController().navigate(R.id.action_signUp_Fragment_to_logIn_Fragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        SignUpBinding = null
    }


}