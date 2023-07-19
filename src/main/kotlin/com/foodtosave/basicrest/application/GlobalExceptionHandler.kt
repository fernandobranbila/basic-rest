package com.foodtosave.basicrest.application

import com.foodtosave.basicrest.domain.exception.ErrorMessage
import com.foodtosave.basicrest.domain.exception.GenericCodeException
import com.foodtosave.basicrest.domain.exception.NotFoundException
import com.foodtosave.basicrest.domain.exception.RepositoryException
import org.slf4j.LoggerFactory
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@ControllerAdvice
class GlobalExceptionHandler(
        private val messageSource: MessageSource
) {

    companion object {
        private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    }

    @ExceptionHandler(value = [(Exception::class), (java.lang.Exception::class), (RepositoryException::class), (GenericCodeException::class)])
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun exception(e: Exception): ResponseEntity<ErrorMessage> {
        log.error("Exception", e)
        return ResponseEntity(ErrorMessage("", e.message!!), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(value = [(NotFoundException::class)])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFoundException(e: GenericCodeException): ResponseEntity<ErrorMessage> {
        log.error("NotFoundException", e)
        return ResponseEntity(resolveExceptionMessage(e), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [(MethodArgumentNotValidException::class)])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun springValidationException(e: Exception): ResponseEntity<ErrorMessage> {
        log.error("Spring validation error", e)
        return ResponseEntity(resolveExceptionMessage(e), HttpStatus.BAD_REQUEST)
    }

    private fun resolveExceptionMessage(e: GenericCodeException) =
            if (e.code != null) {
                ErrorMessage(e.code, messageSource.getMessage(e.code, null, Locale("en-US")))
            } else {
                ErrorMessage("non-mapped-exception", e.message!!)
            }

    private fun resolveExceptionMessage(e: Exception) =
            ErrorMessage(e.toString(), e.message!!)


}




