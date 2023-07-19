package com.foodtosave.basicrest.integration.food

import com.fasterxml.jackson.module.kotlin.readValue
import com.foodtosave.basicrest.application.food.entrypoint.dto.FoodDto
import com.foodtosave.basicrest.config.BaseTest
import com.foodtosave.basicrest.domain.food.result.model.orThrow
import com.foodtosave.basicrest.fixture.FoodFixture.Companion.createFoodDto
import com.foodtosave.basicrest.infrastructure.food.postgres.FoodRepository
import org.junit.Before
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import redis.embedded.RedisServer


@SpringBootTest
@AutoConfigureMockMvc
class FoodIntegrationTest: BaseTest() {

	@Autowired
	lateinit var foodRepository: FoodRepository

	@Test
	fun createFoodTest() {
		val foodDto = createFoodDto(null, "test food", 1, "best food")
		val foodDtoAsString = mapper.writeValueAsString(foodDto)
		val createFoodMockMvcResponse = mockMvc.perform(
			MockMvcRequestBuilders.post("/api/v1/foods")
			.content(foodDtoAsString)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isCreated)
			.andReturn()

		val createFoodResponse = mapper.readValue<FoodDto>(createFoodMockMvcResponse.response.contentAsString)

		Assertions.assertNotNull(createFoodResponse.id)
		Assertions.assertEquals(foodDto.code, createFoodResponse.code)
		Assertions.assertEquals(foodDto.description, createFoodResponse.description)
		Assertions.assertEquals(foodDto.title, createFoodResponse.title)

		val createdFood = FoodDto.fromDomain(foodRepository.findFoodById(createFoodResponse.id!!).orThrow())
		Assertions.assertEquals(createdFood, createFoodResponse)
	}

	@Test
	fun findFoodTest() {
		val foodDto = createFoodDto(null, "test food", 1, "best food")
		val foodDtoAsString = mapper.writeValueAsString(foodDto)
		val createFoodMockMvcResponse = mockMvc.perform(
			MockMvcRequestBuilders.post("/api/v1/foods")
				.content(foodDtoAsString)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isCreated)
			.andReturn()

		val createFoodResponse = mapper.readValue<FoodDto>(createFoodMockMvcResponse.response.contentAsString)

		val findFoodMockMvcResponse = mockMvc.perform(
			MockMvcRequestBuilders.get("/api/v1/foods/${createFoodResponse.id!!}")
				.content(foodDtoAsString)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk)
			.andReturn()

		val findFoodResponse = mapper.readValue<FoodDto>(findFoodMockMvcResponse.response.contentAsString)

		val createdFood = FoodDto.fromDomain(foodRepository.findFoodById(createFoodResponse.id!!).orThrow())
		Assertions.assertEquals(createdFood, findFoodResponse)
	}

}
