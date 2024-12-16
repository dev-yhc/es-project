package com.yhc.es_project.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.elasticsearch.client.elc.NativeQuery
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery

@Component
class SearchWebSocketHandler(
    private val elasticsearchOperations: ElasticsearchOperations,
    private val objectMapper: ObjectMapper
) : TextWebSocketHandler() {

//    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
//        val query = message.payload
//        val results = searchProducts(query)
//        val jsonResults = objectMapper.writeValueAsString(results)
//        session.sendMessage(TextMessage(jsonResults))
//    }

//    private fun searchProducts(query: String): List<Product> {
//        val searchQuery = NativeQuery.builder()
//            .withQuery { q ->
//                q.match { m ->
//                    m.field("name").query(query)
//                }
//            }
//            .build()
//
//        return elasticsearchOperations.search(searchQuery, Product::class.java)
//            .searchHits
//            .map { it.content }
//    }
}
