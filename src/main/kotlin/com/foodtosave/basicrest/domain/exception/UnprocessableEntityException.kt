package com.foodtosave.basicrest.domain.exception

class UnprocessableEntityException(message: String? = null, code: String = "") : GenericCodeException(message, code) {
}
