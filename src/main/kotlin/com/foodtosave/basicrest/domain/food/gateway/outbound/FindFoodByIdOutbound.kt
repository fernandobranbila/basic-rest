package com.foodtosave.basicrest.domain.food.gateway.outbound

import com.foodtosave.basicrest.domain.food.model.Food
import com.foodtosave.basicrest.domain.food.result.model.Result

interface FindFoodByIdOutbound {

    fun execute(foodId: Long): Result<Food, Exception>

}