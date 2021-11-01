package com.im.solobarbers.views

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.im.solobarbers.views.dashboard.Dashboard_Fragment
import com.im.solobarbers.views.dashboard.fragments.home.home_fragments.ConfirmBooking_Fragment
import com.im.solobarbers.views.login.LogIn_Fragment
import com.im.solobarbers.views.reset_password.ForgotPassword_Fragment
import com.im.solobarbers.views.signup.SignUp_Fragment
import javax.inject.Inject

class DefaultFragmentFactory
@Inject
constructor(
    private val fAuth: FirebaseAuth,
    private val fStore: FirebaseFirestore
) : FragmentFactory() {


    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {


        return when (className) {


            LogIn_Fragment::class.java.name -> {
                LogIn_Fragment(fAuth)
            }

            SignUp_Fragment::class.java.name -> {
                SignUp_Fragment(fAuth)
            }

            ForgotPassword_Fragment::class.java.name -> {
                ForgotPassword_Fragment(fAuth)
            }

            Dashboard_Fragment::class.java.name -> {
                Dashboard_Fragment(fAuth)
            }

            ConfirmBooking_Fragment::class.java.name -> {
                ConfirmBooking_Fragment(fAuth, fStore)
            }

            else -> {

                super.instantiate(classLoader, className)

            }

        }


    }

}