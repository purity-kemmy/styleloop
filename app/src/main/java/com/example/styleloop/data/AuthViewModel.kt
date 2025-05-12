package com.example.styleloop.data


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.styleloop.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel(navController: NavHostController) : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val dbRef = FirebaseDatabase.getInstance().getReference("Users")

    val user = MutableLiveData<User?>()
    val isAuthenticated = MutableLiveData<Boolean>(auth.currentUser != null)

    fun signup(
        name: String,
        email: String,
        password: String,
        confirmPassword: String,
        onResult: (Boolean, String) -> Unit
    ) {
        // Validate fields
        if (name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            onResult(false, "All fields are required.")
            return
        }

        // Validate passwords
        if (password != confirmPassword) {
            onResult(false, "Passwords do not match.")
            return
        }

        // Firebase authentication for signup
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid ?: return@addOnCompleteListener
                    val newUser = User(name, email, uid)

                    // Save user to Firebase Realtime Database
                    dbRef.child(uid).setValue(newUser)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                user.postValue(newUser)
                                isAuthenticated.postValue(true)
                                onResult(true, "Signup successful.")
                            } else {
                                onResult(false, it.exception?.localizedMessage ?: "Signup failed.")
                            }
                        }
                } else {
                    onResult(false, task.exception?.localizedMessage ?: "Signup failed.")
                }
            }
    }

    fun login(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        // Validate fields
        if (email.isBlank() || password.isBlank()) {
            onResult(false, "Email and password cannot be blank.")
            return
        }

        // Firebase authentication for login
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val currentUser = auth.currentUser
                    val userModel = User(
                        name = currentUser?.displayName ?: "Unknown User",
                        email = currentUser?.email ?: email,  // fallback to email if null
                        uid = currentUser?.uid ?: ""
                    )
                    user.postValue(userModel)
                    isAuthenticated.postValue(true)
                    onResult(true, "Login successful.")
                } else {
                    onResult(false, task.exception?.localizedMessage ?: "Login failed.")
                }
            }
    }

    fun logout() {
        auth.signOut()
        user.postValue(null)
        isAuthenticated.postValue(false)
    }
}

fun isLoggedIn(): Boolean = FirebaseAuth.getInstance().currentUser != null
