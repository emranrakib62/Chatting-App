package com.example.chatapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.chatapp.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class HomeFragment : Fragment() {
lateinit var binding: FragmentHomeBinding
lateinit var mAuth: FirebaseAuth
var userlist=mutableListOf<User>()
lateinit var  adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater,container,false)


        mAuth= FirebaseAuth.getInstance()
adapter= UserAdapter()
        binding.logoutBtn.setOnClickListener {
            mAuth.signOut()
            findNavController().navigate(R.id.action_homeFragment_to_signinFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirebaseDatabase.getInstance().reference.child("user").addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                snapshot.children.forEach {
                    var user:User = it.getValue(User::class.java)!!
                     userlist.add(user)


                }
               adapter.submitList(userlist)
              binding.userRecycler.adapter=adapter

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }


}



