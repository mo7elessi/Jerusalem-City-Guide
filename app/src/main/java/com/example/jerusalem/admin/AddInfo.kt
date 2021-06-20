package com.example.jerusalem.admin

import android.app.ProgressDialog
import android.os.Bundle
import android.service.autofill.SaveInfo
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.jerusalem.R

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_add_parg.*
import kotlinx.android.synthetic.main.fragment_add_parg.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class AddInfo : Fragment() {
    lateinit var db: FirebaseFirestore
    val TAG = "tag"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = Firebase.firestore
        val root = inflater.inflate(R.layout.fragment_add_parg, container, false)
        root.btnBarg.setOnClickListener {
            if (root.title.text.toString().isEmpty()) {
                validTitle.setText("لا يمكن ترك هذا الحقل فارغا")
                validDesc.setText("")


            } else if (root.desc.text.toString().isEmpty()) {
                validDesc.setText("لا يمكن ترك هذا الحقل فارغا")
                validTitle.setText("")

            } else {
                validTitle.setText("")
                validDesc.setText("")
                val date: String =
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

                SaveInfo(root.title.text.toString(), root.desc.text.toString(), date)
                title.setText("")
                desc.setText("")

            }
        }
        return root
    }

    private fun SaveInfo(title: String, description: String, date: String) {

        val info = hashMapOf(
            "title" to title,
            "description" to description,
            "date" to date,

            )
        val pd = ProgressDialog(activity)
        pd.setMessage("جاري الحفظ")
        pd.show()
        db.collection("paragraph")
            .add(info)
            .addOnSuccessListener { documentReference ->
                pd.dismiss()
                Toast.makeText(activity!!, "تم الحفظ بنجاح", Toast.LENGTH_SHORT)
                Log.e(TAG, "paragraph added, paragraph id ${documentReference.id}")
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "error in saving")
            }
    }
}
