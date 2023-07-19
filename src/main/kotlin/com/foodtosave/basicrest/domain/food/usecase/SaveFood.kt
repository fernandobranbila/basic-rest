package com.foodtosave.basicrest.domain.food.usecase

import com.foodtosave.basicrest.domain.food.gateway.inbound.SaveFoodInbound
import com.foodtosave.basicrest.domain.food.gateway.outbound.SaveFoodOutbound
import com.foodtosave.basicrest.domain.food.model.Food
import com.foodtosave.basicrest.domain.food.result.model.Result
import org.springframework.stereotype.Component

@Component
class SaveFood(
private val saveFoodOutbound: SaveFoodOutbound
): SaveFoodInbound {

    override fun execute(food: Food): Result<Food, Exception> {
        return saveFoodOutbound.execute(food)
    }

}