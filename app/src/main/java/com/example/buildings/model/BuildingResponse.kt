package com.example.buildings.model

import com.google.gson.annotations.Expose

data class BuildingResponse(
    @Expose val data: List<Building>
)