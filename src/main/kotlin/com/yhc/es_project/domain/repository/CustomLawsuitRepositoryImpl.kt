package com.yhc.es_project.domain.repository

import com.yhc.es_project.domain.entity.Lawsuit
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.core.ElasticsearchOperations

import org.springframework.data.elasticsearch.client.elc.NativeQuery
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.domain.PageImpl
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery
import co.elastic.clients.elasticsearch._types.query_dsl.PrefixQuery
import co.elastic.clients.elasticsearch._types.query_dsl.WildcardQuery
import co.elastic.clients.elasticsearch._types.query_dsl.TextQueryType

class CustomLawsuitRepositoryImpl(private val elasticsearchOperations: ElasticsearchOperations) : CustomLawsuitRepository {
    override fun searchLawsuits(keyword: String, pageable: Pageable): Page<Lawsuit> {
        val query = NativeQuery.builder()
            .withQuery { q ->
                q.bool { b ->
                    b.should(
                        MultiMatchQuery.of { m ->
                            m.query(keyword)
                                .fields("court^2", "case_type^2", "judges^2", "parties.plaintiff^2", "parties.defendant^2",
                                    "summary^1.5", "full_text", "keywords^2", "precedents", "ruling^2", "laws_cited^1.5")
                                .type(TextQueryType.BestFields)
                                .fuzziness("AUTO")
                        }._toQuery(),
                        PrefixQuery.of { p ->
                            p.field("case_number").value(keyword).boost(5.0f)
                        }._toQuery(),
                        WildcardQuery.of { w ->
                            w.field("case_number").wildcard("*$keyword*").boost(3.0f)
                        }._toQuery()
                    )
                }
            }
            .withPageable(pageable)
            .build()

        val searchHits: SearchHits<Lawsuit> = elasticsearchOperations.search(query, Lawsuit::class.java)
        val lawsuits = searchHits.searchHits.map { it.content }
        return PageImpl(lawsuits, pageable, searchHits.totalHits)
    }
}


