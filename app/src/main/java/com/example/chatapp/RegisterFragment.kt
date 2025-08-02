package com.example.chatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

     lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)

        binding.btnSignIn.setOnClickListener {
           findNavController().navigate(R.id.action_registerFragment_to_signinFragment)

        }

        binding.btnRegister.setOnClickListener {
            var email=binding.editTextEmail.text.toString()
            var password =binding.editTextPassword.text.toString()
            registerUser(email,password)

        }



        return binding.root
    }
}

private fun RegisterFragment.registerUser(email: String, password: String) {


}
