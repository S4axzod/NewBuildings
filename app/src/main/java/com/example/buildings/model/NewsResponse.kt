package com.example.buildings.model

import com.google.gson.annotations.Expose

data class NewsResponse(
    @Expose val data: List<News>
)