package br.com.study.kotlin.app.exception

import br.com.study.kotlin.app.exception.client.ClientNotSaveException
import br.com.study.kotlin.app.exception.client.ClientNotUpdateException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.util.WebUtils

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [ClientNotUpdateException::class, ClientNotSaveException::class])
    fun handleException(ex: Exception, webRequest: WebRequest): ResponseEntity<ApiError>{
        if(ex is ClientNotUpdateException){
            val clientNotUpdateException: ClientNotUpdateException = ex
            return handleClientNotUpdateException(clientNotUpdateException, HttpStatus.BAD_REQUEST, webRequest)
        }
        if(ex is ClientNotSaveException){
            val clientNotSaveException: ClientNotSaveException = ex
            return handleClientNotSaveException(clientNotSaveException, HttpStatus.BAD_REQUEST, webRequest)
        }
        return handleExceptionInternal(ex, null, HttpStatus.INTERNAL_SERVER_ERROR, webRequest)
    }

    internal fun handleClientNotUpdateException(
            ex: ClientNotUpdateException,
            status: HttpStatus,
            request: WebRequest): ResponseEntity<ApiError>
    {
        return handleExceptionInternal(ex, ApiError(ex.message), status, request)
    }

    internal fun handleClientNotSaveException(
            ex: ClientNotSaveException,
            status: HttpStatus,
            request: WebRequest): ResponseEntity<ApiError>
    {
        return handleExceptionInternal(ex, ApiError(ex.message), status, request)
    }

    internal fun handleExceptionInternal(
            ex: Exception,
            body: ApiError?,
            status: HttpStatus,
            request: WebRequest): ResponseEntity<ApiError>
    {
        if(HttpStatus.INTERNAL_SERVER_ERROR == status){
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST)
        }
        return ResponseEntity(body, status)
    }
}