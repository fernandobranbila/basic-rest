package com.foodtosave.basicrest.domain.exception

open class GenericCodeException(message: String? = "", val code: String? = null) : RuntimeException(message) {
}