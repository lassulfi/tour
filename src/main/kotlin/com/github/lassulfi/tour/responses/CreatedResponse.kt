package com.github.lassulfi.tour.responses

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class CreatedResponse (val message: String,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") val date: Date)