package com.timilehinjegede.task1.fragments


import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation

import com.timilehinjegede.task1.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sharedPref = activity!!.getSharedPreferences("USER_CREDENTIALS",MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("ISLOGGEDIN",false)

        if(isLoggedIn){
            Navigation.findNavController(view!!).navigate(R.id.action_loginFragment_to_dashboardFragment)
        }

        val loginName = sharedPref.getString("FIRST_NAME","")
        val loginEmail = sharedPref.getString("EMAIL","")
        val loginPassword = sharedPref.getString("PASSWORD","")


        //set a listener to the login button
        loginFragmentButton.setOnClickListener {

            var isValid : Boolean = true

            val loginEmailInput = textInputEditTextEmail_login.text.toString()
            val loginPasswordInput = textInputEditTextPassword_login.text.toString()

            if(loginEmailInput!=loginEmail){
                textInputLayoutEmail.error= "Incorrect Email"
                isValid = false
            }
            if (loginPasswordInput!=loginPassword){
                textInputLayoutPassword.error ="Incorrect Password"
                isValid = false
            }

            if (loginEmailInput.isEmpty()){
                textInputLayoutEmail.error= "Email cannot be empty"
                isValid = false
            }

            if(loginPasswordInput.isEmpty()){
                textInputLayoutPassword.error ="Password cannot be empty"
                isValid = false
            }

            if (isValid){
                sharedPref.edit().putBoolean("ISLOGGEDIN",false).apply()

                Toast.makeText(context,"Welcome back $loginName",Toast.LENGTH_LONG).show()
                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_dashboardFragment)
            }


        }

        //set a click listener to the register textview
        fragmentTextViewRegister.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}
