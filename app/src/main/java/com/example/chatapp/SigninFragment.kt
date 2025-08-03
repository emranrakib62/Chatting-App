package com.example.chatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentSigninBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlin.math.sign


class SignInFragment : Fragment() {

    lateinit var binding: FragmentSigninBinding
    lateinit var mAuth: FirebaseAuth
    private var firebaseUser: FirebaseUser? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        FirebaseApp.initializeApp(requireContext())
        binding = FragmentSigninBinding.inflate(layoutInflater, container, false)

        mAuth = FirebaseAuth.getInstance()
        firebaseUser = FirebaseAuth.getInstance().currentUser

        if (firebaseUser != null) {
            findNavController().navigate(R.id.action_signinFragment_to_homeFragment2)
        }

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
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    findNavController().navigate(R.id.action_signinFragment_to_homeFragment2)
                } else {
                    Toast.makeText(requireContext(), "${it.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }



}