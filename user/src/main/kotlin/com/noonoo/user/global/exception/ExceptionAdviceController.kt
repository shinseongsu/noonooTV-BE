package com.noonoo.user.global.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdviceController {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ExceptionResponse {
        val message =
            ex.bindingResult.fieldErrors
                .first()
                .let {
                    it.defaultMessage
                        ?.replace("{0}", it.field)
                        ?.replace("{1}", it.arguments?.getOrNull(2)?.toString() ?: "")
                        ?.replace("{2}", it.arguments?.getOrNull(1)?.toString() ?: "")
                        ?: ""
                }

        return ExceptionResponse(
            code = "BAD_REQUEST",
            message = message
        )
    }
}
