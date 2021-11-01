package com.im.solobarbers.views.dashboard.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.im.solobarbers.R
import com.im.solobarbers.databinding.FragmentHomeBinding
import com.im.solobarbers.viewmodel.HaircutViewModel
import com.im.solobarbers.views.adapters.HaircutAdapter


class Home_Fragment : Fragment() {


    private var homeBinding: FragmentHomeBinding? = null
    private val binding get() = homeBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        homeBinding = null
    }

}