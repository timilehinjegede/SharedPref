package com.timilehinjegede.task1.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.timilehinjegede.task1.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Navigate to login fragment when the login button is clicked
        loginHomeButton.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_loginFragment)
        }

        //Navigate to the register fragment when the register button is clicked
        registerHomeButton.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_registerFragment)
        }
    }

}
