package com.example.chatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment() {
lateinit var binding: FragmentHomeBinding
lateinit var mAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)


        mAuth= FirebaseAuth.getInstance()

        binding.btnLogout.setOnClickListener {
            mAuth.signOut()
            findNavController().navigate(R.id.action_homeFragment_to_signinFragment)
        }
        return binding.root
    }


}



