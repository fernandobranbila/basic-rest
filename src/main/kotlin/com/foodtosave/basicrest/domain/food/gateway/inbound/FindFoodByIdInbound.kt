package com.foodtosave.basicrest.domain.food.gateway.inbound

import com.foodtosave.basicrest.domain.food.model.Food
import com.foodtosave.basicrest.domain.food.result.model.Result

interface FindFoodByIdInbound {

    fun execute(foodId: Long): Result<Food, Exception>
}