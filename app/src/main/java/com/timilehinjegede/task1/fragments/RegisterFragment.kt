package com.timilehinjegede.task1.fragments


import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.timilehinjegede.task1.R
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //set a listener to the register button
        registerFragmentButton.setOnClickListener {

            val firstName = textInputEditTextFirstName.text.toString()
            val lastName = textInputEditTextLastName.text.toString()

            val email = textInputEditTextEmail.text.toString()

            val passwordOne = textInputEditTextPassword.text.toString()
            val passwordTwo = textInputEditTextConfirmPassword.text.toString()

            var isValid  = true

            //client side validation
            if (passwordOne != passwordTwo) {
                textInputLayoutConfirmPassword.error = "Passwords dont match"
                isValid = false
            }

            //client side validation
            if(firstName.isEmpty()){
                textInputLayoutFirstName.error = "Firstname cannot be empty"
                isValid = false
            }

            //client side validation
            if(lastName.isEmpty()){
                textInputLayoutLastName.error = "Lastname cannot be empty"
                isValid = false
            }

            //client side validation
            if(email.isEmpty()){
                textInputLayoutEmail_register.error="Email cannot be empty"
                isValid = false
            }

            //client side validation
            if(passwordOne.isEmpty()){
                textInputLayoutPassword_register.error = "Password cannot be empty"
                isValid = false
            }

            //client side validation
            if(passwordTwo.isEmpty()){
                textInputLayoutConfirmPassword.error = "Passowrd cannot be empty"
                isValid = false
            }

            //storing user details in shared preferences
            if (isValid) {
                val sharedPref = activity!!.getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE)
                val editor = sharedPref!!.edit()
                editor.putString("FIRST_NAME", firstName)
                editor.putString("LAST_NAME", lastName)
                editor.putString("EMAIL", email)
                editor.putString("PASSWORD", passwordOne)
                editor.putBoolean("ISLOGGEDIN", true)
                editor.apply()

                //navigate to another screen after validation
                Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_loginFragment)
            }

        }

        //set a click listener to the login textview
        fragmentTextViewLogin.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

}
