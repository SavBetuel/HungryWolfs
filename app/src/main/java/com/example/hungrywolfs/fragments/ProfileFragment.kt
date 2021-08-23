package com.example.hungrywolfs.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hungrywolfs.R
import com.example.hungrywolfs.databinding.FragmentProfileBinding
import com.example.hungrywolfs.model.ProfileViewModel


const val TERMS_AND_CONDITIONS_URI= "https://www.wolfpack-digital.com/privacy"

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel=viewModel

        setupObservers()
    }

    private fun setupObservers(){
        viewModel.navigateBack.observe(viewLifecycleOwner){
            findNavController().popBackStack()
        }

        viewModel.navigateFavourites.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_profileFragment_to_favouritesFragment)
        }

        viewModel.navigateTermsAndConditions.observe(viewLifecycleOwner){
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(TERMS_AND_CONDITIONS_URI))
            startActivity(intent)
        }
    }
}