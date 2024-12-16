package com.yhc.es_project.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "products")
data class Product(
    @Id
    val id: String,

    @Field(type = FieldType.Text, name = "name")
    var name: String,

    @Field(type = FieldType.Text, name = "description")
    var description: String,

    @Field(type = FieldType.Double, name = "price")
    var price: Double
)
