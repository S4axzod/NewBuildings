package com.example.buildings.model

import com.google.gson.annotations.Expose

class Partner(
    @Expose val id: Int,
    @Expose val name: String,
    @Expose val photo: String
)