package com.example.chatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentSigninBinding
import kotlin.math.sign


class SigninFragment : Fragment() {

    lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigninBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        binding.btnRegister.setOnClickListener {

            findNavController().navigate(R.id.action_signinFragment_to_registerFragment)
        }

        binding.btnSignIn.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            signInUser(email, password)
        }
        return binding.root
    }


    private fun signInUser(email: String, password: String) {


    }

}
