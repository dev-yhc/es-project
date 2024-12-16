package com.yhc.es_project.adapter.web

data class CreateProductRequest(
    val id: String,
    val name: String,
    val description: String,
    val price: Double
)

data class UpdateProductRequest(
    val name: String,
    val description: String,
    val price: Double
)

