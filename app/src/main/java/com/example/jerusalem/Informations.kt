package com.example.jerusalem

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jerusalem.adapter.InformationAdapter
import com.example.jerusalem.model.InfoModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_articles.*

/**
 * A simple [Fragment] subclass.
 */
class Informations : Fragment() ,InformationAdapter.onClick{
    var db: FirebaseFirestore? = null
    val TAG = "tag"
    var list = ArrayList<InfoModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = Firebase.firestore
        val root = inflater.inflate(R.layout.fragment_articles, container, false)
        getInfo()
        return root
    }

    private fun getInfo() {
        val pd = ProgressDialog(activity)
        pd.setMessage("انتظر قليلا...")
        pd.show()
        db!!.collection("paragraph").get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
                Log.e(TAG, "${document.id} => ${document.data}")
                list.add(
                    InfoModel(
                        document.data.get("title").toString(),
                        document.data.get("description").toString(),
                        document.data.get("date").toString(),

                        )
                )
                pd.dismiss()

            }
            rvarticles!!.layoutManager = LinearLayoutManager(context)
            val Adapter_product = InformationAdapter(activity!!, list,this)
            rvarticles.adapter = Adapter_product
        }
            .addOnFailureListener { exception ->
                exception.message?.let { Log.e(TAG, it) }
            }
    }
    override fun onClickItem(position: Int) {
        val fragment = InfoDetails()
        val i = Bundle()
        i.putString("title", list[position].title)
        i.putString("description", list[position].description)
        i.putString("time", list[position].time)


        fragment.setArguments(i)
        activity!!.supportFragmentManager.beginTransaction().replace(
            R.id.mainContainer,
            fragment
        ).addToBackStack(null).commit()

    }

}
