package com.example.chatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chatapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
binding= FragmentProfileBinding.inflate(inflater,container,false)
        var usermail=requireArguments().getString("email")

        // Inflate the layout for this fragment
        binding.nametv.text=usermail

        return binding.root
    }



}