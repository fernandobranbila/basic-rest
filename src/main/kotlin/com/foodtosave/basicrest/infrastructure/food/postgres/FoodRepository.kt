package com.foodtosave.basicrest.infrastructure.food.postgres

import com.foodtosave.basicrest.domain.food.model.Food
import com.foodtosave.basicrest.domain.food.result.model.Result

interface FoodRepository {

    fun findFoodById(foodById: Long): Result<Food, Exception>

    fun saveFood(food: Food): Result<Food, Exception>

}