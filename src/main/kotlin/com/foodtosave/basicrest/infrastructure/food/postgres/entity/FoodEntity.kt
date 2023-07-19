package com.foodtosave.basicrest.infrastructure.food.postgres.entity

import com.foodtosave.basicrest.domain.food.model.Food

data class FoodEntity(
    val id: Long?,
    val title: String,
    val code: Int,
    val description: String
){

    fun toDomain(): Food {
        return Food(
            this.id,
            this.title,
            this.code,
            this.description
        )
    }
}
