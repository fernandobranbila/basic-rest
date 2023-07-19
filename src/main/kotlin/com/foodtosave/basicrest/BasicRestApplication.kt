package com.foodtosave.basicrest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class BasicRestApplication

fun main(args: Array<String>) {
	runApplication<BasicRestApplication>(*args)
}
