package com.example.chatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class RegisterFragment : Fragment() {

    lateinit var binding: FragmentRegisterBinding
    lateinit var mAuth: FirebaseAuth
    lateinit var firebaseUser: FirebaseUser
    lateinit var userId: String
    lateinit var myRef: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater, container, false)

        mAuth = FirebaseAuth.getInstance()

        val database= Firebase.database
        myRef = database.reference.child("user")

        binding.btnSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_signinFragment)

        }

        binding.btnRegister.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val name=binding.editTextName.text.toString()
            val phone=binding.editTextPhone.text.toString()
            val address=binding.editTextAddress.text.toString()
            registerUser(email,password,name,phone,address)

        }

        return binding.root

    }


    private fun registerUser(
        email: String,
        password: String,
        name: String,
        phone: String,
        address: String
    ) {
mAuth.createUserWithEmailAndPassword(email,password)
    .addOnCompleteListener{
       if(it.isSuccessful){
           firebaseUser= FirebaseAuth.getInstance().currentUser!!
           userId=firebaseUser.uid
           val map=mapOf("name" to name,"email" to email,"phone" to phone,"password" to password,"address" to address)


           if(userId!=null){
               myRef.child(userId).setValue(map).addOnCompleteListener {

            if(it.isSuccessful){
           findNavController().navigate(R.id.action_registerFragment_to_homeFragment)

            }else{
           Toast.makeText(requireContext(),"${it.exception?.message}", Toast.LENGTH_LONG).show()
       }
    }
           }
       }else{
           Toast.makeText(requireContext(),"${it.exception?.message}", Toast.LENGTH_LONG).show()
       }
    }

    }
}