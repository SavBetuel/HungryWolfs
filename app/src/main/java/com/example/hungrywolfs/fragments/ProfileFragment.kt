package com.example.hungrywolfs.fragments

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hungrywolfs.R
import com.example.hungrywolfs.databinding.FragmentProfileBinding
import com.example.hungrywolfs.model.ProfileViewModel
import java.util.*

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

        setMyClickListener()

    }

    private fun setupObservers(){
        viewModel.navigateBack.observe(viewLifecycleOwner){
            findNavController().popBackStack()
        }

        viewModel.navigateFavourites.observe(viewLifecycleOwner){
            findNavController().navigate(R.id.action_profileFragment_to_favouritesFragment)
        }

        viewModel.navigateTermsAndConditions.observe(viewLifecycleOwner){
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.terms_and_conditions_uri)))
            startActivity(intent)
        }
    }

    private fun setMyClickListener(){
        binding.englishLanguage.setOnClickListener{
            setLocale("en")
        }
        binding.romanianLanguage.setOnClickListener{
            setLocale("ro")
        }
    }

    fun setLocale(lang: String?) {
        val myLocale = Locale(lang)
        val res: Resources = resources
        val dm: DisplayMetrics = res.getDisplayMetrics()
        val conf: Configuration = res.getConfiguration()
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)

        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentSelf())
    }
}