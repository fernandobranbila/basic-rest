package com.foodtosave.basicrest.application.food.entrypoint.dto

import com.foodtosave.basicrest.domain.food.model.Food
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.io.Serializable

data class FoodDto(
    val id: Long?,
    @field:NotNull
    val title: String,
    @field:Min(1)
    val code: Int,
    val description: String
): Serializable {

    companion object{
        fun fromDomain(food: Food) =
            FoodDto(
                food.id,
                food.title,
                food.code,
                food.description
            )
    }

    fun toDomain(): Food {
        return Food(
            this.id,
            this.title,
            this.code,
            this.description
        )
    }
}
