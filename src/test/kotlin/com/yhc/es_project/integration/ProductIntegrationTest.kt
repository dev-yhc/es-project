package com.yhc.es_project.integration

import com.yhc.es_project.domain.entity.Product
import com.yhc.es_project.domain.repository.ProductRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@TestPropertySource(properties = [
    "spring.elasticsearch.uris=http://localhost:9200"
])
class ProductIntegrationTest {
    @Autowired
    lateinit var productRepository: ProductRepository

    @Test
    fun `should save and retrieve product`() {
        val product = Product("1", "Test", "Description", 19.99)
        productRepository.save(product)

        val retrieved = productRepository.findById("1").orElse(null)
        assertNotNull(retrieved)
        assertEquals(product, retrieved)
    }
}