package com.yhc.es_project.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.util.*


@Document(indexName = "lawsuit")
class Lawsuit {
    @Id
    private val id: String? = null

    @Field(type = FieldType.Keyword)
    private val caseNumber: String? = null

    @Field(type = FieldType.Keyword)
    private val court: String? = null

    @Field(type = FieldType.Date)
    private val date: Date? = null

    @Field(type = FieldType.Keyword)
    private val caseType: String? = null

    @Field(type = FieldType.Keyword)
    private val judges: String? = null

    @Field(type = FieldType.Nested)
    private val parties: Parties? = null

    @Field(type = FieldType.Text)
    private val summary: String? = null

    @Field(type = FieldType.Text)
    private val fullText: String? = null

    @Field(type = FieldType.Keyword)
    private val keywords: List<String>? = null

    @Field(type = FieldType.Keyword)
    private val precedents: List<String>? = null

    @Field(type = FieldType.Keyword)
    private val ruling: String? = null

    @Field(type = FieldType.Text)
    private val lawsCited: List<String>? = null // Getters and setters
}

internal class Parties {
    @Field(type = FieldType.Text)
    private val plaintiff: String? = null

    @Field(type = FieldType.Text)
    private val defendant: String? = null // Getters and setters
}
