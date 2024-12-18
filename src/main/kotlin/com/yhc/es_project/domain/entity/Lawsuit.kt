package com.yhc.es_project.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDate

@Document(indexName = "lawsuit")
data class Lawsuit(
    @Id
    val id: String? = null,

    @Field(type = FieldType.Keyword)
    val caseNumber: String? = null,

    @Field(type = FieldType.Keyword)
    val court: String? = null,

    @Field(type = FieldType.Date, format = [DateFormat.date])
    val date: LocalDate? = null,

    @Field(type = FieldType.Keyword)
    val caseType: String? = null,

    @Field(type = FieldType.Keyword)
    val judges: String? = null,

    @Field(type = FieldType.Nested)
    val parties: Parties? = null,

    @Field(type = FieldType.Text)
    val summary: String? = null,

    @Field(type = FieldType.Text)
    val fullText: String? = null,

    @Field(type = FieldType.Keyword)
    val keywords: List<String>? = null,

    @Field(type = FieldType.Keyword)
    val precedents: List<String>? = null,

    @Field(type = FieldType.Keyword)
    val ruling: String? = null,

    @Field(type = FieldType.Text)
    val lawsCited: List<String>? = null
)

data class Parties(
    @Field(type = FieldType.Text)
    val plaintiff: String? = null,

    @Field(type = FieldType.Text)
    val defendant: String? = null
)
