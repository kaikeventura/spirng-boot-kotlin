package br.com.study.kotlin.app.exception

import org.springframework.http.HttpStatus

class ApiError(override val message: String?): Exception()