package com.example.jerusalem

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jerusalem.adapter.ImageAdapter
import com.example.jerusalem.model.ImageModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_jerusalem_image.*

/**
 * A simple [Fragment] subclass.
 */
class JerusalemImage : Fragment(), ImageAdapter.onClick {
    var db: FirebaseFirestore? = null
    val TAG = "tag"
    var list = ArrayList<ImageModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = Firebase.firestore

        val root = inflater.inflate(R.layout.fragment_jerusalem_image, container, false)
        getImage()
        return root
    }

    private fun getImage() {
        val pd = ProgressDialog(activity)
        pd.setMessage("انتظر قليلا...")
        pd.show()
        db!!.collection("image").get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
                Log.e(TAG, "${document.id} => ${document.data}")
                list.add(
                    ImageModel(
                        document.data.get("titleImage").toString(),
                        document.data.get("descImage").toString(),
                        document.data.get("date").toString(),
                        document.data.get("image").toString(),
                    )
                )
                pd.dismiss()
            }
            rvjImage.layoutManager = LinearLayoutManager(context)
            val ad = ImageAdapter(activity!!, list, this)
            rvjImage.adapter = ad
        }
            .addOnFailureListener { exception ->
                exception.message?.let { Log.e(TAG, it) }
            }
    }

    override fun onClickItem(position: Int) {
        val fragment = Details()
        val i = Bundle()
        i.putString("title", list[position].titleImage)
        i.putString("description", list[position].descImage)
        i.putString("image", list[position].imageV)
        i.putString("time", list[position].time)
        fragment.setArguments(i)
        activity!!.supportFragmentManager.beginTransaction().replace(
            R.id.mainContainer,
            fragment
        ).addToBackStack(null).commit()

    }

}