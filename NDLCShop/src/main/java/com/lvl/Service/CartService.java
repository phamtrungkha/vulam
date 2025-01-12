package com.lvl.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lvl.Entity.Order;
import com.lvl.Repository.OrderRepository;

@Service
public class CartService {

    @Autowired
    private OrderRepository orderRepository;

    // Lấy giỏ hàng của người dùng với status = 1
    public Order GetOrderByUserIdAndStatus(Long userId, int status) {
        return orderRepository.findByUser_UserIdAndStatus(userId, status);
    }

    // Cập nhật giỏ hàng
    public boolean updateCart(Order order) {
        try {
            // Kiểm tra dữ liệu
            if (order.getOrderId() == null || order.getUser() == null) {
                throw new IllegalArgumentException("Order ID hoặc User không hợp lệ");
            }

            // Đảm bảo các `OrderDetail` có liên kết đúng với `Order`
            order.getOrderDetails().forEach(detail -> detail.setOrder(order));

            // Lưu giỏ hàng
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa sản phẩm khỏi giỏ hàng
    public boolean removeProductFromCart(Long userId, Long productId) {
        Order order = GetOrderByUserIdAndStatus(userId, 1);  // Lọc giỏ hàng có status = 1
        if (order != null) {
            order.getOrderDetails().removeIf(detail -> detail.getProduct().getProductId().equals(productId));
            updateCart(order);  // Cập nhật lại giỏ hàng sau khi xóa sản phẩm
            return true;
        }
        return false;
    }
}
