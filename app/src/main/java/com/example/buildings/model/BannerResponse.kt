package com.example.buildings.model

import com.google.gson.annotations.Expose

data class BannerResponse(
    @Expose val data : List<Event>
)