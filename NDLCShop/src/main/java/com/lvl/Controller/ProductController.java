package com.lvl.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lvl.Entity.Category;
import com.lvl.Entity.Product;
import com.lvl.Service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Product>> getProductsByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.getAllProductsByName(name));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        Category category = new Category();
        category.setCategoryId(categoryId);
        return ResponseEntity.ok(productService.findByCategory(category));
    }   
    @GetMapping("/top-selling")
    public ResponseEntity<List<Product>> getTopSellingProducts() {
        List<Product> topSellingProducts = productService.getTop8BestSellingProducts();
        return ResponseEntity.ok(topSellingProducts);
    }
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam Double minPrice, 
            @RequestParam Double maxPrice) {
        List<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        return ResponseEntity.ok(productService.searchName(keyword));
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    
    // Lấy danh sách sản phẩm bán chạy nhất (Sắp xếp theo số lượng bán)
    @GetMapping("/best-sellers")
    public ResponseEntity<List<Product>> getBestSellingProducts() {
        List<Product> bestSellingProducts = productService.findBestSellingProducts();  // Lấy sản phẩm bán chạy nhất
        return ResponseEntity.ok(bestSellingProducts);
    }

    // Lấy danh sách sản phẩm mới nhất (Sắp xếp theo ngày nhập)
    @GetMapping("/newest")
    public ResponseEntity<List<Product>> getNewestProducts() {
        List<Product> newestProducts = productService.getNewestProducts();  // Lấy sản phẩm mới nhất
        return ResponseEntity.ok(newestProducts);
    }
}
