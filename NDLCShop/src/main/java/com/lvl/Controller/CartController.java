package com.lvl.Controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lvl.Entity.Order;
import com.lvl.Entity.OrderDetail;
import com.lvl.Repository.OrderRepository;
import com.lvl.Service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderRepository orderRepository;
    // Lấy giỏ hàng của người dùng với status = 1
    @GetMapping
    public ResponseEntity<Order> getCart(@RequestParam Long userId) {
        // Lọc đơn hàng của người dùng có status = 1
        Order order = cartService.GetOrderByUserIdAndStatus(userId, 1);
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    // Cập nhật giỏ hàng
    @PutMapping("/update")
    public ResponseEntity<?> updateCart(@RequestParam String orderId, @RequestParam String amount, @RequestParam String orderDetailsId, @RequestParam String quantity) {
		Order order = orderRepository.findById(Long.parseLong(orderId)).orElse(null);
		order.setAmount(Double.valueOf(amount));
		for(int i=0 ; i<order.getOrderDetails().size() ; i++) {
			if(order.getOrderDetails().get(i).getOrderDetailId() == Long.parseLong(orderDetailsId)) {
				order.getOrderDetails().get(i).setQuantity(Integer.valueOf(quantity));
			}
		}
		
		boolean result = cartService.updateCart(order);
		if (result) {
		    return ResponseEntity.ok(Collections.singletonMap("message", "Cập nhật giỏ hàng thành công"));
		} else {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		                         .body(Collections.singletonMap("message", "Lỗi khi cập nhật giỏ hàng"));
		}
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @DeleteMapping("/removeProduct")
    public ResponseEntity<Boolean> removeProductFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        boolean isRemoved = cartService.removeProductFromCart(userId, productId);
        if (isRemoved) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
}


