package com.example.buildings.model

import com.google.gson.annotations.Expose

data class Event(
        @Expose val id: Int,
        @Expose val name: String,
        @Expose val description: String,
        @Expose val photo: String,
        @Expose val logotype: String
)