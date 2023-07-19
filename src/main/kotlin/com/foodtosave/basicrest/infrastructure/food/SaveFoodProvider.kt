package com.foodtosave.basicrest.infrastructure.food

import com.foodtosave.basicrest.domain.food.gateway.outbound.SaveFoodOutbound
import com.foodtosave.basicrest.domain.food.model.Food
import com.foodtosave.basicrest.domain.food.result.model.Result
import com.foodtosave.basicrest.infrastructure.food.postgres.FoodRepository
import org.springframework.stereotype.Component

@Component
class SaveFoodProvider(
 private val foodRepository: FoodRepository
): SaveFoodOutbound {

    override fun execute(food: Food): Result<Food, Exception> {
        return foodRepository.saveFood(food)
    }

}