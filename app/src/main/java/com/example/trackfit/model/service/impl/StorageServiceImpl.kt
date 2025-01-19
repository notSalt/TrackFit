package com.example.trackfit.model.service.impl

import com.example.trackfit.model.Activity
import com.example.trackfit.model.Meal
import com.example.trackfit.model.service.AccountService
import com.example.trackfit.model.service.StorageService
import com.example.trackfit.model.service.trace
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    private val auth: AccountService,
    private val firestore: FirebaseFirestore
) : StorageService {

    private fun activityCollection(uid: String): CollectionReference =
        firestore.collection(USER_COLLECTION).document(uid).collection(ACTIVITY_COLLECTION)

    @OptIn(ExperimentalCoroutinesApi::class)
    override val activities: Flow<List<Activity>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                firestore.collection(ACTIVITY_COLLECTION).whereEqualTo(USER_ID_FIELD, user.userId)
                    .dataObjects()
            }

    override suspend fun getActivity(activityId: String): Activity? =
        firestore.collection(ACTIVITY_COLLECTION).document(activityId).get().await().toObject()

    override suspend fun saveActivity(activity: Activity): String =
        trace(SAVE_ACTIVITY_TRACE) {
            val updatedActivity = activity.copy(userId = auth.currentUserId)
            firestore.collection(ACTIVITY_COLLECTION).add(updatedActivity).await().id
        }

    override suspend fun updateActivity(activity: Activity): Unit =
        trace(UPDATE_ACTIVITY_TRACE) {
            firestore.collection(ACTIVITY_COLLECTION).document(activity.id).set(activity).await()
        }

    override suspend fun deleteActivity(activityId: String) {
        firestore.collection(ACTIVITY_COLLECTION).document(activityId).delete().await()
    }

    private fun mealCollection(uid: String): CollectionReference =
        firestore.collection(USER_COLLECTION).document(uid).collection(MEAL_COLLECTION)

    // Meals
    @OptIn(ExperimentalCoroutinesApi::class)
    override val meals: Flow<List<Meal>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                firestore.collection(MEAL_COLLECTION).whereEqualTo(USER_ID_FIELD, user.userId)
                    .dataObjects()
            }

    override suspend fun getMeal(mealId: String): Meal? =
        firestore.collection(MEAL_COLLECTION).document(mealId).get().await().toObject()

    override suspend fun saveMeal(meal: Meal): String =
        trace(SAVE_MEAL_TRACE) {
            val updatedMeal = meal.copy(userId = auth.currentUserId)
            firestore.collection(MEAL_COLLECTION).add(updatedMeal).await().id
        }

    override suspend fun updateMeal(meal: Meal): Unit =
        trace(UPDATE_MEAL_TRACE) {
            firestore.collection(MEAL_COLLECTION).document(meal.id).set(meal).await()
        }

    override suspend fun deleteMeal(mealId: String) {
        firestore.collection(MEAL_COLLECTION).document(mealId).delete().await()
    }

    companion object {
        private const val USER_COLLECTION = "users"
        private const val USER_ID_FIELD = "userId"
        private const val CREATED_AT_FIELD = "createdAt"
        private const val ACTIVITY_COLLECTION = "activities"
        private const val SAVE_ACTIVITY_TRACE = "saveActivity"
        private const val UPDATE_ACTIVITY_TRACE = "updateActivity"
        private const val MEAL_COLLECTION = "meals"
        private const val SAVE_MEAL_TRACE = "saveMeal"
        private const val UPDATE_MEAL_TRACE = "updateMeal"
    }
}
