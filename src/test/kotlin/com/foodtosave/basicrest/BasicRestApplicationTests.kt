package com.foodtosave.basicrest

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.boot.test.context.SpringBootTest
import redis.embedded.RedisServer


@SpringBootTest
class BasicRestApplicationTests {

	@BeforeEach
	fun setUp() {
		val redisServer = RedisServer(6379)
		redisServer.start()
		redisServer.stop()
	}


	@Test
	fun contextLoads() {
		assert(1 == 1)
	}

}
