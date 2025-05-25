package dev.luischang.lab03countryapp.data.remote.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object FirebaseAuthManager {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun registerUser(name: String, email: String, password: String): Result <Unit> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(email, password).await()
            val uid = authResult.user?.uid ?: return Result.failure(Exception("Invalid user ID"))

            val user = hashMapOf(
                "uid" to uid,
                "email" to email,
                "name" to name
            )
            firestore.collection("users").document(uid).set(user).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    //Login
    suspend fun loginUser(email: String, password: String): Result<Unit> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            val uid = authResult.user?.uid ?: return Result.failure(Exception("Invalid user ID"))
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}