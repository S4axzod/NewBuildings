package com.example.buildings.model

import com.google.gson.annotations.Expose
import java.io.Serializable

data class News(
    @Expose val id: Int,
    @Expose val title: String,
    @Expose val content: String,
    @Expose val photo: String,
    @Expose val date: String
) : Serializable