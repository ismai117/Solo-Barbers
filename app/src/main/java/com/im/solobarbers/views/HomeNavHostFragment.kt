package com.im.solobarbers.views

import android.content.Context
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.im.solobarbers.views.DefaultFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeNavHostFragment : NavHostFragment(){

    @Inject
    lateinit var defaultFragmentFactory: DefaultFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)

        childFragmentManager.fragmentFactory = defaultFragmentFactory
    }
}