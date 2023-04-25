package com.example.androidproject

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproject.recyclerview.ContactModel
import com.example.androidproject.recyclerview.InviteAdapter
import com.example.androidproject.recyclerview.MemberAdapter
import com.example.androidproject.recyclerview.MemberModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private val listContacts: ArrayList<ContactModel> = ArrayList()


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
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listMember = listOf<MemberModel>(
            MemberModel(
                "Google",
                "13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015",
                "45%",
                "300"
            ),
            MemberModel(
                "Yahoo",
                "13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015",
                "40%",
                "333"
            ),
            MemberModel(
                "Nagarro",
                "13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015",
                "30%",
                "550"
            ),
            MemberModel(
                "graduation",
                "13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015",
                "90%",
                "555"
            ),
            MemberModel(
                "Btech",
                "13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015",
                "100%",
                "122"
            ),
            MemberModel(
                "Diploma",
                "13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015",
                "33%",
                "533"
            ),
            MemberModel(
                "Office",
                "13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015",
                "11%",
                "444"
            ),
            MemberModel(
                "Google",
                "13, Sub.Major Laxmi Chand Rd, Maruti Udyog, Sector 18, Gurugram, Haryana 122015",
                "23%",
                "876"
            ),
        )

        val adapter = MemberAdapter(listMember)
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter


        val inviteAdapter = InviteAdapter(listContacts)


        CoroutineScope(Dispatchers.IO).launch {

            listContacts.addAll(fetchContacts())

            withContext(Dispatchers.Main) {
                inviteAdapter.notifyDataSetChanged()

            }

        }

        Log.d("FetchContacts", "fetchContacts: Start calling....")

        Log.d("FetchContacts", "fetchContacts: Ending.....q")
        val inviteRecycler = requireView().findViewById<RecyclerView>(R.id.recycler_invite)
        inviteRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        inviteRecycler.adapter = inviteAdapter

    }


    @SuppressLint("Range")
    private fun fetchContacts(): ArrayList<ContactModel> {

        Log.d("FetchContacts", "fetchContacts: Start")

        val cr = requireActivity().contentResolver
        val cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

        val listContacts: ArrayList<ContactModel> = ArrayList()

        if (cursor != null && cursor.count > 0) {
            while (cursor != null && cursor.moveToNext()) {
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val hasPhoneNumber =
                    cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))

                if (hasPhoneNumber > 0) {
                    val pCur = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(id),
                        ""
                    )

                    if (pCur != null && pCur.count > 0) {
                        while (pCur != null && pCur.moveToNext()) {
                            val phoneNum =
                                pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

                            listContacts.add(ContactModel(name, phoneNum))

                        }
                        pCur.close()
                    }
                }
            }
            if (cursor != null) {
                cursor.close()
            }
        }
        Log.d("FetchContacts", "fetchContacts: End")
        return listContacts
    }


}