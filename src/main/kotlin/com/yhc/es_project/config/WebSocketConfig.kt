package com.yhc.es_project.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig(
    private val elasticsearchOperations: ElasticsearchOperations,
    private val objectMapper: ObjectMapper
) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(webSocketHandler(), "/ws")
            .setAllowedOrigins("*")
    }

    @Bean
    fun webSocketHandler(): WebSocketHandler = SearchWebSocketHandler(
        elasticsearchOperations = elasticsearchOperations,
        objectMapper = objectMapper
    )
}