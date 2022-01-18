package com.example.mrshopercapstone.Repository

import android.content.Context
import com.example.mrshopercapstone.models.items.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.ref.Reference

class UserProfileRepositoryService(val context: Context) {

    private var UserReference =  Firebase.firestore.collection("UserProfile")

    fun saveProfile(userProfile: UserProfile)= UserReference.document(FirebaseAuth.getInstance().uid.toString()).set(userProfile)
//    fun deleteProfile() = UserReference.document(FirebaseAuth.getInstance().uid.toString()).delete()
    fun getUser()= UserReference.document(FirebaseAuth.getInstance().uid.toString()).get()

    companion object {
        private var instance: UserProfileRepositoryService? = null

        fun init(context: Context) {
            if (instance == null)
                instance = UserProfileRepositoryService(context)
        }

        fun get(): UserProfileRepositoryService {
            return instance ?: throw Exception("UserProfileRepositoryService must be initialized")
        }

    }

}