package com.foodtosave.basicrest.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.BeforeAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import redis.embedded.RedisServer


@SpringBootTest
@AutoConfigureMockMvc
abstract class BaseTest {

    companion object {
        @JvmStatic
        @BeforeAll
        fun postConstruct(): Unit {
            val redisServer = RedisServer.builder()
                .port(6379)
                .build()
            redisServer.start()
        }
    }

    @Autowired
    lateinit var mockMvc: MockMvc

    var mapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())

}
