package com.im.solobarbers.views.dashboard.fragments.home.home_fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.im.solobarbers.databinding.FragmentServicesBinding
import com.im.solobarbers.viewmodel.HaircutViewModel
import com.im.solobarbers.views.adapters.HaircutAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Services_Fragment : Fragment() {

    private var servicesBinding: FragmentServicesBinding? = null
    private val binding get() = servicesBinding!!
    private val haircutModel: HaircutViewModel by viewModels()
    private lateinit var haircutAdapter: HaircutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        haircutAdapter = HaircutAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        servicesBinding = FragmentServicesBinding.inflate(inflater, container, false)
        val view = binding.root


        haircutModel.haircutResponse.observe(this.viewLifecycleOwner) { response ->

            response?.let {

                if (it.haircut != null) {
                    initRecycler()
                    haircutAdapter.setHaircut(it.haircut!!)
                    binding.servicesProgressBar.visibility = View.GONE
                } else {
                    Log.d("haircut", "${it.exception?.localizedMessage}")
                    binding.servicesProgressBar.visibility = View.GONE
                }

            }

        }


        binding.servicesProgressBar.visibility = View.VISIBLE



        return view
    }

    fun initRecycler() {
        binding.haircutRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        binding.haircutRecyclerView.setHasFixedSize(true)
        binding.haircutRecyclerView.adapter = haircutAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        servicesBinding = null
    }

}