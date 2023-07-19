package com.foodtosave.basicrest.infrastructure.food

import com.foodtosave.basicrest.domain.food.gateway.outbound.FindFoodByIdOutbound
import com.foodtosave.basicrest.domain.food.model.Food
import com.foodtosave.basicrest.domain.food.result.model.Result
import com.foodtosave.basicrest.infrastructure.food.postgres.FoodRepository
import org.springframework.stereotype.Component

@Component
class FindFoodByIdProvider(
 private val foodRepository: FoodRepository
): FindFoodByIdOutbound {

    override fun execute(foodId: Long): Result<Food, Exception> {
        return foodRepository.findFoodById(foodId)
    }

}