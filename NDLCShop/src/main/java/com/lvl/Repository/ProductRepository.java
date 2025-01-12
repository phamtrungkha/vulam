package com.lvl.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.lvl.Entity.Category;
import com.lvl.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Các phương thức đã có
    List<Product> findByStatus(Boolean status);
    
    List<Product> findByStatusTrueOrderBySoldDesc();
    
    List<Product> findTop10ByOrderBySoldDesc();

    List<Product> findByStatusTrueOrderByQuantityDesc();
    
    List<Product> findByStatusTrueOrderByEnteredDateDesc();
    
    List<Product> findByCategory(Category category);
    
    List<Product> findByName(String name);
    
    List<Product> findByNameAndCategory(String name, Category category);
    
    Product findByProductIdAndStatusTrue(Long id);
    
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword%")
    List<Product> searchByName(@Param("keyword") String keyword);

    @Query("SELECT p FROM Product p ORDER BY p.sold DESC")
    List<Product> findTop8BySold();

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findProductsByPriceRange(Double minPrice, Double maxPrice);
    
 // Get top 5 most sold products
    @Query("SELECT p FROM Product p ORDER BY p.sold DESC")
    List<Product> findTop5BySold();

    // Truy vấn để lấy sản phẩm bán chạy nhất (sắp xếp theo số lượng bán)
    @Query("SELECT p FROM Product p ORDER BY p.sold DESC")
    List<Product> findMostSoldProducts();

    // Truy vấn để lấy sản phẩm mới nhất (sắp xếp theo ngày nhập)
    @Query("SELECT p FROM Product p ORDER BY p.enteredDate DESC")
    List<Product> findNewestProducts();
    // Tìm sản phẩm có status = true

    List<Product> findByDiscountGreaterThanEqual(Integer discount);

    List<Product> findByQuantityGreaterThanEqual(Integer quantity);

    List<Product> findByEnteredDate(Date enteredDate);

    List<Product> findBySoldGreaterThanEqual(Integer sold);

    List<Product> findByDescriptionContaining(String description);
}
