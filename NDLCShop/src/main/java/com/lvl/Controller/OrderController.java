package com.lvl.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lvl.Entity.Order;
import com.lvl.Service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // API để thêm sản phẩm vào giỏ hàng
    @GetMapping("/addcart")
    public ResponseEntity<Boolean> addCartByProductIdAndUserId(@RequestParam String productId, @RequestParam String userId) {
        boolean isAdded = orderService.AddToOrder(userId, productId);
        if (isAdded) {
            return ResponseEntity.ok(true); 
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false); 
        }
    }

    // API để lấy danh sách đơn hàng của người dùng theo status
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders(@RequestParam Long userId) {
        // Lấy danh sách đơn hàng với các status khác nhau
        List<Order> orders = orderService.getOrdersByUserIdAndStatus(userId);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }
}
