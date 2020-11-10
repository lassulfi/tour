package com.github.lassulfi.tour.exception

class PromocaoNotFoundException : Exception  {
    constructor(message: String): super(message)

    constructor(message: String, cause: Throwable): super(message, cause)
}