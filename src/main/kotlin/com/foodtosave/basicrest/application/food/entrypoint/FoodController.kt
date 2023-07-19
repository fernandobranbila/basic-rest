package com.foodtosave.basicrest.application.food.entrypoint

import com.foodtosave.basicrest.application.GlobalExceptionHandler
import com.foodtosave.basicrest.application.food.entrypoint.dto.FoodDto
import com.foodtosave.basicrest.domain.food.gateway.inbound.FindFoodByIdInbound
import com.foodtosave.basicrest.domain.food.gateway.inbound.SaveFoodInbound
import com.foodtosave.basicrest.domain.food.result.model.orThrow
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/foods")
class FoodController(
    private val findFoodByIdInbound: FindFoodByIdInbound,
    private val saveFoodInbound: SaveFoodInbound
) {

    companion object {
        private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    @GetMapping("/{foodId}")
    @Cacheable("foods", key = "#foodId")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable foodId: Long): FoodDto {
        return FoodDto.fromDomain(findFoodByIdInbound.execute(foodId).orThrow())
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@Valid @RequestBody food: FoodDto): FoodDto {
        return FoodDto.fromDomain(saveFoodInbound.execute(food.toDomain()).orThrow())
    }
}