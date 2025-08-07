package com.example.chatapp

import android.app.Activity
import android.app.Instrumentation
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.chatapp.databinding.FragmentProfileBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.DexterError
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.PermissionRequestErrorListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.util.jar.Manifest


class ProfileFragment : Fragment() {


    lateinit var binding: FragmentProfileBinding




    private lateinit var fileUri: Uri
    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {

                 fileUri = data?.data!!

                binding.profileImage.setImageURI(fileUri)
                binding.imagepicker.text="Upload"



            } else if (resultCode == ImagePicker.RESULT_ERROR) {

            } else {

            }
        }







    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        var user = requireArguments().getParcelable<User>("email")!!



        binding.nametv.text = user.email
        binding.mobiletv.text=user.phone
       binding.imagepicker.setOnClickListener {

           if(binding.imagepicker.text=="")



    requestPermissions()
}


        return binding.root
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
                    // check for permanent denial of any permission
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
