package com.foodtosave.basicrest.fixture

import com.foodtosave.basicrest.application.food.entrypoint.dto.FoodDto

class FoodFixture {

    companion object {

        fun createFoodDto(
            id: Long? = null,
            title: String,
            code: Int,
            description: String
        ) =
            FoodDto(
                id,
                title,
                code,
                description
            )

    }
}