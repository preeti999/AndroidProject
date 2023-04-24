package com.example.androidproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.recyclerview.MemberAdapter
import com.example.androidproject.recyclerview.MemberModel

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listMember = listOf<MemberModel>(
            MemberModel("Google","13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015","45%","300"),
            MemberModel("Yahoo","13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015","40%","333"),
            MemberModel("Nagarro","13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015","30%","550"),
            MemberModel("graduation","13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015","90%","555"),
            MemberModel("Btech","13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015","100%","122"),
            MemberModel("Diploma","13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015","33%","533"),
            MemberModel("Office","13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015","11%","444"),
            MemberModel("Google","13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015","23%","876"),
        )

        val adapter = MemberAdapter(listMember)
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter


    }

}