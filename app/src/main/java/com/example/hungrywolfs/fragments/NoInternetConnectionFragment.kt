package com.example.hungrywolfs.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.hungrywolfs.databinding.FragmentNoInternetConnectionBinding


class NoInternetConnectionFragment : Fragment() {

    private lateinit var binding: FragmentNoInternetConnectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoInternetConnectionBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tryAgain.setOnClickListener {
            Toast.makeText(context, "Trying to connect...", Toast.LENGTH_SHORT).show()
        }
    }

}