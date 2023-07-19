package com.foodtosave.basicrest.infrastructure.food.postgres

import com.foodtosave.basicrest.domain.exception.NotFoundException
import com.foodtosave.basicrest.domain.exception.RepositoryException
import com.foodtosave.basicrest.domain.food.model.Food
import com.foodtosave.basicrest.domain.food.result.model.Failure
import com.foodtosave.basicrest.domain.food.result.model.Result
import com.foodtosave.basicrest.domain.food.result.model.Success
import com.foodtosave.basicrest.infrastructure.food.postgres.entity.FoodEntity
import org.springframework.dao.DataAccessException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Component

@Component
class FoodRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate
) : FoodRepository {

    private val SQL_FIND_PERSON = "SELECT * FROM food WHERE id = ?"

    override fun findFoodById(foodById: Long): Result<Food, Exception> {
        val employeeRowMapper =
            RowMapper<FoodEntity> { rs, _ ->
                FoodEntity(
                    rs.getLong("id"),
                    rs.getString("title"),
                    rs.getInt("code"),
                    rs.getString("description")
                )
            }
        return try {
            Success(jdbcTemplate.queryForObject(SQL_FIND_PERSON, employeeRowMapper, foodById)!!.toDomain())
        } catch (e: EmptyResultDataAccessException) {
            Failure(NotFoundException(e.message, "food.repository.not-found"))
        } catch (e: Exception) {
            Failure(RepositoryException(e.message, "food.repository.generic-exception"))
        }
    }

    override fun saveFood(food: Food): Result<Food, Exception> {
        return try {
            val parameters = mapOf<String, Any>(
                Pair("title", food.title),
                Pair("description", food.description),
                Pair("code", food.code)
            )
            val simpleJdbcInsert = SimpleJdbcInsert(jdbcTemplate)
                .withTableName("food")
                .usingGeneratedKeyColumns("id")
            val createdId = simpleJdbcInsert.executeAndReturnKey(parameters).toLong()
            Success(food.copy(id = createdId))
        } catch (e: DataAccessException) {
            Failure(RepositoryException(e.message, "food.failed-insert"))
        } catch (e: Exception) {
            Failure(RepositoryException(e.message, "food.repository.generic-exception"))
        }
    }
}