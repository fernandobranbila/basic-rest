package com.foodtosave.basicrest.domain.food.gateway.outbound

import com.foodtosave.basicrest.domain.food.model.Food
import com.foodtosave.basicrest.domain.food.result.model.Result

interface SaveFoodOutbound {

    fun execute(food: Food): Result<Food, Exception>

}