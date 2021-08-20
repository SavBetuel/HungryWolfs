package com.example.hungrywolfs.fragments


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hungrywolfs.R
import com.example.hungrywolfs.databinding.FragmentProfileBinding
import com.example.hungrywolfs.model.ProfileViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

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
        binding.profile=this
        viewModel.navigateBack.observe(viewLifecycleOwner){
            findNavController().popBackStack()
        }
    }

    fun goFavourites(){
        findNavController().navigate(R.id.action_profileFragment_to_favouritesFragment)
    }

    fun termsAndConditions(){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wolfpack-digital.com/privacy"))
        startActivity(intent)
    }
}