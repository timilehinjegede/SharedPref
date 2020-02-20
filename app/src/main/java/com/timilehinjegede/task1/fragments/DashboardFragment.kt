package com.timilehinjegede.task1.fragments


import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation

import com.timilehinjegede.task1.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //getting values from the shared preference instance
        val sharedPref = activity!!.getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE)
        val userFirstName = sharedPref.getString("FIRST_NAME", "")
        val userLastName = sharedPref.getString("LAST_NAME", "")
        val userEmail = sharedPref.getString("EMAIL","")

        val userNames = "$userFirstName $userLastName"

        dashboardNameTextView.text = userNames
        dashboardEmailTextView.text = userEmail

        logoutDashboardImage.setOnClickListener {

            //dialog to ask user if she/he wants to logout
            val builder = AlertDialog.Builder(context!!)
            builder.setTitle("LogOut")
            builder.setMessage("Are you sure you want to logout?")

            builder.setPositiveButton("YES") { _, _ ->
                sharedPref.edit().putBoolean("ISLOGGEDIN", false).apply()
                Navigation.findNavController(it).navigate(R.id.action_dashboardFragment_to_homeFragment)
            }

            builder.setNegativeButton("NO") { _, _ ->
                Toast.makeText(context, "No", Toast.LENGTH_LONG).show()

            }

            builder.setNeutralButton("Cancel") { _, _ ->
                Toast.makeText(context, "Cancel", Toast.LENGTH_LONG).show()

            }

            //show the dialog
            builder.show()
        }
    }
}
