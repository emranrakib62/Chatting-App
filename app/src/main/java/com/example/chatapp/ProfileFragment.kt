package com.example.chatapp

import android.app.Activity
import android.app.Instrumentation
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load

import com.example.chatapp.databinding.FragmentProfileBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.DexterError
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.PermissionRequestErrorListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.util.jar.Manifest


class ProfileFragment : Fragment() {

    lateinit var firebaseDatabaseReference: DatabaseReference
    lateinit var binding: FragmentProfileBinding
    lateinit var firebaseStorage: StorageReference
    lateinit var user: User


    private lateinit var fileUri: Uri
    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {

                fileUri = data?.data!!

                binding.profileImage.setImageURI(fileUri)
                binding.imagepicker.text=UPLOAD



            } else if (resultCode == ImagePicker.RESULT_ERROR) {

            } else {

            }
        }







    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val database= Firebase.database
        firebaseDatabaseReference = database.reference.child("User").child(user.userId)

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        firebaseStorage= FirebaseStorage.getInstance().getReference("Upload")


        user = requireArguments().getParcelable<User>("email")!!



        binding.nametv.text = user.email
        binding.mobiletv.text=user.phone
        binding.imagepicker.setOnClickListener {

            if(binding.imagepicker.text==UPLOAD)
            {

                startImageUpload()

            }else{
                requestPermissions()
            }



        }


        return binding.root
    }


    private fun startImageUpload() {
        var storageReference: StorageReference=firebaseStorage.child("Profile").child("profile-img.${user.userId}")
        storageReference.putFile(fileUri).addOnCompleteListener {task ->
            if(task.isSuccessful){
                storageReference.downloadUrl.addOnSuccessListener {

                    var url: String =it.toString()

                    val map=mapOf(


                        "profileimgurl" to url


                    )



                    firebaseDatabaseReference.updateChildren(map).addOnSuccessListener {profileUrl ->
                        Toast.makeText(requireContext(),"profile image uploaded", Toast.LENGTH_LONG).show()


                        binding.profileImage.load(profileUrl)
                    }
                }
            }
        }


    }



    companion object{
        const val USER="user"
        const val UPLOAD="Upload"
    }

    private fun requestPermissions() {


        Dexter.withContext(requireActivity())
            .withPermissions(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,

                )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(multiplePermissionsReport: MultiplePermissionsReport) {
                    // this method is called when all permissions are granted
                    if (multiplePermissionsReport.areAllPermissionsGranted()) {
                        // do you work now
                        pickanImage()
                    }

                    if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {


                    }
                }

                public override fun onPermissionRationaleShouldBeShown(
                    list: MutableList<PermissionRequest?>?,
                    permissionToken: PermissionToken
                ) {
                    // this method is called when user grants some permission and denies some of them.
                    permissionToken.continuePermissionRequest()
                }
            }).withErrorListener(PermissionRequestErrorListener { error: DexterError? ->


            })
            .onSameThread().check()
    }


    fun pickanImage(){

        ImagePicker.with(this)
            .crop()
            .compress(500)
            .maxResultSize(200, 200)
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }



    }

}

