package com.lvl.Service;

import com.lvl.Entity.Order;
import com.lvl.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckoutService {

    @Autowired
    private OrderRepository orderRepository;

    // Lấy đơn hàng của người dùng có status = 1
    public Optional<Order> getOrderForUser(Long userId) {
        return orderRepository.findByUserUserIdAndStatus(userId, 1);  // Giả sử bạn có phương thức findByUserUserIdAndStatus trong repository
    }

    // Cập nhật thông tin đơn hàng
    public void updateOrder(Long orderId, String address, String phone) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();
            order.setAddress(address);
            order.setPhone(phone);
            order.setStatus(2);  // Đặt trạng thái thành 2
            orderRepository.save(order);  // Cập nhật đơn hàng
        }
    }
}
