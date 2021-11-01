package com.im.solobarbers.views.dashboard

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.im.solobarbers.R
import com.im.solobarbers.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class Dashboard_Fragment
@Inject
constructor(
    private val fAuth: FirebaseAuth
) : Fragment() {


    private var dashboardBinding: FragmentDashboardBinding? = null
    private val binding get() = dashboardBinding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root


        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.dashboardFrag_navhost) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)


        val firebaseUser = fAuth.currentUser?.email


        Log.d("user_name", "$firebaseUser")



        binding.toolbar.setTitle("Solo Barbers")


        binding.logout.setOnClickListener {
            logout()
        }


        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.toolbar_menu, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when (item.itemId) {

            R.id.logout -> {
                logout()
            }

        }


        return super.onOptionsItemSelected(item)
    }


    private fun logout() {
        fAuth.signOut()
        findNavController().navigate(R.id.action_dashboard_Fragment_to_logIn_Fragment)
    }

    override fun onStart() {
        super.onStart()

        val firebaseUser = fAuth.currentUser
        if (firebaseUser == null) {
            findNavController().navigate(R.id.action_dashboard_Fragment_to_logIn_Fragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dashboardBinding = null
    }

}