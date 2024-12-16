package com.yhc.es_project.adapter.web

import com.yhc.es_project.domain.application.ProductService
import com.yhc.es_project.domain.entity.Product
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(private val productService: ProductService) {

    @PostMapping
    fun createProduct(@RequestBody request: CreateProductRequest): ResponseEntity<Product> {
        val createdProduct = productService.createProduct(
            product = Product(
                id = request.id,
                name = request.name,
                description = request.description,
                price = request.price,
            )
        )
        return ResponseEntity(createdProduct, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: String): ResponseEntity<Product> {
        val product = productService.getProductById(id)
        return ResponseEntity.ok(product)
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: String, @RequestBody request: UpdateProductRequest): ResponseEntity<Product> {
        val updatedProduct = productService.updateProduct(
            id = id,
            request = request
        )
        return ResponseEntity.ok(updatedProduct)
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: String): ResponseEntity<Unit> {
        productService.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/search")
    fun searchProducts(@RequestParam query: String): ResponseEntity<List<Product>> {
        val products = productService.searchProducts(query)
        return ResponseEntity.ok(products)
    }

    @GetMapping
    fun getAllProducts(): ResponseEntity<List<Product>> {
        val products = productService.getAllProducts()
        return ResponseEntity.ok(products)
    }
}
