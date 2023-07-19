package com.foodtosave.basicrest.domain.exception

class NotFoundException(message: String? = null, code: String = "") : GenericCodeException(message, code) {


}