package com.foodtosave.basicrest.domain.food.usecase

import com.foodtosave.basicrest.domain.food.gateway.inbound.FindFoodByIdInbound
import com.foodtosave.basicrest.domain.food.gateway.outbound.FindFoodByIdOutbound
import com.foodtosave.basicrest.domain.food.model.Food
import com.foodtosave.basicrest.domain.food.result.model.Result
import org.springframework.stereotype.Component

@Component
class FindFoodById(
private val findFoodByIdOutbound: FindFoodByIdOutbound
): FindFoodByIdInbound {

    override fun execute(foodId: Long): Result<Food, Exception> {
        Thread.sleep(10000)
        return findFoodByIdOutbound.execute(foodId)
    }

}