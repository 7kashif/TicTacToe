package com.example.tictactoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.tictactoe.databinding.FragmentHomeBinding

class HomeFragment:Fragment() {
    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        binding.playFriendsButton.setOnClickListener {
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToOneVsOneFragment())
        }

        binding.autoPlayButton.setOnClickListener {
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAutoPlayFragment())
        }

        return binding.root
    }


}