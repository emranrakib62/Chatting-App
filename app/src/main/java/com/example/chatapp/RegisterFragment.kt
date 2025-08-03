package com.example.chatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentRegisterBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)

        mAuth = FirebaseAuth.getInstance()


        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_signinFragment)

        }

        binding.btnRegister.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            registerUser(email, password)

        }

        return binding.root

    }


    private fun registerUser(email: String, password: String) {
mAuth.createUserWithEmailAndPassword(email,password)
    .addOnCompleteListener{
       if(it.isSuccessful){
           findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
       }else{
           Toast.makeText(requireContext(),"", Toast.LENGTH_LONG).show()
       }
    }


    }
}