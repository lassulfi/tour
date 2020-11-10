package com.github.lassulfi.tour.advice

import com.fasterxml.jackson.core.JsonParseException
import com.github.lassulfi.tour.exception.PromocaoNotFoundException
import com.github.lassulfi.tour.responses.ErrorMessage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(JsonParseException::class)
    fun jsonParseExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse,
                                  exception: Exception) = ResponseEntity(ErrorMessage("Bad Request",
            exception.localizedMessage ?: "Corpo da requisição mal-formado"), HttpStatus.BAD_REQUEST)

    @ExceptionHandler(PromocaoNotFoundException::class)
    fun promocaoNotFoundExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse,
                                         exception: Exception) = ResponseEntity(ErrorMessage("Not Found",
            exception.message !!), HttpStatus.NOT_FOUND)
}