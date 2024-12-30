package com.example.trackfit.data.meal

import kotlinx.coroutines.flow.Flow

class OfflineMealRepository(private val mealDao: MealDao) : MealRepository {
    override fun getAllMealsStream(): Flow<List<Meal>> = mealDao.getAllMeals()

    override fun getMealStream(id: Int): Flow<Meal?> = mealDao.getMeal(id)

    override suspend fun insertMeal(meal: Meal) = mealDao.insert(meal)

    override suspend fun deleteMeal(meal: Meal) = mealDao.delete(meal)

    override suspend fun updateMeal(meal: Meal) = mealDao.update(meal)
}