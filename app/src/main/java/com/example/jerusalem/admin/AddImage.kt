package com.example.jerusalem.admin

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.jerusalem.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.fragment_add_image.*
import kotlinx.android.synthetic.main.fragment_add_image.view.*
import java.text.SimpleDateFormat
import java.util.*

class AddImage : Fragment() {
    private var db: FirebaseFirestore? = FirebaseFirestore.getInstance()
    private val PICK_IMAGE_REQUEST = 7
    var imageUri: Uri? = null
    var imageRef: StorageReference? = null
    var fileUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_add_image, container, false)

        val storage = Firebase.storage
        val storageRef = storage.reference
        imageRef = storageRef.child("images")
        root.pickImage.setOnClickListener {
            pickImage()
        }
        root.textUpload.setOnClickListener{
            pickImage()
        }
        root.btnUpload.setOnClickListener {
            if (root.titleImage.text.toString().isEmpty()) {
                validTitleImage.setText("لا يمكن ترك هذا الحقل فارغا")
                validDescImage.setText("")
            } else if (root.descImage.text.toString().isEmpty()) {
                validTitleImage.setText("")
                validDescImage.setText("لا يمكن ترك هذا الحقل فارغا")
            } else {
                uploadImage()
                validTitleImage.setText("")
                validDescImage.setText("")
            }
        }
        return root
    }

    private fun pickImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select an image"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            imageUri = data!!.data
            Log.e("imageUri", imageUri.toString())
            image.setImageURI(imageUri)
            pickImage.setImageResource(R.drawable.ic_done_black_24dp)
            textUpload.setText("تم إختيار صورة بنجاح")


        }
    }

    private fun uploadImage() {
        if (imageUri != null) {
            val pd = ProgressDialog(activity)
            pd.setMessage("جاري الحفظ")
            pd.show()
            val ref = imageRef!!.child("images/" + UUID.randomUUID().toString())
            ref.putFile(imageUri!!).addOnSuccessListener { taskSnapshot ->
                ref.downloadUrl.addOnSuccessListener { uri ->
                    fileUri = uri
                    Log.e("URI image >", uri.toString())
                    val date: String =
                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

                    saveImage(
                        titleImage.text.toString(),
                        descImage.text.toString(),
                        date,
                        fileUri.toString()
                    )
                    titleImage.setText("")
                    descImage.setText("")
                    pickImage.setImageResource(R.drawable.ic_cloud_upload_black_24dp)
                    textUpload.setText("قم بإختيار صورة")

                }

                Log.e("TAG", "Uploaded...")
                Toast.makeText(activity, "تم الحفظ بنجاح", Toast.LENGTH_SHORT).show()
                pd.dismiss()


            }
                .addOnFailureListener { e ->
                    Log.e("TAG", "Failed...")
                }
        }
    }


    fun saveImage(titleImage: String, descImage: String, date: String, image: String) {

        val images = hashMapOf(
            "titleImage" to titleImage,
            "descImage" to descImage,
            "date" to date,
            "image" to image
        )
        db!!.collection("image")
            .add(images)
            .addOnSuccessListener { documentReference ->
                Log.e("TAG", "added id ${documentReference.id}")
            }
            .addOnFailureListener { exception ->
                Log.e("TAG", "error in saving")
            }
    }
}



