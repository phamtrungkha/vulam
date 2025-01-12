package com.lvl.Service;

import java.util.List;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.lvl.Entity.Category;
import com.lvl.Entity.Product;
import com.lvl.Repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Các phương thức đã có
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getAllProductsByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getTop8BestSellingProducts() {
        return productRepository.findTop8BySold();
    }

    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findProductsByPriceRange(minPrice, maxPrice);
    }

    public List<Product> findByCategoryAndName(String name, Category category) {
        return productRepository.findByNameAndCategory(name, category);
    }

    public List<Product> searchName(String keyword) {
        return productRepository.searchByName(keyword);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setDiscount(product.getDiscount());
        existingProduct.setEnteredDate(product.getEnteredDate());
        existingProduct.setImage(product.getImage());
        existingProduct.setStatus(product.getStatus());
        existingProduct.setSold(product.getSold());
        existingProduct.setCategory(product.getCategory());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Các phương thức tìm kiếm mới
    public List<Product> getProductsByStatus(Boolean status) {
        return productRepository.findByStatus(status);
    }

    public List<Product> getProductsByDiscount(Integer discount) {
        return productRepository.findByDiscountGreaterThanEqual(discount);
    }

    public List<Product> getProductsByQuantity(Integer quantity) {
        return productRepository.findByQuantityGreaterThanEqual(quantity);
    }

    public List<Product> getProductsByEnteredDate(Date enteredDate) {
        return productRepository.findByEnteredDate(enteredDate);
    }

    public List<Product> getProductsBySold(Integer sold) {
        return productRepository.findBySoldGreaterThanEqual(sold);
    }

    public List<Product> getProductsByDescription(String description) {
        return productRepository.findByDescriptionContaining(description);
    }
 // Lấy sản phẩm bán chạy nhất (sắp xếp theo số lượng bán)
    public List<Product> findBestSellingProducts() {
        return productRepository.findMostSoldProducts();
    }

    // Lấy sản phẩm mới nhất (sắp xếp theo ngày nhập)
    public List<Product> getNewestProducts() {
        return productRepository.findNewestProducts();
    }
}
