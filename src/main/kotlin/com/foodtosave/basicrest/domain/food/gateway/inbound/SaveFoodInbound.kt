package com.foodtosave.basicrest.domain.food.gateway.inbound

import com.foodtosave.basicrest.domain.food.model.Food
import com.foodtosave.basicrest.domain.food.result.model.Result

interface SaveFoodInbound {

    fun execute(food: Food): Result<Food, Exception>
}