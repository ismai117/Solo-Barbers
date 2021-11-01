package com.im.solobarbers.views.dashboard.fragments.favourites

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.im.solobarbers.R
import com.im.solobarbers.databinding.FragmentFavouritesBinding
import com.im.solobarbers.viewmodel.FavouriteViewModel
import com.im.solobarbers.views.adapters.FavouriteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Favourites_Fragment : Fragment() {


    private var favouriteBinding: FragmentFavouritesBinding? = null
    private val binding get() = favouriteBinding!!
    private val favouriteModel: FavouriteViewModel by viewModels()
    private lateinit var favouriteAdapter: FavouriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favouriteAdapter = FavouriteAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouriteBinding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val view = binding.root



        favouriteModel.favourites.observe(this.viewLifecycleOwner) { response ->

            response?.let {

                if (it.favourite != null) {
                    initRecycler()
                    favouriteAdapter.setFavourite(it.favourite!!)
                    binding.favProgressBar.visibility = View.GONE
                } else {
                    Log.d("favourites", "${it.exception?.localizedMessage}")
                    binding.favProgressBar.visibility = View.GONE
                }

            }

        }


        binding.favProgressBar.visibility = View.VISIBLE



        return view
    }

    private fun initRecycler() {
        binding.favouriteRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        binding.favouriteRecyclerView.setHasFixedSize(true)
        binding.favouriteRecyclerView.adapter = favouriteAdapter
    }


    override fun onDestroy() {
        super.onDestroy()
        favouriteBinding = null
    }


}