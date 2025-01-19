package com.example.trackfit.model.service.impl

import com.example.trackfit.model.User
import com.example.trackfit.model.service.AccountService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AccountService {

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = auth.currentUser != null

    override val currentUser: Flow<User> = callbackFlow {
        val listener =
            FirebaseAuth.AuthStateListener { auth ->
                val firebaseUser = auth.currentUser
                if (firebaseUser != null) {
                    val userId = firebaseUser.uid
                    launch {
                        val userDocument = firestore
                            .collection(USER_COLLECTION)
                            .document(userId)
                            .get()
                            .await()
                        val userData =
                            userDocument.toObject(User::class.java) ?: User(userId = userId)
                        trySend(
                            userData.copy(
                                userId = userId,
                                firstName = userData.firstName,
                                lastName = userData.lastName
                            )
                        )
                    }
                } else {
                    trySend(User())
                }
            }
        auth.addAuthStateListener(listener)
        awaitClose { auth.removeAuthStateListener(listener) }
    }

    override suspend fun authenticate(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ) {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val userId = result.user?.uid ?: throw IllegalStateException("User ID cannot be null")
        val user = User(userId = userId, firstName = firstName, lastName = lastName)
        firestore.collection(USER_COLLECTION).document(userId).set(user).await()
    }

    override suspend fun sendRecoveryEmail(email: String) {
        auth.sendPasswordResetEmail(email).await()
    }

    override suspend fun deleteAccount() {
        auth.currentUser!!.delete().await()
    }

    override suspend fun logOut() {
        if (auth.currentUser!!.isAnonymous) {
            auth.currentUser!!.delete()
        }
        auth.signOut()
    }

    companion object {
        private const val USER_COLLECTION = "users"
    }
}
